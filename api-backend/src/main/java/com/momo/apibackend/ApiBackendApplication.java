package com.momo.apibackend;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.momo.apiclientsdk","com.momo.apibackend"})
@MapperScan("com.momo.apibackend.mapper")
@EnableDubbo
public class ApiBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBackendApplication.class, args);
    }
}
