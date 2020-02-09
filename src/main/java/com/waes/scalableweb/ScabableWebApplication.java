package com.waes.scalableweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.waes.scalableweb"})
public class ScabableWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScabableWebApplication.class, args);
    }

}
