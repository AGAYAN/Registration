package com.example.focus_group.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.example.focus_group")
@PropertySource("classpath:application.properties")
public class RestConfig {
    private final Environment environment;

    public RestConfig(Environment environment) {
        this.environment = environment;
    }



    @Bean
    public DataSource dataSourse() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.driver"));
        dataSource.setUrl(environment.getProperty("spring.url"));
        dataSource.setUsername(environment.getProperty("spring.username"));
        dataSource.setPassword(environment.getProperty("spring.password"));
        return dataSource;
    }


}
