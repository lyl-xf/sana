package com.sana.base.framework.swagger;

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
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("lyl-xf");
        return new OpenAPI().info(new Info()
                .title("Sana接口文档")
                .description("基于Sa-token，jdk17的基础管理平台")
                .contact(contact)
                .version("1.0")
                .termsOfService("Apache-2.0 license")
                .license(new License().name("Apache-2.0 license").url("Not ready, please wait")));
    }
}
