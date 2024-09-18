package com.gyzjc.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微信服务启动类
 *
 * @author gyz
 * @since 2024/09/17
 */
@SpringBootApplication
@ComponentScan("com.gyzjc")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class, args);
    }
}
