package vn.viettel.app.model.dto.landing_page_productdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import vn.viettel.app.common.enums.Type;
import vn.viettel.app.model.entity.LandingPageProduct;
import vn.viettel.app.model.entity.ProductSpecChar;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LandingPageProduct_Reponse {

    private Integer id;

    private String name;

    private String image;

    private String technicalPhoto;

    private Long price;

    private Integer sort;

    private Type type;

    private List<ProductSpecChar> productList;

}
