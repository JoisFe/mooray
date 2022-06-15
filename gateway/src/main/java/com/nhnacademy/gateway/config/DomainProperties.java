package com.nhnacademy.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "url.domain")
@ConfigurationPropertiesScan
@Configuration
public class DomainProperties {
    private String accountDomain;
    private String taskDomain;
    private String gatewaypassDomain;
}
