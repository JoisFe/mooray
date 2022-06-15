package com.nhnacademy.gateway.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .setReadTimeout(Duration.ofSeconds(50L))
            .setConnectTimeout(Duration.ofSeconds(30L))
            .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/admin/**").setViewName("admin");
        registry.addViewController("/private-project/**").setViewName("private-project");
        registry.addViewController("/project/**").setViewName("project");
        registry.addRedirectViewController("/redirect-index", "/");
        registry.addViewController("/auth/login").setViewName("login");
        registry.addViewController("/auth/logout").setViewName("logout");
        registry.addViewController("/error/403").setViewName("error403");
    }

}
