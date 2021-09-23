package cn.itcast.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @className: cn.itcast.order.config-> DefaultFeignConfiguration
 * @description: feign远程调用，日志级别配置类
 * @author: kongwz
 * @createDate: 2021-09-15 11:06
 * @version: 1.0
 * @todo:
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLogLevel(){
        // 日志级别为BASIC
        return Logger.Level.BASIC;
    }
}
