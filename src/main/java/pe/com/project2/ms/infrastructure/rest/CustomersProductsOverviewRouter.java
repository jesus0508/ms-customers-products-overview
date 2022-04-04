package pe.com.project2.ms.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomersProductsOverviewRouter {

    private static final String CUSTOMERS_PRODUCTS_OVERVIEW = "/customers-products-overview";

    @Bean
    public RouterFunction<ServerResponse> routes(CustomersProductsOverviewHandler customersProductsOverviewHandler) {
        return route(GET(CUSTOMERS_PRODUCTS_OVERVIEW).and(queryParam("customerId", c -> true)).and(queryParam("startDate", s -> true)).and(queryParam("endDate", e -> true)),
                customersProductsOverviewHandler::getAllCustomersProductsOverviewByCustomerIdAndStartDateAndEndDate)
                .andRoute(GET(CUSTOMERS_PRODUCTS_OVERVIEW).and(queryParam("customerId", t -> true)), customersProductsOverviewHandler::getAllCustomersProductsOverviewByCustomerId)
                .andRoute(GET(CUSTOMERS_PRODUCTS_OVERVIEW), customersProductsOverviewHandler::getAllCustomersProductsOverview)
                .andRoute(POST(CUSTOMERS_PRODUCTS_OVERVIEW).and(accept(APPLICATION_JSON)), customersProductsOverviewHandler::postCustomersProductsOverview);
    }

}
