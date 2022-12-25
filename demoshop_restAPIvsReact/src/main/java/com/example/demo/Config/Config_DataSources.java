package com.example.demo.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.sql.SimpleSelect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.NoArgsConstructor;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo.Repository",
    entityManagerFactoryRef = "masterEntityManager", transactionManagerRef = "masterTransactionManager")
@NoArgsConstructor
public class Config_DataSources {
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.host}")
	private String host;
	
	@Value("${db.port}")
	private String port;
	
	@Value("${db.db-name}")
	private String dbname;

	@Value("${db.user-name}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	/**
	   * Master data source.
	   *
	   * @return the data source
	   */
	  @Bean(name = "masterDataSource")
	  @Primary
	  public DataSource masterDataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(this.driver);
	    dataSource.setUrl(getConnection());
	    System.out.println(getConnection());
	    // giải mã password
//	    String plainText = HashHelper.aesDecrypt(password, aesSecretKey);
	    dataSource.setUsername(System.getenv("db.username")==null ? this.username : System.getenv("db.username"));
	    dataSource.setPassword(System.getenv("db.password")==null ? this.password : System.getenv("db.password"));
	    return dataSource;
	  }
	
//	  @Bean
//	    public JdbcTemplate lockProvider(DataSource dataSource) {
//	        return new JdbcTemplate(
//	                JdbcTemplateLockProvider.Configuration.builder()
//	                        .withJdbcTemplate(new JdbcTemplate(masterDataSource()))
//	                        .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
//	                        .build()
//	        );
//	    }
	  
	  @Bean(name = "masterEntityManager")
	    @Qualifier(value = "masterEntityManager")
	    public LocalContainerEntityManagerFactoryBean masterEntityManager() {
	        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	        DataSource source = masterDataSource();
	        em.setDataSource(source);
	        em.setPackagesToScan("com.example.demo.Entity");
	        
	        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
	        // nen tim hieu 3 cai nay la gi
	        em.setJpaVendorAdapter(vendorAdapter);
	        em.setJpaProperties(hibernateProperties());
	        return em;
	    }
	  
	  /**
	   * Master transaction manager.
	   *
	   * @return the platform transaction manager
	   */
	  @Bean(name = "masterTransactionManager")
	  @Qualifier(value = "masterTransactionManager")
	  public PlatformTransactionManager masterTransactionManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
	    return transactionManager;
	  }
	  

	  private static Properties hibernateProperties() {
		    // Initialize property configuration
		    Properties properties = new Properties();

		    properties.put("spring.datasource.testWhileIdle", "true");
		    properties.put("hibernate.hbm2ddl.auto", "update");
		    properties.put("spring.datasource.validationQuery", "SELECT 1");
		    properties.put("spring.datasource.initialization-mode", "never");
		    properties.put("hibernate.show_sql", "false");
		    properties.put("hibernate.globally_quoted_identifiers", "true");
		    properties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
		    properties.put("spring.jpa.hibernate.naming.implicit-strategy",
		        "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl");
		    properties.put("spring.jpa.hibernate.naming.physical-strategy",
		        "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		    properties.put("spring.jpa.properties.hibernate.dialec", "org.hibernate.dialect.MySQL5InnoDBDialect");
		    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		    properties.put("spring.jpa.properties.hibernate.format_sql", "true");
		    

		    // Return property configuration
		    return properties;
		  }
	  
	  private String getConnection() {
		    String host = System.getenv("db.host")!=null ? this.host : System.getenv("db.host");
		    String port = System.getenv("db.port")!=null ? this.port : System.getenv("db.port");
		    String name = System.getenv("db.db-name")!=null ? dbname : System.getenv("db.db-name");
		    return String.format(url, this.host, this.port, this.dbname);
		  }
}
