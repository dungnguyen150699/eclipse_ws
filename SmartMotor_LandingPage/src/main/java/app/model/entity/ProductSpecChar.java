package vn.viettel.app.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.app.common.enums.Type;
import vn.viettel.app.model.AbstractModel;

import javax.persistence.*;

@Entity(name="ProductSpecChar")
@Table(name="product_spec_char")
@NoArgsConstructor
@Data
public class ProductSpecChar extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "order")
    private Integer order;

    @Column(name = "status")
    private Integer status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private LandingPageProduct landingPageProduct;
}
