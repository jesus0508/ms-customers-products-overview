package pe.com.project2.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.project2.ms.application.persistence.CustomersProductsOverviewRepository;
import pe.com.project2.ms.domain.CustomersProductOverview;
import pe.com.project2.ms.infrastructure.persistence.model.CustomersProductOverviewDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CustomersProductsOverviewReactiveMongoRepository implements CustomersProductsOverviewRepository {

    private final ICustomersProductsOverviewReactiveMongoRepository customersProductsOverviewReactiveMongoRepository;

    @Override
    public Flux<CustomersProductOverview> findAll() {
        return customersProductsOverviewReactiveMongoRepository
                .findAll()
                .map(CustomersProductOverviewDao::toCustomersProductOverview);
    }

    @Override
    public Flux<CustomersProductOverview> findByCustomerId(String customerId) {
        return customersProductsOverviewReactiveMongoRepository
                .findByCustomerId(customerId)
                .map(CustomersProductOverviewDao::toCustomersProductOverview);
    }

    @Override
    public Mono<CustomersProductOverview> save(CustomersProductOverview customersProductOverview) {
        return customersProductsOverviewReactiveMongoRepository
                .save(new CustomersProductOverviewDao(customersProductOverview))
                .map(CustomersProductOverviewDao::toCustomersProductOverview);
    }
}
