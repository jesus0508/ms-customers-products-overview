package pe.com.project2.ms.infrastructure.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pe.com.project2.ms.application.persistence.CustomersProductsOverviewRepository;
import pe.com.project2.ms.domain.Product;
import pe.com.project2.ms.infrastructure.persistence.model.ProductDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class CustomersProductsOverviewReactiveMongoRepository implements CustomersProductsOverviewRepository {

    private final ICustomersProductsOverviewReactiveMongoRepository customersProductsOverviewReactiveMongoRepository;

    @Override
    public Flux<Product> findAll() {
        return customersProductsOverviewReactiveMongoRepository
                .findAll()
                .map(ProductDao::toCustomersProductOverview);
    }

    @Override
    public Flux<Product> findByCustomerId(String customerId) {
        return customersProductsOverviewReactiveMongoRepository
                .findByCustomerId(customerId)
                .map(ProductDao::toCustomersProductOverview);
    }

    @Override
    public Flux<Product> findByCustomerIdAndCreatedAtBetween(String customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return customersProductsOverviewReactiveMongoRepository.findByCustomerIdAndCreatedAtBetween(customerId, startDate, endDate);
    }

    @Override
    public Mono<Product> save(Product product) {
        return customersProductsOverviewReactiveMongoRepository
                .save(new ProductDao(product))
                .map(ProductDao::toCustomersProductOverview);
    }
}
