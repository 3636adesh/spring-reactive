package com.workspace.reactiveapp.router;

import com.workspace.reactiveapp.dao.CustomerDao;
import com.workspace.reactiveapp.dto.Customer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CustomerHandlerStream {

    private final CustomerDao customerDao;

    public CustomerHandlerStream(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        var customerFlux = customerDao.getAllCustomersEvent();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> getCustomersInput(ServerRequest request) {
        Integer input = Integer.parseInt(request.pathVariable("input"));
        var customerFlux = customerDao.getAllCustomersEvent();
        var customerMono = customerFlux.filter(customer -> Objects.equals(customer.getId(), input)).next();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> getCustomersPost(ServerRequest request) {
        Mono<Customer> customerMono=request.bodyToMono(Customer.class);
        Mono<String> stringMono=customerMono.map(customer -> customer.getId()+":"+customer.getName());
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(stringMono,String.class);
    }
}
