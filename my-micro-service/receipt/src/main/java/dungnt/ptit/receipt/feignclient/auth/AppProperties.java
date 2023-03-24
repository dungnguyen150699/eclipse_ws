package dungnt.ptit.receipt.feignclient.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {
    private final Auth auth = new Auth();

    @Data
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
        private String urlAuth;
        private String email;
        private String password;
    }
}
