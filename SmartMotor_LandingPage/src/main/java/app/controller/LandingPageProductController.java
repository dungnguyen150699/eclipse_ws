package vn.viettel.app.controller;

import lombok.Builder;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.viettel.app.config.exception.GlobalExceptionHandler;
import vn.viettel.app.config.exception.ValidateException;
import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProductDTO;
import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProduct_CreateRequest;
import vn.viettel.app.model.dto.landing_page_productdto.LandingPageProduct_Reponse;
import vn.viettel.app.service.MessageService;
import vn.viettel.app.service.LandingPageProductService;
import vn.viettel.app.utils.DataUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/landing-page-product")
@Builder
public class LandingPageProductController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private LandingPageProductService landingPageProductService;

    @GetMapping("/find-all")
    public ResponseEntity findAll() throws ValidateException {
        return ResponseEntity.ok(GlobalExceptionHandler.ApiResponseData.<List<LandingPageProduct_Reponse>>builder()
                .errorCode(DataUtils.safeToString(HttpStatus.OK.value()))
                .messages(Arrays.asList(messageService.getMessage("error.code.success")))
                .data(landingPageProductService.findAll())
                .build());
    }

    @PostMapping("/find-by-conditions")
    public ResponseEntity findByConditions() throws ValidateException {
        return ResponseEntity.ok(GlobalExceptionHandler.ApiResponseData.<List<LandingPageProduct_Reponse>>builder()
                .errorCode(DataUtils.safeToString(HttpStatus.OK.value()))
                .messages(Arrays.asList(messageService.getMessage("error.code.success")))
                .data(landingPageProductService.findAll())
                .build());
    }


    @PostMapping(value="/create", consumes = {"multipart/form-data"})
    public ResponseEntity create(@ParameterObject @Valid LandingPageProduct_CreateRequest createRequest, BindingResult bindingResult) throws ValidateException, IOException {
        if (bindingResult.hasErrors()){
            throw new ValidateException(messageService.getMessage("error.code.dto.null"));
        }
        // Build to DTO, Build Reponse
        LandingPageProductDTO dto = LandingPageProductDTO.buildCreateDTO(createRequest);
        LandingPageProduct_Reponse reponse = LandingPageProductDTO.buildReponse(landingPageProductService.create(dto));

        return ResponseEntity.ok(GlobalExceptionHandler.ApiResponseData.<LandingPageProduct_Reponse>builder()
                .errorCode(DataUtils.safeToString(HttpStatus.OK.value()))
                .messages(Arrays.asList(messageService.getMessage("error.code.success")))
                .data(reponse)
                .build());
    }
}
