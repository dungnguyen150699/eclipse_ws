package vn.viettel.app.service;

import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProductDTO;
import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProduct_Reponse;

import java.util.List;

public interface LandingPageProductService {

    public List<LandingPageProduct_Reponse> findAll();

    public List<LandingPageProductDTO> findByConditions();

    public LandingPageProductDTO create(LandingPageProductDTO landingPageProductDTO);

}
