package pe.com.project2.ms.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.project2.ms.application.CustomersProductsOverviewService;
import pe.com.project2.ms.application.persistence.CustomersProductsOverviewRepository;
import pe.com.project2.ms.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomersProductsOverviewServiceImpl implements CustomersProductsOverviewService {

    private final CustomersProductsOverviewRepository customersProductsOverviewRepository;

    @Override
    public Flux<Product> findAll() {
        return customersProductsOverviewRepository.findAll();
    }

    @Override
    public Flux<Product> findByCustomerId(String customerId) {
        return customersProductsOverviewRepository.findByCustomerId(customerId);
    }

    @Override
    public Mono<Product> save(Product product) {
        return customersProductsOverviewRepository.save(product);
    }

    @Override
    public Flux<Product> findByCustomerIdAndCreatedAtBetween(String customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return customersProductsOverviewRepository.findByCustomerIdAndCreatedAtBetween(customerId, startDate, endDate);
    }
}
