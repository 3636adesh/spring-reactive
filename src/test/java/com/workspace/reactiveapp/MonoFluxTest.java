package com.workspace.reactiveapp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
       Mono<?> monoString= Mono.just("ABC")  // mono store only one value
               .then(Mono.error(new RuntimeException("Exception Occurred")))
               .log();
//        monoString.subscribe(System.out::println);
        monoString.subscribe(System.out::println,e-> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
       Flux<String> fluxString= Flux.just("Spring boot","Hibernate","Microservice","Flux")
               .concatWithValues("MongoDB")
               .concatWith(Flux.error(new RuntimeException("Exception occurred.")))
               .concatWithValues("Cloud")   // not subscribe
               .log();
       fluxString.subscribe(System.out::println);
    }
}
