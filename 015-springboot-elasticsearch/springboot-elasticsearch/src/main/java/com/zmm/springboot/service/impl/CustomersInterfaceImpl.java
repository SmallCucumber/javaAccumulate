package com.zmm.springboot.service.impl;

import com.zmm.springboot.modle.Customer;
import com.zmm.springboot.repository.CustomerRepository;
import com.zmm.springboot.service.CustomersInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersInterfaceImpl implements CustomersInterface {

    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CustomerRepository customerRepository;

   @Override
    public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
/*        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);

        // Function Score Query
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100));

        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

       logger.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<Customer> searchPageResults = customerRepository.search(searchQuery);
        return searchPageResults.getContent();
        */
        return null;
    }
}
