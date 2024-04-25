package com.example.previoussecurityassignment.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EntityScan("com.example.previoussecurityassignment.model")
@ComponentScan({"com.example.previoussecurityassignment.service", "com.example.previoussecurityassignment.controller", "com.example.previoussecurityassignment.config"})
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
@EnableWebSecurity
public class PreviousSecurityAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreviousSecurityAssignmentApplication.class, args);
    }

}
