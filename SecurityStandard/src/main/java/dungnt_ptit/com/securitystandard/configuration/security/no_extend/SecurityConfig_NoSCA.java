package dungnt_ptit.com.securitystandard.configuration.security.no_extend;

import com.google.gson.Gson;
import dungnt_ptit.com.securitystandard.configuration.filter.CustomAuthenticationFilter;
import dungnt_ptit.com.securitystandard.configuration.filter.CustomAuthorizationFilter;
import dungnt_ptit.com.securitystandard.configuration.security.error.CustomAcessDeniedHandler;
import dungnt_ptit.com.securitystandard.configuration.security.error.CustomAuthencationEntryPoint;
import dungnt_ptit.com.securitystandard.configuration.security.error.CustomAuthenticationFailureHandler;
import dungnt_ptit.com.securitystandard.repository.UserRepository;
import dungnt_ptit.com.securitystandard.ulti.common.i18n.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( // Cái này là aop là spring cung cấp -> Nó có sẳn 3 method
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
) // https://www.baeldung.com/spring-security-method-security
public class SecurityConfig_NoSCA {

    // Cấu hình security -->
    @Autowired private UserRepository userRepository;
    @Autowired private MessageService messageService;

    private final String[] WHITELIST = {
            "/register","/login","/api/login/**","/css/**","**/js/**",
            "/api/token/refresh/**","**/images/**","/resources/**", "/static/**"
    };

    @Bean
    Gson getBeanGson(){
        return new Gson();
    }

    @Bean
    @SuppressWarnings("deprecation")
    PasswordEncoder getBeanPasswordEncoder(){
        // Cách 1 dùng mã hóa thật : 3 loại: Pbkdf2PasswordEncoder, SCryptPasswordEncoder or BCryptPasswordEncoder
//        return new BCryptPasswordEncoder();

        // Cái dưới hết hạn xem link này:
        // https://stackoverflow.com/questions/52134823/multiple-markers-at-this-line-the-type-nooppasswordencoder-is-deprecated-the
        return NoOpPasswordEncoder.getInstance(); // Cách 2 trả về 1 phương thức không mã hóa

        // C3 thêm {noop} vào trước password xem cái imemory ở dưới
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .cors().and().csrf().disable()
                 .authorizeHttpRequests((auth) -> {
                     auth.antMatchers(WHITELIST).permitAll();
                     auth.antMatchers("/admin","/delete/**").hasAnyAuthority("ADMIN");
                     auth.antMatchers("/","/shop/**","/cart/**").hasAnyAuthority("ADMIN","USER");
                     auth.anyRequest().authenticated();
                  })
                 .exceptionHandling()
                 .accessDeniedHandler(acessDeniedHandler()) // 403
                 .authenticationEntryPoint(authenticationEntryPoint()) // 401
                 .and();

         http.cors().configurationSource(corsConfigurationSource());
        // Set session management to stateless
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Filter --> 1 cách thay thế @Autowired tránh cricle rất hay nhé
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter
                (new CustomAuthenticationManager(getBeanPasswordEncoder(),messageService,userRepository),getBeanGson());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login"); // custom link login
        http.addFilter(customAuthenticationFilter); // Filter Login -> api (authen)
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); // Filter UnthenClass Check JWT (author)
        return http.httpBasic(withDefaults()).build(); // ** nhờ dòng này nó trả về SecurityFilterChain đó
        // function interface trả về chính thằng HttpSecurity
        // nếu ko thì nó trả ra thằng HTTPBasic relam gì ấy
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

    // Cấu hình các phương thức lỗi
    // 1 Xử lý khi login thất bại (Form login của Spring Security)
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() { // Cái này dùng cho form Login Security
        return new CustomAuthenticationFailureHandler();
    }
    // 2 Xử lý khi người dùng là user đăng nhập vào quyền của người khác vd admin 403
    @Bean
    public AccessDeniedHandler acessDeniedHandler() {
        return new CustomAcessDeniedHandler();
    }
    // 3 Xử lý khi người dùng chưa đăng nhập mà trỏ đến project cũng là 403
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthencationEntryPoint();
    }

    // Config Cors
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),"POST","PUT","DELETE","OPTIONS"));
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
