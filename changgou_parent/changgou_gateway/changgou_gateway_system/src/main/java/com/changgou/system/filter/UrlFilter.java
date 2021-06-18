package com.changgou.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UrlFilter implements GlobalFilter, Ordered {

    //拦截
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //特定地址的拦截
        //业务实现
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();

        System.out.println("第二次拦截_用户的url: "+url);

        //放行
        return chain.filter(exchange);
    }


    //拦截顺序
    @Override
    public int getOrder() {
        return 2;   //第二个执行的过滤器
    }
}
