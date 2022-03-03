package com.itlize.jooleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JooleprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JooleprojectApplication.class, args);
    }

}
