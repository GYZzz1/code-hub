package com.gyzjc.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题微服务启动类
 *
 * @author gyz
 * @since 2024/06/15
 */
@SpringBootApplication
@ComponentScan("com.gyzjc")
@MapperScan("com.gyzjc.**.dao")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class, args);
    }
}
