package myoauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class AuthServerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService") private UserDetailsService userDetailsService;

    private final String[] WHILE_LIST = {
            "/api-docs/**",
            "/public/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/oauth/token",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set session management to stateless // Bỏ state_less nếu dùng mô hình mvc + thymeleaf :) vì nó ko dùng token
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();
        // Cors
         http
            .csrf().disable()
            .cors(withDefaults()); // by default is will active bean CorsConfigurationSource

        // config path request needs auth
        http
            .authorizeRequests()
            .antMatchers(WHILE_LIST).permitAll()
            .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(WHILE_LIST);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("password")).
//                roles("USER").build();
//        UserDetails userAdmin = User.builder().username("admin").password(passwordEncoder().encode("password")).
//                roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, userAdmin);
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
