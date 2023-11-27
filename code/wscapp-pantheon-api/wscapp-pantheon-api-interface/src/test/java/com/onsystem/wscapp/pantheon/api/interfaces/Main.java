package com.onsystem.wscapp.pantheon.api.interfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.onsystem.wscapp.pantheon.api.interfaces.repositories"})
@ComponentScan(basePackages = {"com.onsystem.wscapp.pantheon"})
@EntityScan(basePackages = {"com.onsystem.wscapp.pantheon.api.interfaces.entity"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
