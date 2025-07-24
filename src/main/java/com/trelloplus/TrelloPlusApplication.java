package com.trelloplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.trelloplus.client")
@SpringBootApplication
public class TrelloPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrelloPlusApplication.class, args);
    }

}
