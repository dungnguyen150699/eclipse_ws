package com.techprimers.security.springsecurityauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class AuthServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("password")).
//                roles("USER").build();
        UserDetails user = new User("user","1", Arrays.asList(new SimpleGrantedAuthority("USER")));
//        UserDetails userAdmin = User.builder().username("admin").password(passwordEncoder().encode("password")).
//                roles("ADMIN").build();
        UserDetails userAdmin = new User("admin","1", Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        return new InMemoryUserDetailsManager(Arrays.asList(user, userAdmin));
    }
}
