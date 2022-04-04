package pe.com.project2.ms.infrastructure.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project2.ms.domain.Product;
import pe.com.project2.ms.infrastructure.persistence.model.ProductDao;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface ICustomersProductsOverviewReactiveMongoRepository extends ReactiveMongoRepository<ProductDao, String> {
    Flux<ProductDao> findByCustomerId(String customerId);

    Flux<Product> findByCustomerIdAndCreatedAtBetween(String customerId, LocalDateTime startDate, LocalDateTime endDate);
}
