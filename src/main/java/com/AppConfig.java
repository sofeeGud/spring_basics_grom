package com;

import com.lesson2.hw1.Route;
import com.lesson2.hw1.Service;
import com.lesson2.hw1.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@Configuration
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    //lesson 2 hw1
    @Bean
    public Service service1() {
        return new Service(1, "TestService", new ArrayList<>(Arrays.asList("param1", "param2")));
    }

    @Bean
    public Step step1() {
        Map<String, List> mapParamsFrom = new HashMap<>();
        mapParamsFrom.put("serviceFrom", service1().getParamsToCall());
        Map<String, List> mapParamsTo = new HashMap<>();
        mapParamsTo.put("serviceTo", service1().getParamsToCall());
        return new Step(1, service1(), service1(), mapParamsFrom, mapParamsTo);
    }

    @Bean
    public Route route() {
        return new Route("1", new ArrayList<>(Arrays.asList(step1())));

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@database-1.clkqnh5ztnkq.us-east-2.rds.amazonaws.com:1521:ORCL");
        dataSource.setUsername("main");
        dataSource.setPassword("main1111");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }
}
