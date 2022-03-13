package com.zmm.springcloud.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-10 14:25
 **/
@Configuration
public class ConfigBean {

    /**
     * 一直以为Spring对注解形式的bean的名字的默认处理就是将首字母小写，再拼接后面的字符，但今天看来不是这样的。
     * 回来翻了一下原码，原来还有另外的一个特殊处理：当类的名字是以两个或以上的大写字母开头的话，bean的名字会与类名保持一致
     *
     * 将 RestTemplate 注入到容器中
     * 在客户端使用 RestTemplate 请求服务端时，开启负载均衡（Ribbon）
     *
     *
     * @Bean 作用于方法上创建bean
     * 如果不指定bean名称，bean名称的默认规则是 “方法名” + "首字母小写"的配置方式
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(2000);
        httpRequestFactory.setReadTimeout(2000);

        return new RestTemplate(httpRequestFactory);
    }


    /**
     * RandomRule 为随机策略
     * @return
     */
    @Bean
    public IRule myRule() {

        //return  new RandomRule();
        return new MyRandomRule();
    }
}
