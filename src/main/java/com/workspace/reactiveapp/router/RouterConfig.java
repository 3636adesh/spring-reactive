package com.workspace.reactiveapp.router;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final CustomerHandler customerHandler;
    private final CustomerHandlerStream customerHandlerStream;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route(GET("/router/customer/stream-event"), customerHandler::getAll)
                .andRoute(GET("/router/customer/stream"), customerHandlerStream::getCustomers)
                .andRoute(GET("/router/customer/{input}"), customerHandlerStream::getCustomersInput)
                .andRoute(POST("/router/customer"), customerHandlerStream::getCustomersPost);
    }
}
