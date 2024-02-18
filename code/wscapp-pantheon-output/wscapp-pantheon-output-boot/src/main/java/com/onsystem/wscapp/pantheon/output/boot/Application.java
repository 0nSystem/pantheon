package com.onsystem.wscapp.pantheon.output.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.onsystem.wscapp.pantheon.output.api.interfaces.repositories"})
@ComponentScan(basePackages = {"com.onsystem.wscapp.pantheon.output"})
@EntityScan(basePackages = {"com.onsystem.wscapp.pantheon.commons"})
@EnableWebMvc
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
