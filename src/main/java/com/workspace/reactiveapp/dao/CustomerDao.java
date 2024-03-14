package com.workspace.reactiveapp.dao;

import com.workspace.reactiveapp.dto.Customer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAll(){
       return IntStream.rangeClosed(1,50)
               .peek(CustomerDao::sleepExecution)
               .peek(i-> System.out.println("processing count "+i))
               .mapToObj(i-> new Customer(i,"Customer"+i)).toList();
    }

    public Flux<Customer> getAllCustomers(){
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println("processing count "+i))
                .map(i-> new Customer(i,"Customer"+i));
    }

    public Flux<Customer> getAllCustomersEvent(){
        return Flux.range(1,50)
                .doOnNext(i-> System.out.println("processing count "+i))
                .map(i-> new Customer(i,"Customer"+i));
    }


}
