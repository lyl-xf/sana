package com.sana.base.file.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LON
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "storage.minio")
public class MinioConfig {

    //地址
    private String endPoint;
    //访问密钥
    private String accessKey;
    //秘钥
    private String secretKey;
    //桶名
    private String bucketName;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(endPoint).credentials(accessKey, secretKey).build();
    }
}
