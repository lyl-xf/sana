package com.sana.base.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LON
 * @create 2025/7/12
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("系统设置")
                .pathsToMatch("/sys/**")
                .packagesToScan("com.sana.system")
                .build();
    }

    @Bean
    public GroupedOpenApi abutmentApi() {
        return GroupedOpenApi.builder()
                .group("接入管理")
                .pathsToMatch("/abutment/**")
                .packagesToScan("com.sana.abutment")
                .build();
    }
    @Bean
    public GroupedOpenApi deviceApi() {
        return GroupedOpenApi.builder()
                .group("设备管理")
                .pathsToMatch("/devices/**")
                .packagesToScan("com.sana.devices")
                .build();
    }

    @Bean
    public GroupedOpenApi rulesApi() {
        return GroupedOpenApi.builder()
                .group("规则设置")
                .pathsToMatch("/rules/**")
                .packagesToScan("com.sana.rules")
                .build();
    }

    @Bean
    public GroupedOpenApi cameraApi() {
        return GroupedOpenApi.builder()
                .group("摄像头管理")
                .pathsToMatch("/camera/**")
                .packagesToScan("com.sana.camera")
                .build();
    }

    @Bean
    public GroupedOpenApi combinationApi() {
        return GroupedOpenApi.builder()
                .group("大屏管理")
                .pathsToMatch("/api/goview/**")
                .packagesToScan("com.sana.combination")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("lyl-xf");
        return new OpenAPI().info(new Info()
                .title("Sana接口文档")
                .description("基于jdk17、vue3的管理平台")
                .contact(contact)
                .version("1.0")
                .termsOfService("Apache-2.0 license")
                .license(new License().name("Apache-2.0 license").url("Not ready, please wait")));
    }
}
