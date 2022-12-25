package dungnt_ptit.com.securitystandard.configuration.database;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "db")
@Log4j2
@Data
public class DataBaseInfo {
    private String host ;
    private String port ;
    private String driver;
    private String url ;
    private String userName;
    private String password;
    private String dbName;

    @PostConstruct
    public void init(){
        log.log(Level.INFO,"Getting Data Infor from profile!");
        log.log(Level.INFO,toString());
    }

    public String toString() {
        return "\nuserName:" + this.userName +"\nPassword:" + this.password
                +"\ndbName: " + this.dbName;
    }
}
