package pe.com.project2.ms.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project2.ms.domain.Product;
import pe.com.project2.ms.domain.ProductStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class ProductDao {
    @Id
    private String id;
    private String name;
    private String customerId;
    private LocalDateTime createdAt;
    private ProductStatus productStatus;

    public ProductDao(Product product) {
        id = product.getId();
        name = product.getName();
        customerId = product.getCustomerId();
        createdAt = product.getCreatedAt();
        productStatus = product.getProductStatus();
    }

    public Product toCustomersProductOverview() {
        return new Product(id, name, customerId, createdAt, productStatus);
    }
}
