package com.onsystem.wscapp.pantheon.input.api.interfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.onsystem.wscapp.pantheon.input.api.interfaces.repositories"})
@ComponentScan(basePackages = {"com.onsystem.wscapp.pantheon.input"})
@EntityScan(basePackages = {"com.onsystem.wscapp.pantheon.commons"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
