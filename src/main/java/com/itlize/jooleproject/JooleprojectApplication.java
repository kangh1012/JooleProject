package com.itlize.jooleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.itlize.jooleproject.*")
public class JooleprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JooleprojectApplication.class, args);
    }

}
