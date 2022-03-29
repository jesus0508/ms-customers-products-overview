package pe.com.project2.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersProductOverview {
    private String id;
    private String customerId;
    private Product product;
}
