package com.zmm.springboot;

import com.alibaba.fastjson.JSONObject;
import com.sun.jdi.VoidValue;
import com.zmm.springboot.entry.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.elasticsearch.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void addIndex() throws Exception {
        CreateIndexRequest indexRequest=new CreateIndexRequest("zmm_index");
        indexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1));

        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()

                .startObject("properties") //设置之定义字段

                .startObject("name")//字段id
                .field("type","text")//设置数据类型
                .endObject()

                .startObject("address")
                .field("type","text")
                .endObject()

                .startObject("age")
                .field("type","integer")
                .endObject()

                .startObject("interests")
                .field("type","text")
                .endObject()

                .startObject("birthday")
                .field("type","date")
                .endObject()
                .endObject()
                .endObject();

        indexRequest.mapping(mapping);

        restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void deleteIndex() throws Exception {
        DeleteIndexRequest indexRequest=new DeleteIndexRequest("zmm_index");
        restHighLevelClient.indices().delete(indexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void existsIndex() throws Exception {
        GetIndexRequest indexRequest=new GetIndexRequest("zmm_index");
        GetIndexResponse  getIndexResponse=restHighLevelClient.indices().get(indexRequest, RequestOptions.DEFAULT);
        //restHighLevelClient.indices().exists(indexRequest, RequestOptions.DEFAULT);

        System.out.println(getIndexResponse.getSettings());
        for(Map.Entry<String, MappingMetadata> mappingMetadata:getIndexResponse.getMappings().entrySet()){
            System.out.println("================");
            System.out.println(mappingMetadata.getKey());
            System.out.println(mappingMetadata.getValue().getSourceAsMap().toString());
        }
    }

    @Test
    public void add() throws Exception {
        User user=User.builder()
                .name("wangwu")
                .address("gangzou")
                .age(30)
                .interests("张楠 了咯 老大")
                .build();

        IndexRequest request=new IndexRequest("lib3");

        request.source(JSONObject.toJSONString(user),XContentType.JSON);

        IndexResponse response =restHighLevelClient.index(request,RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    public void getById() throws Exception {
        GetRequest request=new GetRequest("lib3");
        request.id("7LgVK38B1VUThO2gJ4_6");

        GetResponse  getResponse=restHighLevelClient.get(request,RequestOptions.DEFAULT);

        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse.toString());
    }

    @Test
    public void updateIndex()throws Exception {
        UpdateRequest request=new UpdateRequest("lib3","7LgVK38B1VUThO2gJ4_6");

        User user=User.builder()
                .name("wangwu777")
                .address("gangzou888")
                .age(30)
                .interests("张楠 了咯 老大 999")
                .birthday(new Date())
                .build();

        request.doc(JSONObject.toJSONString(user),XContentType.JSON);

        UpdateResponse response =restHighLevelClient.update(request,RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }

    @Test
    public void updateFileIndex()throws Exception {
        UpdateRequest request=new UpdateRequest("lib3","7LgVK38B1VUThO2gJ4_6");

        User user=User.builder()
                .name("赵柳")
                .address("龙华")
                .build();

        request.doc(JSONObject.toJSONString(user),XContentType.JSON);

        UpdateResponse response =restHighLevelClient.update(request,RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }

    @Test
    public void delete() throws Exception {
        DeleteRequest request=new DeleteRequest("lib3","7LgVK38B1VUThO2gJ4_6");

        restHighLevelClient.delete(request,RequestOptions.DEFAULT);
    }

    @Test
    public void bulk()throws Exception {
        BulkRequest bulkRequest=new BulkRequest();

        for(int i=0;i<10;i++){
            User user=User.builder()
                    .name("wangwu777"+i)
                    .address("gangzou888"+i)
                    .age(30+i)
                    .interests("张楠 了咯 老大 999"+i)
                    .birthday(new Date())
                    .build();
            bulkRequest.add(new IndexRequest("lib3").source(JSONObject.toJSONString(user),XContentType.JSON));
        }

        BulkResponse responses =restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println(responses.status());
    }

    @Test
    public void search() throws Exception{
        SearchRequest request=new SearchRequest("lib3");

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("age",30));

        request.source(searchSourceBuilder);
        SearchResponse response =restHighLevelClient.search(request,RequestOptions.DEFAULT);

        System.out.println(response.status());
        Arrays.stream(response.getHits().getHits()).forEach(s ->{
            System.out.println(s.getSourceAsMap().toString());
        });

    }
}
