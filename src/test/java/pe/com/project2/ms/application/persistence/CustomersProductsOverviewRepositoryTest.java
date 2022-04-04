package pe.com.project2.ms.application.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pe.com.project2.ms.domain.Product;
import pe.com.project2.ms.domain.ProductStatus;
import pe.com.project2.ms.infrastructure.persistence.model.ProductDao;
import pe.com.project2.ms.infrastructure.persistence.mongodb.ICustomersProductsOverviewReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("local")
public class CustomersProductsOverviewRepositoryTest {
    @Autowired
    private CustomersProductsOverviewRepository customersProductsOverviewRepository;
    @MockBean
    private ICustomersProductsOverviewReactiveMongoRepository customersProductsOverviewReactiveMongoRepository;
    private ProductDao productDao;

    @BeforeEach
    public void setup() {
        productDao = new ProductDao("2222", "Tarjeta de Credito", "1234", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE);
    }

    @Test
    void testSave() {
        Product product = new Product("2222", "Tarjeta de Credito", "1234", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE);

        when(customersProductsOverviewReactiveMongoRepository.save(productDao)).thenReturn(Mono.just(productDao));

        Mono<Product> monoCustomer = customersProductsOverviewRepository.save(product);

        StepVerifier.create(monoCustomer)
                .consumeNextWith(c -> {
                    assertInstanceOf(Product.class, c);
                    assertEquals(productDao.getId(), c.getId());
                }).verifyComplete();
    }

    @Test
    void testFindById() {
        when(customersProductsOverviewReactiveMongoRepository.findByCustomerId("1234")).thenReturn(Flux.just(productDao));

        StepVerifier.create(customersProductsOverviewRepository.findByCustomerId("1234"))
                .consumeNextWith(found -> {
                    assertInstanceOf(Product.class, found);
                    assertEquals("1234", found.getCustomerId());
                }).verifyComplete();
    }

}
