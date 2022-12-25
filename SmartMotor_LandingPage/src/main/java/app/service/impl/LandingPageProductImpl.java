package vn.viettel.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.viettel.app.common.modelmapper.CommonMapper;
import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProductDTO;
import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProduct_Reponse;
import vn.viettel.app.model.entity.LandingPageProduct;
import vn.viettel.app.service.MessageService;
import vn.viettel.app.repository.LandingPageProductRepository;
import vn.viettel.app.service.LandingPageProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandingPageProductImpl implements LandingPageProductService {

    private final MessageService messageService;

    private final LandingPageProductRepository repository;

    @Override
    public List<LandingPageProduct_Reponse> findAll() {
        List<LandingPageProductDTO> listDTO = CommonMapper.toList(repository.findAll(),LandingPageProductDTO.class);
        List<LandingPageProduct_Reponse> listReponse = new ArrayList<>();
        listDTO.stream().forEach(dto -> {
            try {
                listReponse.add(LandingPageProductDTO.buildReponse(dto));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return listReponse;
    }

    @Override
    public List<LandingPageProductDTO> findByConditions() {
        return null;
    }

    @Override
    public LandingPageProductDTO create(LandingPageProductDTO landingPageProductDTO) {
        LandingPageProduct landingPageProduct = CommonMapper.map(landingPageProductDTO,LandingPageProduct.class);
        landingPageProduct.setId(null);
        return CommonMapper.map(repository.save(landingPageProduct),LandingPageProductDTO.class);
    }
}
