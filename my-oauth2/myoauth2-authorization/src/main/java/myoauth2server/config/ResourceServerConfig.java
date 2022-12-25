//package myoauth2server.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId("client").stateless(false);
//	}
//
//	// Ở đây config các resouce mà client có thể truy cập ( thực tế phía authen cũng có thể cấu hình --> nói chung chỉ nên làm 1 cái)
//	//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.anonymous().disable()
//				.authorizeRequests()
//		.antMatchers("/admin/**").access("hasRole('ADMIN')")
//				.antMatchers("/room/**").access("hasRole('ADMIN')")
//				.antMatchers("/location/**").access("hasRole('ADMIN')")
//				.antMatchers("/department/**").access("hasRole('ADMIN')")
//				.and();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.authorizeRequests()
//				.antMatchers("/oauth/token")
//				.permitAll()
//				.anyRequest().authenticated();
//	}
//}