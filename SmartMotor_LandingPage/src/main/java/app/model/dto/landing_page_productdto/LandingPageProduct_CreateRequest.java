package vn.viettel.app.model.dto.landing_page_productdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import vn.viettel.app.common.enums.Type;
import vn.viettel.app.model.entity.ProductSpecChar;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LandingPageProduct_CreateRequest {

    private Integer id;

    private String name;

    @NotNull
    private MultipartFile image;

    @NotNull
    private MultipartFile technicalPhoto;

    private Long price;

    private Integer sort;

    @NotNull
    private Type type;

    private List<ProductSpecChar> productList;
}
