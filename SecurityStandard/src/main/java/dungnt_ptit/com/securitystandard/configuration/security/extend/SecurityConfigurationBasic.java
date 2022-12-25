//package dungnt_ptit.com.securitystandard.configuration.security.extend;
//
//import com.google.gson.Gson;
//import dungnt_ptit.com.securitystandard.configuration.filter.CustomAuthenticationFilter;
//import dungnt_ptit.com.securitystandard.configuration.filter.CustomAuthorizationFilter;
//import dungnt_ptit.com.securitystandard.configuration.security.error.CustomAcessDeniedHandler;
//import dungnt_ptit.com.securitystandard.configuration.security.error.CustomAuthencationEntryPoint;
//import dungnt_ptit.com.securitystandard.configuration.security.error.CustomAuthenticationFailureHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(
//        securedEnabled = true,
//        jsr250Enabled = true,
//        prePostEnabled = true
//)
//@SuppressWarnings("deprecation") // Nên viết 1 cái Security khác không nên dùng cái này // => Các phiên bản mới sẽ không còn dùng nữa
//public class SecurityConfigurationBasic extends WebSecurityConfigurerAdapter {
////    @Autowired
////    private CustomAuthenticationFilter customAuthenticationFilter;
//    // Cấu hình một vài bean cơ sở
//    @Bean
//    Gson getBeanGson(){
//        return new Gson();
//    }
//
//    @Bean
//    @SuppressWarnings("deprecated")
//    PasswordEncoder getBeanPasswordEncoder(){
//        // Cách 1 dùng mã hóa thật : 3 loại: Pbkdf2PasswordEncoder, SCryptPasswordEncoder or BCryptPasswordEncoder
//        return new BCryptPasswordEncoder();
//
//        // Cái dưới hết hạn xem link này:
//        // https://stackoverflow.com/questions/52134823/multiple-markers-at-this-line-the-type-nooppasswordencoder-is-deprecated-the
////        return NoOpPasswordEncoder.getInstance(); // Cách 2 trả về 1 phương thức không mã hóa
//
//        // C3 thêm {noop} vào trước password xem cái imemory ở dưới
//    }
//
//    // Cấu hình security -->
//    private final String[] WHITELIST = {
//            "/register","/login","/api/login/**","/css/**","**/js/**",
//            "/api/token/refresh/**","**/images/**","/resources/**", "/static/**"
//    };
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override // Security
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // TH1: Cấu hình memory
//        auth
//                // enable in memory based authentication with a user named
//                // "user" and "admin"
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}1")
////                .password("{noop}password") // TH3 ở trên
////                .roles("USER")  // authorities != role ( role thì nó sẽ có prefix(ROLE_) ROLE_USER
//                .authorities("USER")
//                .and()
//                .withUser("admin")
//                .password("{noop}1")
////                .password("{noop}password") // TH3 ở trên
////                .roles("USER", "ADMIN")  // authorities != role ( role thì nó sẽ có prefix(ROLE_) ROLE_USER
//                .authorities("USER","ADMIN")
//                .and()
//                .passwordEncoder(getBeanPasswordEncoder());
//
////         TH2: Cấu hình bằng userDetailService kết nối DB
////         --> cuối cùng thì method này cũng gọi DaoAuthenticationProvider thôi
//        auth.userDetailsService(userDetailsService).passwordEncoder(getBeanPasswordEncoder());
//    }
//
//    // Có 2 cách tạo Bean AuthenticationMananger
//    // Cách 1
//    @Override // Override this method to expose the AuthenticationManager from configure(AuthenticationManagerBuilder) to be exposed as a Bean. For example:
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
////    // Cách 2
////    @Bean
////    @Override //from WebSecurityConfigurerAdapter --> Security Method Bean
////    protected AuthenticationManager authenticationManager() throws Exception {
////        return super.authenticationManager();
////    }
//
////    /** Reset role prefix to be empty. */
////    @Bean
////    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
////        return new GrantedAuthorityDefaults("");
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Enable CORS and disable CSRF
//        // Không cần CSRF nữa cái này FE ko lưu Session, Cookie nữa nên ko cần dùng
//        http = http.cors().and().csrf().disable();
//
//        http.cors().configurationSource(corsConfigurationSource());
//
//        // Set session management to stateless
//        http = http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//
//        http.authorizeRequests()
//                .antMatchers(WHITELIST).permitAll()
//                .antMatchers("/admin","/delete/**").hasAuthority("ADMIN")
//                .antMatchers("/","/shop/**","/cart/**").hasAnyAuthority("ADMIN","USER") // admin mới xóa được sản phẩm
//                .anyRequest().authenticated()
//                .and()
////                .formLogin()
////                .failureHandler(authenticationFailureHandler())
////                .failureUrl("/login-failure")
////                .and();
//                .exceptionHandling()
//                .accessDeniedHandler(acessDeniedHandler())
//                .authenticationEntryPoint(authenticationEntryPoint())
//                .and()
//        ;
//
//        // Filter --> 1 cách thay thế @Autowired tránh cricle rất hay nhé
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(),getBeanGson());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login"); // custom link login
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    // Cấu hình các phương thức lỗi
//    // 1 Xử lý khi login thất bại (Form login của Spring Security)
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler();
//    }
//    // 2 Xử lý khi người dùng là user đăng nhập vào quyền của người khác vd admin 403
//    @Bean
//    public AccessDeniedHandler acessDeniedHandler() {
//        return new CustomAcessDeniedHandler();
//    }
//    // 3 Xử lý khi người dùng chưa đăng nhập mà trỏ đến project cũng là 403
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint(){
//        return new CustomAuthencationEntryPoint();
//    }
//
//    // Config Cors
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
//        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),"POST","PUT","DELETE","OPTIONS"));
//        configuration.addAllowedMethod(CorsConfiguration.ALL);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}
