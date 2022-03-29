package pe.com.project2.ms.application.persistence;

import pe.com.project2.ms.domain.CustomersProductOverview;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomersProductsOverviewRepository {
    Flux<CustomersProductOverview> findAll();

    Flux<CustomersProductOverview> findByCustomerId(String customerId);

    Mono<CustomersProductOverview> save(CustomersProductOverview customersProductOverview);
}
