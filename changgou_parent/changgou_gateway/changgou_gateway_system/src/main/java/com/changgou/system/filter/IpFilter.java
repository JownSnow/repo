package com.changgou.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

@Component
public class IpFilter implements GlobalFilter, Ordered {

    //拦截
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //拦截ip黑名单
        //业务实现
        InetSocketAddress address = exchange.getRequest().getRemoteAddress();

        System.out.println("第一次拦截_用户的ip: "+address.getHostName());

        //放行
        return chain.filter(exchange);
    }


    //拦截顺序
    @Override
    public int getOrder() {
        return 1;   //第一个执行的过滤器
    }
}
