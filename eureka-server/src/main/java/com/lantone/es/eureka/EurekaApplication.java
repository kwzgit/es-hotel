package com.lantone.es.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @className: com.lantone.eureka-> EurekaApplication
 * @description: Eureka服务端启动类
 * @author: kongwz
 * @createDate: 2021-09-15 9:30
 * @version: 1.0
 * @todo:
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
