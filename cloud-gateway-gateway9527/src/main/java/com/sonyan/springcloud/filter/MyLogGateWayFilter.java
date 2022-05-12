package com.sonyan.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author sonyan
 * @create 2022/4/19 13:58
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(">>>>>>>>>>> come in MyLogGateWayFilter: "+ new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        System.out.println("username: "+username);
        if (username == null || username.length() == 0){
            log.info("======用户用户为null，非法用户！！！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {

        //优先级顺序，值越小，优先级越高
        return 0;
    }
}
