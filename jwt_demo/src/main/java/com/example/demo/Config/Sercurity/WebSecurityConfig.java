package com.example.demo.Config.Sercurity;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.Config.Jwt.JwtAuthenticationFilter;
import com.example.demo.service.UserService;


import lombok.extern.log4j.Log4j2;

/*Điểm khác biệt ở đây là sự xuất hiện của JwtAuthenticationFilter.
 * Đây là một lớp Filter do tôi tự tạo ra. 
 * ( tôi ở đây là: https://viblo.asia/p/huong-dan-spring-security-jwt-json-web-token-hibernate-oOVlYGmoK8W)
 * 
 * @author DELL
 *
 */

@EnableWebSecurity
@Log4j2
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
            .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        // Set session management to stateless
        http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

        // Set unauthorized requests exception handler
    	http = http
    	        .exceptionHandling()
    	        .authenticationEntryPoint(
    	            (request, response, ex) -> {
    	              log.error("Unauthorized request - {}", ex.getMessage());
    	              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    	            }
    	        ).and();
    	
    	http.authorizeRequests()
                    .antMatchers("/api/login").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                    .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập

        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
