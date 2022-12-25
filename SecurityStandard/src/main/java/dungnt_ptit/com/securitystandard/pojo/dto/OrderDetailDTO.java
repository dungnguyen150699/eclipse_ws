package dungnt_ptit.com.securitystandard.pojo.dto;

import dungnt_ptit.com.securitystandard.pojo.entity.MapSupperClass;
import dungnt_ptit.com.securitystandard.pojo.entity.Order;
import dungnt_ptit.com.securitystandard.pojo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO extends MapSupperClass {

    private Long count;

    private Double price;

    private Order order;

    private ProductDTO product;
}
