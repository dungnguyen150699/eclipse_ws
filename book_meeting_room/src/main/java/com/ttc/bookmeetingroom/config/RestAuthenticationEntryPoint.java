package com.ttc.bookmeetingroom.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttc.bookmeetingroom.common.enums.ResponseCodeEnum;
import com.ttc.bookmeetingroom.controller.response.ResponseBodyDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		ResponseBodyDto<String> res = new ResponseBodyDto<>(ResponseCodeEnum.R_401, "Unauthorised");
		OutputStream out = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(out, res);
		out.flush();
	}

}