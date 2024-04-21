package com.example.mibanco.exchangerate.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ApplicationProperties {

    @Value("${spring.application.name}")
    private String component;

    @Value("${api.configuration.description}")
    private String description;

    @Value("${api.author.name}")
    private String authorName;

    @Value("${api.author.email}")
    private String authorEmail;


}
