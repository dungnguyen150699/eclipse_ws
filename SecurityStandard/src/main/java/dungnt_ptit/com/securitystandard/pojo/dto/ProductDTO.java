package dungnt_ptit.com.securitystandard.pojo.dto;

import dungnt_ptit.com.securitystandard.pojo.entity.Category;
import dungnt_ptit.com.securitystandard.pojo.entity.MapSupperClass;
import dungnt_ptit.com.securitystandard.pojo.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends MapSupperClass {

    private String name;

    private Long count;

    private String imgBase64;

    private Category category;

    private List<OrderDetailDTO> orderDetails;

}
