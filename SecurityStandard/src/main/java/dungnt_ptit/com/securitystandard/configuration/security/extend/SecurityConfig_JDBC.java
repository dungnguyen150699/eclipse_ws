//package dungnt_ptit.com.securitystandard.configuration.security.extend;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableWebSecurity //  Đang bị lỗi xem cái này (tóm lại cái authen này dùng cho h2 database nên dùng cho 1 project khác)
//@EnableMethodSecurity // -> https://www.youtube.com/watch?v=d7ZmZFbE_qY
//public class SecurityConfig_JDBC { // -> https://shareprogramming.net/huong-dan-su-dung-h2-database-trong-spring-boot/
//
//    // Cấu hình security -->
//    private final String[] WHITELIST = {
//            "/register","/login","/api/login/**","/css/**","**/js/**",
//            "/api/token/refresh/**","**/images/**","/resources/**", "/static/**"
//    };
//
//
//    @Bean
//    EmbeddedDatabase datasource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.HSQL)
//                .setName("dashboard")
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }
//
//    @Bean
//    JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("1"))
//                .roles("ADMIN")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }
//
////    @Bean
////    InMemoryUserDetailsManager users() {
////        return new InMemoryUserDetailsManager(
////                User.withUsername("dan")
////                        .password("{noop}password")
////                        .roles("ADMIN")
////                        .build()
////        );
////    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
////                .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"))
//                .authorizeRequests( auth -> auth
//                        .antMatchers(WHITELIST).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .headers(headers -> headers.frameOptions().sameOrigin())
////                .formLogin(withDefaults())
//                .build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}