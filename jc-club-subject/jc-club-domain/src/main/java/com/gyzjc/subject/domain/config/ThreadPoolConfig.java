package com.gyzjc.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : ThreadPoolConfig
 * @Description : 线程池的config管理
 * @Author : GYZzz
 * @Date: 2024-09-25 00:12
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool() {
        return new ThreadPoolExecutor(20,
                100,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(40),
                new CustomNameThreadFactory("label"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
