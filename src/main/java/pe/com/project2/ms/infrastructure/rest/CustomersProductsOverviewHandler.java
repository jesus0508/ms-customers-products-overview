package pe.com.project2.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project2.ms.application.CustomersProductsOverviewService;
import pe.com.project2.ms.domain.Product;
import pe.com.project2.ms.shared.DateUtils;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomersProductsOverviewHandler {
    private final CustomersProductsOverviewService customersProductsOverviewService;

    public Mono<ServerResponse> getAllCustomersProductsOverview(ServerRequest request) {
        Flux<Product> customersProductsOverviewFlux = customersProductsOverviewService.findAll();
        return this.toServerResponse(customersProductsOverviewFlux, HttpStatus.OK);
    }

    public Mono<ServerResponse> getAllCustomersProductsOverviewByCustomerId(ServerRequest request) {
        Flux<Product> customersProductsOverviewFlux = request.queryParam("customerId")
                .map(customersProductsOverviewService::findByCustomerId)
                .orElseGet(Flux::empty);
        return this.toServerResponse(customersProductsOverviewFlux, HttpStatus.OK);
    }

    public Mono<ServerResponse> getAllCustomersProductsOverviewByCustomerIdAndStartDateAndEndDate(ServerRequest request) {
        LocalDateTime startDate = request.queryParam("startDate")
                .map(DateUtils::fromStringToStartLocalDateTime)
                .orElseGet(() -> LocalDateTime.MIN);
        LocalDateTime endDate = request.queryParam("endDate")
                .map(DateUtils::fromStringToEndLocalDateTime)
                .orElseGet(LocalDateTime::now);
        Flux<Product> customersProductsOverviewFlux = request.queryParam("customerId")
                .map(customerId -> customersProductsOverviewService.findByCustomerIdAndCreatedAtBetween(customerId, startDate, endDate))
                .orElseGet(Flux::empty);
        return this.toServerResponse(customersProductsOverviewFlux, HttpStatus.OK);
    }

    public Mono<ServerResponse> postCustomersProductsOverview(ServerRequest request) {
        return request.bodyToMono(Product.class)
                .map(customersProductsOverviewService::save)
                .flatMap(customersProductsOverview -> this.toServerResponse(customersProductsOverview, HttpStatus.CREATED));
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<Product> customer, HttpStatus status) {
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer, Product.class);
    }


}
