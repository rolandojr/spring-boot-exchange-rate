package com.example.mibanco.exchangerate.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Order(1)
public class ExampleWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("Add header {} into response", "first-filter");
        exchange.getResponse()
                .getHeaders().add("first-filter", "web-filter-test");
        return chain.filter(exchange);
    }
}
