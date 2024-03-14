package com.workspace.reactiveapp.controller;


import com.workspace.reactiveapp.dto.Customer;
import com.workspace.reactiveapp.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @RequestMapping("/stream")
    public Flux<Customer> getAllCustomers(){
        return customerService.getAllStream();
    }

    @RequestMapping(value="/stream-event",produces = "text/event-stream")
    public Flux<Customer> getAllCustomersEvent(){
        return customerService.getAllStream();
    }
}
