package pe.com.project2.ms.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project2.ms.domain.CustomersProductOverview;
import pe.com.project2.ms.domain.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("customersProductOverviewDao")
public class CustomersProductOverviewDao {
    @Id
    private String id;
    private String customerId;
    private Product product;

    public CustomersProductOverviewDao(CustomersProductOverview customersProductOverview) {
        id = customersProductOverview.getId();
        customerId = customersProductOverview.getCustomerId();
        product = customersProductOverview.getProduct();
    }

    public CustomersProductOverview toCustomersProductOverview() {
        return new CustomersProductOverview(id, customerId, product);
    }
}
