package dungnt_ptit.com.securitystandard.pojo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Setter
@Getter
@NoArgsConstructor
public class Product extends MapSupperClass{

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Long count;

    @Column(name = "img")
    @Lob
    private Byte[] img;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    // Không nên có mối quan hệ gì mới đúng dù product cấu thành nên OrderDetail
    // Ý là Cascade = none ấy
    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
