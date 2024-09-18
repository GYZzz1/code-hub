package com.gyzjc.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName : SubscribeMsgHandlerImpl
 * @Description : 接收关注消息句柄
 * @Author : GYZzz
 * @Date: 2024-09-17 16:48
 */
@Component
@Slf4j
public class SubscribeMsgHandler implements WeChatMsgHandler {
    @Override
    public WeChatMsgTypeEnum getMsgType() {
        return WeChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealMsg(Map<String, String> msgMap) {
        log.info("触发用户关注事件！");
        return null;
    }
}
