package com.gyzjc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * oss服务启动类
 *
 * @author gyz
 * @since 2024/09/13
 */
@SpringBootApplication
@ComponentScan("com.gyzjc")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
