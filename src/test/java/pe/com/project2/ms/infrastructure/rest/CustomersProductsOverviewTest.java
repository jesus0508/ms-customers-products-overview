package pe.com.project2.ms.infrastructure.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.project2.ms.application.CustomersProductsOverviewService;
import pe.com.project2.ms.domain.Product;
import pe.com.project2.ms.domain.ProductStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebFluxTest
@ContextConfiguration(classes = {CustomersProductsOverviewHandler.class, CustomersProductsOverviewRouter.class})
@ActiveProfiles("local")
class CustomersProductsOverviewTest {
    private static final Map<String, Product> customersProductsOverview = new HashMap<>();
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private CustomersProductsOverviewService customersProductsOverviewService;

    @BeforeAll
    public static void setup() {
        customersProductsOverview.put("2222", new Product("2222", "Tarjeta de Credito", "1234", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE));
        customersProductsOverview.put("2223", new Product("2223", "Cuenta de ahorro", "1234", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE));
        customersProductsOverview.put("2224", new Product("2224", "Cuenta de ahorro", "4321", LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), ProductStatus.ACTIVE));
    }

    @Test
    void testGetAllCustomersProductsOverview() {
        final Flux<Product> customersProductsOverviewMono = Flux.fromStream(customersProductsOverview.values().stream());
        when(customersProductsOverviewService.findAll()).thenReturn(customersProductsOverviewMono);
        webTestClient.get().uri("/customers-products-overview")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .value(Assertions::assertNotNull)
                .value(c -> assertEquals(3, c.size()));
        verify(customersProductsOverviewService, times(1)).findAll();
    }

    @Test
    void testPostCustomersProductsOverview() {
        final Product product = customersProductsOverview.get("2222");
        final Mono<Product> customersProductOverviewMono = Mono.just(product);

        when(customersProductsOverviewService.save(product)).thenReturn(customersProductOverviewMono);

        webTestClient.post().uri("/customers-products-overview")
                .contentType(MediaType.APPLICATION_JSON)
                .body(customersProductOverviewMono, Product.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Product.class)
                .value(Assertions::assertNotNull)
                .value(customersProductOverviewCreated -> {
                    assertEquals("2222", customersProductOverviewCreated.getId());
                    assertEquals("1234", customersProductOverviewCreated.getCustomerId());
                    assertEquals("Tarjeta de Credito", customersProductOverviewCreated.getName());
                    assertEquals(LocalDateTime.of(2022, Month.MARCH, 29, 15, 13, 40), customersProductOverviewCreated.getCreatedAt());
                    assertEquals(ProductStatus.ACTIVE, customersProductOverviewCreated.getProductStatus());
                });
        verify(customersProductsOverviewService, times(1)).save(any());
    }

    @Test
    void testGetAllCustomersProductsOverviewByCustomerId() {
        final Product product1 = customersProductsOverview.get("2222");
        final Product product2 = customersProductsOverview.get("2223");

        final Flux<Product> customersProductOverviewFlux = Flux.just(product1, product2);

        when(customersProductsOverviewService.findByCustomerId("1234")).thenReturn(customersProductOverviewFlux);

        webTestClient.get().uri("/customers-products-overview?customerId=1234")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .value(Assertions::assertNotNull)
                .value(c -> assertEquals(2, c.size()));
        verify(customersProductsOverviewService, times(1)).findByCustomerId("1234");
    }
}
