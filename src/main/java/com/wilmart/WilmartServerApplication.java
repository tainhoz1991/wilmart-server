package com.wilmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WilmartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WilmartServerApplication.class, args);
    }

}
