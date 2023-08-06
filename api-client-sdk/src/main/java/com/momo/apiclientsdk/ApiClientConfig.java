package com.momo.apiclientsdk;

import com.momo.apiclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/*
* 客户端配置
 */
@EnableAutoConfiguration
@Configuration
@ConfigurationProperties("momoapi.client")
@Data
@ComponentScan("com.momo.apiclientsdk")
public class ApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }
}
