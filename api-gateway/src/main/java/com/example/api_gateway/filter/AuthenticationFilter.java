package com.example.api_gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuration properties can go here if needed
    }

    @Autowired
    private RouteValidator validator;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return Mono.error(new RuntimeException("Missing Authorization Header"));
                }

                String authHeaderToken = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaderToken != null && authHeaderToken.startsWith("Bearer ")) {
                    authHeaderToken = authHeaderToken.substring(7);
                }

                try {
                    RestClient restClient = RestClient.create();
                    restClient.get().uri("http://auth-service:7079/api/validate/token?token=" + authHeaderToken).retrieve().body(Boolean.class);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("Invalid Access!! : " + e.getMessage());
                }
            }

            return chain.filter(exchange);
        };

    }
}