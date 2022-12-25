package com.example.demo.Config.Jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class exceptionJwt_AccessDenied  implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
			throws IOException, ServletException {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write("Access Denied! xxx");

	}
}