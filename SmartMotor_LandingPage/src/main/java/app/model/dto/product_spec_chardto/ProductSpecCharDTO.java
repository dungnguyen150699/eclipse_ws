package vn.viettel.app.model.dto.product_spec_chardto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.app.common.enums.Type;
import vn.viettel.app.model.AbstractModel;
import vn.viettel.app.model.entity.LandingPageProduct;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSpecCharDTO extends AbstractModel {

    private Integer id;

    private String key;

    private String value;

    private Integer order;

    private Integer status;

    private Type type ;

    private LandingPageProduct landingPageProduct;
}
