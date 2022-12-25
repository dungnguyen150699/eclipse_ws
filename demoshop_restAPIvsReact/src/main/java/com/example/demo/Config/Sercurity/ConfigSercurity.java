package com.example.demo.Config.Sercurity;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.ui.Model;

import com.example.demo.Config.Jwt.JwtDoFilter;
import com.example.demo.Config.Jwt.exceptionJwt_AccessDenied;
import com.example.demo.Config.Jwt.exceptionJwt_UnAuthentication;
import com.example.demo.Entity.RoleEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.UserService;


@Configuration
@EnableWebSecurity
public class ConfigSercurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService us;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
//		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public JwtDoFilter jwtDoFilter() throws Exception {
		JwtDoFilter jwtDoFilter= new JwtDoFilter();
		jwtDoFilter.setAuthenticationManager(authenticationManager());
		return jwtDoFilter;
	}

	@Bean
	public exceptionJwt_UnAuthentication restServicesEntryPoint() {
		return new exceptionJwt_UnAuthentication();
	}

	@Bean
	public exceptionJwt_AccessDenied customAccessDeniedHandler() {
		return new exceptionJwt_AccessDenied();
	}
	
	@Bean
	@Override //from WebSecurityConfigurerAdapter --> Security Method Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		System.out.println("create authProvider ----~~");
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TH1
//		auth
//				.inMemoryAuthentication()
//				.withUser("user")
//                .password("{noop}1") // TH3 ở trên
////                .roles("USER")
//				.authorities("USER")
//				.and()
//				.withUser("admin1")
//                .password("{noop}1") // TH3 ở trên
////                .roles("USER", "ADMIN")
//				.authorities("USER","ADMIN")
//				.and();
		// TH2
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder()); //
	}

	private static final String[] AUTH_WHITELIST = {
			"/v2/api-docs/**",  "/configuration/ui/**",
			"/swagger-resources/**", "/configuration/security/**",
			"/swagger-ui.html/**", "/webjars/**","/swagger/**","/user/login"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		System.out.println("test .. .");
		// Enable CORS and disable CSRF
	    http = http.cors().and().csrf().disable();
//	    http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

	    // Set session management to stateless
	    http = http
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and();
	    
//	    // Set unauthorized requests exception handler
//	    http.antMatcher("/**")
//	        .httpBasic()
//	        .authenticationEntryPoint(new exceptionJwt_UnAuthentication());
	    		
		http.authorizeRequests()
			.antMatchers("/swagger-ui.html/*,/swagger-ui.html#/*,/user/login/**","/user/register/**","/css/**","**/js/**","**/images/**","/resources/**", "/static/**").permitAll()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers("/admin","/delete/**","/product/**").hasAuthority("ADMIN")
	        .antMatchers("/","/shop/**","/cart/**","/product/**").hasAnyAuthority("ADMIN","USER") // admin mới xóa được sản phẩm
	        .anyRequest().authenticated()
	        .and()
//	        .formLogin()
//	        .loginPage("/login")
//	        .usernameParameter("username")
//	        .passwordParameter("password")
//            .defaultSuccessUrl("/product/getAll",true)
//            .failureUrl("/login?error").permitAll()
//	        .and()
//	        .logout().logoutSuccessUrl("/login").permitAll()
//	        .and()
	        .exceptionHandling()
//	        .accessDeniedPage("/403")
	        .accessDeniedHandler(new exceptionJwt_AccessDenied());
		
	    http.addFilterBefore(jwtDoFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
