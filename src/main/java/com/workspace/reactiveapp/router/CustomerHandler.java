package com.workspace.reactiveapp.router;


import com.workspace.reactiveapp.dao.CustomerDao;
import com.workspace.reactiveapp.dto.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private final CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> getAll(ServerRequest request){
     var customerFlux= customerDao.getAllCustomersEvent();
     return ServerResponse.ok().body(customerFlux,Customer.class);
    }

}
