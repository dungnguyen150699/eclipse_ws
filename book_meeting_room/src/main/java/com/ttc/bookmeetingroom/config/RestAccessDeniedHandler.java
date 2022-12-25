package com.ttc.bookmeetingroom.config;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ResponseBodyDto<String> res = new ResponseBodyDto<>(ResponseCodeEnum.R_403, "Forbidden");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, res);
        out.flush();
    }
}
