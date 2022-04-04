package pe.com.project2.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsCustomersProductsOverviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCustomersProductsOverviewApplication.class, args);
    }

}
