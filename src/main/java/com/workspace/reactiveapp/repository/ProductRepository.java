package com.workspace.reactiveapp.repository;

import com.workspace.reactiveapp.dto.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>{
    <T> Flux<T> findByPriceBetween(Range<Double> closed);
}
