package com.gene.cyptomonitoringtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApplicationStater {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStater.class, args);
    }
}
