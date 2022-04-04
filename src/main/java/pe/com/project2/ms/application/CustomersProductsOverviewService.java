package pe.com.project2.ms.application;

import pe.com.project2.ms.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface CustomersProductsOverviewService {
    Flux<Product> findAll();

    Flux<Product> findByCustomerId(String customerId);

    Mono<Product> save(Product product);

    Flux<Product> findByCustomerIdAndCreatedAtBetween(String customerId, LocalDateTime startDate, LocalDateTime endDate);
}
