package com.zmm.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

/*    @Bean
    @Primary
    public DataSource dataSource(){

        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/152_db_order?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root123");

        DataSourceProxy dataSourceProxy=new DataSourceProxy(druidDataSource);
        return dataSourceProxy;
    }*/
}
