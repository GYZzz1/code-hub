package com.gyzjc.club.gateway.filter;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName : LoginFilter
 * @Description : 登录拦截器
 * @Author : GYZzz
 * @Date: 2024-09-25 10:35
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String uri = request.getURI().getPath();
        log.info("LoginFilter.filter.uri: {}", uri);
        if (uri.equals("/user/doLogin")) {
            return chain.filter(exchange);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        log.info("LoginFilter.filter.tokenInfo: {}", new Gson().toJson(tokenInfo));
        String loginId = (String) tokenInfo.getLoginId();
        mutate.header("loginId", loginId);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
