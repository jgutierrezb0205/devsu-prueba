package com.devsu.prueba.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties
@Configuration
public class PropertiesConfiguration {

    @Value("${client.account.baseUrl}")
    private String ClientAccountBaseUrl;

    @Value("${client.account.movements.path}")
    private String ClientAccountMovementsPath;
}
