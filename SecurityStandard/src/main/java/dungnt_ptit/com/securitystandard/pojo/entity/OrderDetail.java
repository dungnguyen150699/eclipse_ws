package dungnt_ptit.com.securitystandard.pojo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
@Setter
@Getter
@NoArgsConstructor
public class OrderDetail extends MapSupperClass{

    @Column(name = "count")
    private Long count;

    @Column(name = "price")
    private Double price;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
}
