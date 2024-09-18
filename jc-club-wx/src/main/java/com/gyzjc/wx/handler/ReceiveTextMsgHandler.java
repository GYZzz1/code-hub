package com.gyzjc.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName : ReceiveTextMsgHandler
 * @Description : 接收文本消息句柄
 * @Author : GYZzz
 * @Date: 2024-09-17 16:51
 */
@Component
@Slf4j
public class ReceiveTextMsgHandler implements WeChatMsgHandler {
    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealMsg(Map<String, String> msgMap) {
        log.info("接收到文本消息");
        return null;
    }
}
