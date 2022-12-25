package com.ttc.bookmeetingroom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Value("${security.oath2.resource-id}")
	private String RESOURCE_ID;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new RestAuthenticationFailureHandler();
	}

	@Bean
	RestAccessDeniedHandler accessDeniedHandler() {
		return new RestAccessDeniedHandler();
	}

	@Bean
	RestAuthenticationEntryPoint authenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable()
				.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/room/**").access("hasRole('ADMIN')")
				.antMatchers("/location/**").access("hasRole('ADMIN')")
				.antMatchers("/department/**").access("hasRole('ADMIN')")
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.authenticationEntryPoint(authenticationEntryPoint());
	}
}