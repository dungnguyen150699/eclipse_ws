package com.example.demo.Config.Sercurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity // Nhớ phải đủ 2 annotation để Spring biết là m đang cấu hình Sercurity
// thực tế ko thêm thì nó sẽ quét Component và tìm ra thôi nhưng ko nên làm thế
public class ConfigSercurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

//	@PostConstruct
//	@Autowired
//	private void init(UserService userService){
//		this.userService = userService;
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Không pass
			//	return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

//// Cách 1 Đang bị lỗi do cirle bean
//	@Autowired // Cái này là đang @Autowrired đến thằng bean AuthenticationManagerBuilder là đầu vào
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.authenticationProvider(authenticationProvider());
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userService);// Cả 2 thằng này đều phải set
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}

// Cách 2
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()
			.antMatchers("/admin/createAdmin","/register","/login","/doRegister","/css/**","**/js/**","**/images/**","/resources/**", "/static/**").permitAll()
	        .antMatchers("/admin","/delete/**").hasAuthority("ADMIN")
	        .antMatchers("/","/shop/**","/cart/**").hasAnyAuthority("ADMIN","USER") // admin mới xóa được sản phẩm
	        .anyRequest().authenticated()
	        .and()
	        .formLogin()
	        .loginPage("/login")
	        .usernameParameter("username")
	        .passwordParameter("password")
            .defaultSuccessUrl("/doLogin",true)
            .failureUrl("/login?error=sai thong tin dang nhap").permitAll()
	        .and()
	        .logout().logoutSuccessUrl("/login").permitAll()
	        .and()
	        .exceptionHandling().accessDeniedPage("/403");
	}
}
