package pe.com.project2.ms.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project2.ms.application.CustomersProductsOverviewService;
import pe.com.project2.ms.domain.CustomersProductOverview;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomersProductsOverviewHandler {
    private final CustomersProductsOverviewService customersProductsOverviewService;

    public Mono<ServerResponse> getAllCustomersProductsOverview(ServerRequest request) {
        Flux<CustomersProductOverview> customersProductsOverviewFlux = customersProductsOverviewService.findAll();
        return this.toServerResponse(customersProductsOverviewFlux, HttpStatus.OK);
    }

    public Mono<ServerResponse> getAllCustomersProductsOverviewByCustomerId(ServerRequest request) {
        Flux<CustomersProductOverview> customersProductsOverviewFlux = request.queryParam("customerId")
                .map(customersProductsOverviewService::findByCustomerId)
                .orElseGet(Flux::empty);
        return this.toServerResponse(customersProductsOverviewFlux, HttpStatus.OK);
    }

    public Mono<ServerResponse> postCustomersProductsOverview(ServerRequest request) {
        return request.bodyToMono(CustomersProductOverview.class)
                .map(customersProductsOverviewService::save)
                .flatMap(customersProductsOverview -> this.toServerResponse(customersProductsOverview, HttpStatus.CREATED));
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<CustomersProductOverview> customer, HttpStatus status) {
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer, CustomersProductOverview.class);
    }

}
