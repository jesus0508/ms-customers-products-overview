package pe.com.project2.ms.application.persistence;

import pe.com.project2.ms.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface CustomersProductsOverviewRepository {
    Flux<Product> findAll();

    Flux<Product> findByCustomerId(String customerId);

    Flux<Product> findByCustomerIdAndCreatedAtBetween(String customerId, LocalDateTime startDate, LocalDateTime endDate);

    Mono<Product> save(Product product);
}
