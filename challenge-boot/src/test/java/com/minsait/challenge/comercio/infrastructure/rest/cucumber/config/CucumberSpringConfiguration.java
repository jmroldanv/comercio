package com.minsait.challenge.comercio.infrastructure.rest.cucumber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@Configuration
public class CucumberSpringConfiguration {
    @Value("${openapi.comercio.base-path:}")
    protected String basePath;

    @LocalServerPort protected int port;

    @Autowired protected TestRestTemplate restTemplate;
}
