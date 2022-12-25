//package com.example.demo.db2.Config;
//
//import org.hibernate.cfg.Environment;
//import org.hibernate.jpa.HibernatePersistenceProvider;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import javax.persistence.EntityManagerFactory;
//import java.util.Properties;
//
//@Configuration
//public class HibernateUtils {
//	
//	@Value("${db.driver2}")
//	private String driver;
//	
//	@Value("${db.url2}")
//	private String url;
//	
//	@Value("${db.host2}")
//	private String host;
//	
//	@Value("${db.port2}")
//	private String port;
//	
//	@Value("${db.db-name2}")
//	private String dbname;
//
//	@Value("${db.user-name2}")
//	private String username;
//	
//	@Value("${db.password2}")
//	private String password;
//
////    @Bean
////    public Properties hibernateProperties(){
////        final Properties properties = new Properties();
////        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
////        properties.put(Environment.DRIVER,this.driver);
////        properties.put(Environment.URL,this.getConnection());
////        properties.put(Environment.USER,this.username);
////        properties.put(Environment.PASS,this.password);
////        properties.put(Environment.SHOW_SQL,"true");
////
////
////        return properties;
////    }
//    @Bean(name = "masterEntityManager2")
//    public EntityManagerFactory entityManagerFactory(Properties hibernateProperties ){
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//
//        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter());
//        em.setJpaProperties( hibernateProperties );
//        em.setPersistenceUnitName( "test" );
//        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        em.afterPropertiesSet();
//
//        return em.getObject();
//    }
//    
//	  private String getConnection() {
//		    String host = System.getenv("db.host2")!=null ? this.host : System.getenv("db.host2");
//		    String port = System.getenv("db.port2")!=null ? this.port : System.getenv("db.port2");
//		    String name = System.getenv("db.db-name2")!=null ? dbname : System.getenv("db.db-name2");
//		    return String.format(url, this.host, this.port, this.dbname);
//		  }
//}
