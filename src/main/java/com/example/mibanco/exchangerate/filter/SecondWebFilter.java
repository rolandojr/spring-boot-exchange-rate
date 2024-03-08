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
@Order(2)
public class SecondWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("Add header {} into response", "second-filter");
        exchange.getResponse()
                .getHeaders().add("second-filter", "new-web-filter");
        return chain.filter(exchange);

    }
}
