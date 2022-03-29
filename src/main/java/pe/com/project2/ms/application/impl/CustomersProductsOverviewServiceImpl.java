package pe.com.project2.ms.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.project2.ms.application.CustomersProductsOverviewService;
import pe.com.project2.ms.application.persistence.CustomersProductsOverviewRepository;
import pe.com.project2.ms.domain.CustomersProductOverview;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomersProductsOverviewServiceImpl implements CustomersProductsOverviewService {

    private final CustomersProductsOverviewRepository customersProductsOverviewRepository;

    @Override
    public Flux<CustomersProductOverview> findAll() {
        return customersProductsOverviewRepository.findAll();
    }

    @Override
    public Flux<CustomersProductOverview> findByCustomerId(String customerId) {
        return customersProductsOverviewRepository.findByCustomerId(customerId);
    }

    @Override
    public Mono<CustomersProductOverview> save(CustomersProductOverview customersProductOverview) {
        return customersProductsOverviewRepository.save(customersProductOverview);
    }
}
