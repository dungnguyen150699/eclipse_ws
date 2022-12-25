package vn.viettel.app.model.dto.landing_page_productdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.viettel.app.common.enums.Type;
import vn.viettel.app.model.AbstractModel;
import vn.viettel.app.model.entity.ProductSpecChar;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LandingPageProductDTO extends AbstractModel {

    private Integer id;

    private String name;

    @NotNull
    private byte[] image;

    @NotNull
    private byte[] technicalPhoto;

    private Long price;

    private Integer sort;

    @NotNull
    private Type type;

    private List<ProductSpecChar> productList;

    @JsonIgnore
    public static LandingPageProductDTO buildCreateDTO(LandingPageProduct_CreateRequest request) throws IOException {
        LandingPageProductDTO dto = new LandingPageProductDTO();
        dto.setName(request.getName());
        dto.setImage(request.getImage().getBytes());
        dto.setTechnicalPhoto(request.getTechnicalPhoto().getBytes());
        dto.setPrice(request.getPrice());
        dto.setSort(request.getSort());
        dto.setType(request.getType());
        dto.setProductList(request.getProductList());
        return dto;
    }

    @JsonIgnore
    public static LandingPageProduct_Reponse buildReponse (LandingPageProductDTO dto) throws IOException {
        LandingPageProduct_Reponse reponse = new LandingPageProduct_Reponse();
        reponse.setId(dto.getId());
        reponse.setName(dto.getName());
        if(dto.getImage() != null)        reponse.setImage(Base64.getEncoder().encodeToString(dto.getImage()));
        if(dto.getTechnicalPhoto() != null)     reponse.setTechnicalPhoto(Base64.getEncoder().encodeToString(dto.getTechnicalPhoto()));
        reponse.setPrice(dto.getPrice());
        reponse.setSort(dto.getSort());
        reponse.setType(dto.getType());
        reponse.setProductList(dto.getProductList());
        return reponse;
    }



}
