package com.onsystem.wscapp.pantheon.input.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.onsystem.wscapp.pantheon.input.api.interfaces.repositories"})
@ComponentScan(basePackages = {"com.onsystem.wscapp.pantheon.input"})
@EntityScan(basePackages = {"com.onsystem.wscapp.pantheon.commons"})
@EnableWebMvc
public class AppPantheonInput {
    public static void main(String[] args) {
        SpringApplication.run(AppPantheonInput.class);
    }
}
