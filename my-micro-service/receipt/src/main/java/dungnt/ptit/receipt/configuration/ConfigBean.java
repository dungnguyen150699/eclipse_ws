package dungnt.ptit.receipt.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate declareRestemplate(){
        return new RestTemplate();
    }

    @Bean
    public Gson declareGson(){
        return new Gson();
    }
}
