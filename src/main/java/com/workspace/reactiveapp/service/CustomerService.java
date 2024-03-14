package com.workspace.reactiveapp.service;


import com.workspace.reactiveapp.dao.CustomerDao;
import com.workspace.reactiveapp.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    public List<Customer> getAll(){
        long start = System.currentTimeMillis();
        var customers = customerDao.getAll();
        long end =System.currentTimeMillis();
        System.out.println(end-start);
        return customers;
    }

    public Flux<Customer> getAllStream(){
        long start = System.currentTimeMillis();
        var customers = customerDao.getAllCustomers();
        long end =System.currentTimeMillis();
        System.out.println(end-start);
        return customers;
    }
}
