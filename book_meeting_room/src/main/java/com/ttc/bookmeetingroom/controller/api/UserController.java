package com.ttc.bookmeetingroom.controller.api;

import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import com.ttc.bookmeetingroom.dto.TokenResetPassDTO;
import com.ttc.bookmeetingroom.model.User;
import com.ttc.bookmeetingroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@CrossOrigin(origins= {"*"})
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("change-passwords")
    public ResponseEntity<ResponseBodyDto<TokenResetPassDTO>> changePass(
            @RequestBody TokenResetPassDTO tokenResetPassDTO
    ) {
        ResponseBodyDto<TokenResetPassDTO> res;
        if (userService.changePassword(tokenResetPassDTO)) {
            res = new ResponseBodyDto<>(ResponseCodeEnum.R_200, "OK");
        } else {
            res = new ResponseBodyDto<>(ResponseCodeEnum.R_400, "invalid email or password");
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
