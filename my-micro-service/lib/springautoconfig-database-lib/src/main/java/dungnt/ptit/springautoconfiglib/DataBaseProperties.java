package dungnt.ptit.springautoconfiglib;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Data
@Configuration
@PropertySource("classpath:databaseconfig.properties")
@ConfigurationProperties(prefix = "database")
//@ConditionalOnProperty(prefix = "database",name = "url")
public class DataBaseProperties {
    private String username;
    private String password;
    private String url;
    private String driver;

}
