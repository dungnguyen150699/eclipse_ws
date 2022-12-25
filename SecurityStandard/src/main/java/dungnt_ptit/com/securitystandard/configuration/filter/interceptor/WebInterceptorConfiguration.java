package dungnt_ptit.com.securitystandard.configuration.filter.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
//public class WebInterceptorConfiguration implements WebMvcConfigurerAdapter {
public class WebInterceptorConfiguration implements WebMvcConfigurer {
    @Autowired private GeneralPurposeInterceptor generalPurposeInterceptor;
    @Autowired private SinglePurposeInterceptor singlePurposeInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(generalPurposeInterceptor).order(1); // Nó chỉ chạy cái thàng này thôi thằng dưới ko chạy
        registry.addInterceptor(singlePurposeInterceptor).addPathPatterns(Arrays.asList("/api/login-test")).order(2);
    }
}
