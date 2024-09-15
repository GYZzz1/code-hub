package com.gyzjc.club.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关启动类
 *
 * @author gyz
 * @since 2024/09/13
 */
@SpringBootApplication
@ComponentScan("com.gyzjc")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
