package dungnt_ptit.com.securitystandard.configuration.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = DataBaseConfig.jpaPackage,
        entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class DataBaseConfig {

    public final static String jpaPackage = "dungnt_ptit.com.securitystandard.repository";
    public final static String entityPackage = "dungnt_ptit.com.securitystandard.pojo.entity";
    @Autowired
    private DataBaseInfo dataBaseInfo;

    @Autowired
    private Environment env;

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(dataBaseInfo.getDriver()==null ? env.getProperty("db.driver") : dataBaseInfo.getDriver());
        dataSource.setUrl(getConnection());
        dataSource.setUsername(dataBaseInfo.getUserName()==null ? env.getProperty("db.userName") : dataBaseInfo.getUserName());
        dataSource.setPassword(dataBaseInfo.getPassword()==null ? env.getProperty("db.password") : dataBaseInfo.getPassword());
        // schema init
//        Resource initSchema = new ClassPathResource("scripts/schema.sql"); // ko dung dc
//        Resource initData = new ClassPathResource("scripts/data.sql");
//        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema,initData);
//        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData);
//        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        DataSource source = masterDataSource();
        em.setDataSource(source);
        em.setPackagesToScan(DataBaseConfig.entityPackage);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private static Properties hibernateProperties() {
        // Initialize property configuration
        Properties properties = new Properties();
        properties.put("spring.datasource.testWhileIdle", "true");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("spring.datasource.validationQuery", "SELECT 1");
        properties.put("spring.datasource.initialization-mode", "never");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.globally_quoted_identifiers", "true");
//        properties.put("spring.jpa.hibernate.ddl-auto", "create"); // Why not run this line properties ???
        properties.put("spring.jpa.hibernate.naming.implicit-strategy",
                "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl");
        properties.put("spring.jpa.hibernate.naming.physical-strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("spring.jpa.properties.hibernate.format_sql", "true");
        properties.put("spring.sql.init.mode","always");
        return properties;
    }

    private String getConnection() {
        String host = dataBaseInfo.getHost()==null ? env.getProperty("db.host") : dataBaseInfo.getHost();
        String port = dataBaseInfo.getPort()==null ? env.getProperty("db.port") : dataBaseInfo.getPort();
        String dbName = dataBaseInfo.getDbName()==null ? env.getProperty("db.dbName") : dataBaseInfo.getDbName();
        return String.format(dataBaseInfo.getUrl(),host, port, dbName);
    }

    @Bean("EntityManager")
    public EntityManager getEntityManager(){
        EntityManagerFactory entityManagerFactory = entityManagerFactory().getNativeEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if(entityManagerFactory != null) return entityManager;
        return null;
    }
}
