package com.example.demo.hibernate;


import com.example.demo.pojo.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

import java.util.Properties;


@org.springframework.context.annotation.Configuration
public class HibernateSessionUtils {
    private static final SessionFactory SESSION_FACTORY;
    static {
        Configuration  configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL,"jdbc:mysql://localhost:3306/hibernate_session?createDatabaseIfNotExist=true&reconnect=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useTimezone=true&serverTimezone=UTC");
        properties.put(Environment.USER,"root");
        properties.put(Environment.PASS,"b17dccn160");
        properties.put(Environment.SHOW_SQL,"true");
        properties.put(Environment.HBM2DDL_AUTO,"create");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Product.class);
//        configuration.addPackage("com.example.demo.pojo");
        ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
