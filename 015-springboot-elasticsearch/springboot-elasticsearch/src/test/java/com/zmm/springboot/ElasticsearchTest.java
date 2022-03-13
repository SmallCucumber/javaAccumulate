package com.zmm.springboot;

import com.zmm.springboot.modle.Customer;
import com.zmm.springboot.repository.CustomerRepository;
import net.minidev.json.JSONUtil;
import org.assertj.core.util.DateUtil;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.InternalAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void save(){
        Customer customer=new Customer("李四","北京",55);
        customerRepository.save(customer);
    }


    @Test
    public void saveAll(){
        List<Customer> customers=new ArrayList<>();
        Customer customer1=new Customer("张三1","深圳1",22);
        Customer customer2=new Customer("张三2","深圳2",33);
        Customer customer3=new Customer("张三3","深圳3",44);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customerRepository.saveAll(customers);
    }

    /**
     * 测试普通查询
     */
    @Test
    public void select() {
        Iterable<Customer> customers=customerRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));

        customers.forEach(customer -> {
            System.out.println(customer.toString());
        });

    }


    @Test
    public void select2() {
        Iterable<Customer> customers=customerRepository.findByAddress("北京");
        customers.forEach(customer -> {
            System.out.println(customer.toString());
        });

    }

    @Test
    public void select3() {
        Customer customer=customerRepository.findByUserName("张三");
        System.out.println(customer.toString());
    }

    @Test
    public void select4() {
        List<Customer> customers=customerRepository.findByAgeBetween(20,40);
        customers.forEach(customer -> {
            System.out.println(customer.toString());
        });
    }


    /**
     * 高级查询
     */
    @Test
    public void advanceSelect() {
        // QueryBuilders 提供了很多静态方法，可以实现大部分查询条件的封装
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("userName", "张三");
        System.out.println("【queryBuilder】= "+ queryBuilder.toString());
        customerRepository.search(queryBuilder)
                .forEach(person -> System.out.println("【person】= "+ person));
    }

    /**
     * 自定义高级查询
     */
    @Test
    public void customAdvanceSelect() {
        // 构造查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("address", "深圳"));
        // 排序条件
        queryBuilder.withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));
        // 分页条件
        queryBuilder.withPageable(PageRequest.of(0, 2));
        Page<Customer> people = customerRepository.search(queryBuilder.build());
        System.out.println("【people】总条数 = {}"+ people.getTotalElements());
        System.out.println("【people】总页数 = {}"+ people.getTotalPages());
        people.forEach(person -> System.out.println(person.toString()));
    }


    /**
     * 测试聚合，测试平均年龄
     */
    @Test
    public void agg() {
        // 构造查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));

        // 平均年龄
        queryBuilder.addAggregation(AggregationBuilders.avg("avg").field("age"));

        //log.info("【queryBuilder】= {}", JSONUtil.toJsonStr(queryBuilder.build()));

        AggregatedPage<Customer> people = (AggregatedPage<Customer>) customerRepository.search(queryBuilder.build());
        Map<String, Aggregation> aggregationMap= people.getAggregations().getAsMap();
        List<Aggregation> aggregationList=people.getAggregations().asList();
        double avgAge = ((ParsedAvg) people.getAggregation("avg")).getValue();
        System.out.println("【avgAge】= {}"+avgAge);
    }
}
