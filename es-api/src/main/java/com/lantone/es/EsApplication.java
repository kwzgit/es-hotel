package com.lantone.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @className: com.lantone.es-> EsApplication
 * @description: es项目启动类
 * @author: kongwz
 * @createDate: 2021-09-18 14:51
 * @version: 1.0
 * @todo:
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class,args);
    }

}
