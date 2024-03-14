package com.workspace.reactiveapp.service;


import com.workspace.reactiveapp.dto.AppUtils;
import com.workspace.reactiveapp.dto.ProductDto;
import com.workspace.reactiveapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<ProductDto> getAll() {
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> save(Mono<ProductDto> productDto) {
        return productDto.map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);

    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<ProductDto> getById(String id) {
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }


    public Flux<ProductDto> getProductRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }


}
