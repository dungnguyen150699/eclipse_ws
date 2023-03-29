package com.codeusingjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.codeusingjava.service.UsersService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	

@Autowired
private UsersService usersService;

@Override
protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests()
	.antMatchers("/").permitAll()  //dashboard  page will be permit to all user 
	.antMatchers("/admin/**").hasAnyRole("ADMIN") //Only admin user can login 
	.antMatchers("/user/**").hasAnyRole("USER") //Only normal user can login 
	.anyRequest().authenticated() //Rest of all request need authentication 
	        .and()
	        .formLogin()
	.loginPage("/login")  //Loginform all can access .. 
	.defaultSuccessUrl("/dashboard")
	.failureUrl("/login?error")
	.permitAll()
	.and()
	        .logout()
	.permitAll();
	
	}
	
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
auth.userDetailsService(usersService).passwordEncoder(passwordEncoder);;
	    }



	}

