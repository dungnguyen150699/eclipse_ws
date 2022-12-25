package vn.viettel.app.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.app.common.enums.Type;
import vn.viettel.app.model.AbstractModel;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "LandingPageProduct")
@Table(name="landing_page_product")
public class LandingPageProduct extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name = "technical_photo")
    @Lob
    private byte[] technicalPhoto;

    @Column(name = "price")
    private Long price;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "landingPageProduct",cascade = CascadeType.ALL)
    private List<ProductSpecChar> productList;

}
