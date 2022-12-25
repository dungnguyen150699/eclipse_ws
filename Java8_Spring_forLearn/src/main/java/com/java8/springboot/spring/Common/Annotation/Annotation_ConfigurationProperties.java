package com.java8.springboot.spring.Common.Annotation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;



import lombok.Data;
import lombok.NoArgsConstructor;
//	Link: https://www.baeldung.com/configuration-properties-in-spring-boot
@Configuration //We use @Configuration so that Spring creates a Spring bean in the application context.
@ConfigurationProperties(prefix = Annotation_ConfigurationProperties.PREFIX)
/* Không thể định nghĩa class là 1 Component vì nó là 1 Config phía trên . Cứ @Autowired Bình thường
(@Configuration) = Bean
*/
@Data
@NoArgsConstructor
public class Annotation_ConfigurationProperties{
	public static final String PREFIX = "db";
	private String host ;
	private String port ;
	private String drive ;
	private String url ;
	private String userName;
	private String password;
	private String dbName;
	
	public String toString() {
		return "userName:" + this.userName +"\nPassword:" + this.password
				+"\ndbName: " + this.dbName;
	}
	
}
