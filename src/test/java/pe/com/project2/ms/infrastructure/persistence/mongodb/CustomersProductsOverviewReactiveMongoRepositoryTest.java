package pe.com.project2.ms.infrastructure.persistence.mongodb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import pe.com.project2.ms.domain.ProductStatus;
import pe.com.project2.ms.infrastructure.persistence.model.ProductDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@DataMongoTest
@ActiveProfiles("local")
class CustomersProductsOverviewReactiveMongoRepositoryTest {

    private static final List<ProductDao> customersProductsOverviewDao = new ArrayList<>();
    @Autowired
    private ICustomersProductsOverviewReactiveMongoRepository customersProductsOverviewReactiveMongoRepository;
    private Mono<Void> deleteAllMono;

    @BeforeAll
    public static void init() {
        customersProductsOverviewDao.add(new ProductDao("2222", "Tarjeta de Credito", "1234", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE));
        customersProductsOverviewDao.add(new ProductDao("2223", "Cuenta de ahorro", "1234", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE));
    }

    @BeforeEach
    public void setup() {
        deleteAllMono = customersProductsOverviewReactiveMongoRepository.deleteAll();
    }

    @Test
    void testFindByCustomerId() {
        final String customerId = "1234";
        final Flux<ProductDao> customersProductOverviewDaoFlux = deleteAllMono
                .thenMany(customersProductsOverviewReactiveMongoRepository.saveAll(customersProductsOverviewDao))
                .thenMany(customersProductsOverviewReactiveMongoRepository.findByCustomerId(customerId));
        StepVerifier.create(customersProductOverviewDaoFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testFindByNonExistingCustomerId() {
        final String customerId = "4321";
        final Flux<ProductDao> customersProductOverviewDaoFlux = deleteAllMono
                .thenMany(customersProductsOverviewReactiveMongoRepository.saveAll(customersProductsOverviewDao))
                .thenMany(customersProductsOverviewReactiveMongoRepository.findByCustomerId(customerId));
        StepVerifier.create(customersProductOverviewDaoFlux)
                .expectNextCount(0)
                .verifyComplete();
    }
}
