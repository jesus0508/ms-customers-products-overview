package pe.com.project2.ms.application;

import pe.com.project2.ms.domain.CustomersProductOverview;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomersProductsOverviewService {
    Flux<CustomersProductOverview> findAll();

    Flux<CustomersProductOverview> findByCustomerId(String customerId);

    Mono<CustomersProductOverview> save(CustomersProductOverview customersProductOverview);
}
