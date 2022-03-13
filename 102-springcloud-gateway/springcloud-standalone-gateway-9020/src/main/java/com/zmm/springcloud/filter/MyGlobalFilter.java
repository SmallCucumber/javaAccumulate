package com.zmm.springcloud.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-10 11:40
 **/
@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("进入自定义的全局过滤器 MyGlobalFilter" + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null) {

            log.info("参数 uname 不能为 null！");

            ServerHttpResponse response =exchange.getResponse();

            Map<String,Object> returnMap=new HashMap<>();
            returnMap.put("status", -1);
            returnMap.put("data", "uname不能为空");

            byte[] bits=JSONObject.toJSONString(returnMap).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer=response.bufferFactory().wrap(bits);
            //exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //指定编码，否则在浏览器中会中文乱码
            //response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
