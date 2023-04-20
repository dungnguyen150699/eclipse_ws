package dungnt.ptit.springautoconfiglib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"dungnt.ptit.springautoconfiglib"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "dungnt.ptit",entityManagerFactoryRef = "masterEntityManager", transactionManagerRef = "masterTransactionManager")
public class ConfigDataBase {
    @Autowired
    private DataBaseProperties dataBaseProperties;
    /**
     * Master data source.
     *
     * @return the data source
     */
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.dataBaseProperties.getDriver());
        dataSource.setUrl(this.dataBaseProperties.getUrl());

        // giải mã password
//	    String plainText = HashHelper.aesDecrypt(password, aesSecretKey);
        dataSource.setUsername(System.getenv("database.username")==null ? this.dataBaseProperties.getUsername(): System.getenv("database.username"));
        dataSource.setPassword(System.getenv("database.password")==null ? this.dataBaseProperties.getPassword() : System.getenv("database.password"));
        return dataSource;
    }

    @Bean(name = "masterEntityManager")
    public LocalContainerEntityManagerFactoryBean masterEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        DataSource source = masterDataSource();
        em.setDataSource(source);
        em.setPackagesToScan("dungnt.ptit");

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
}
