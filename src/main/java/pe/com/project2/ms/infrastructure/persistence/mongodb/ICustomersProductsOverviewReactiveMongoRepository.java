package pe.com.project2.ms.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project2.ms.infrastructure.persistence.model.CustomersProductOverviewDao;
import reactor.core.publisher.Flux;

public interface ICustomersProductsOverviewReactiveMongoRepository extends ReactiveMongoRepository<CustomersProductOverviewDao, String> {
    Flux<CustomersProductOverviewDao> findByCustomerId(String customerId);
}
