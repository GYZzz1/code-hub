package com.gyzjc.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : WeChatMsgFactory
 * @Description : 微信消息工厂
 * @Author : GYZzz
 * @Date: 2024-09-17 16:55
 */
@Component
public class WeChatMsgFactory implements InitializingBean {

    @Resource
    private List<WeChatMsgHandler> weChatMsgHandlerList;
    private final Map<WeChatMsgTypeEnum, WeChatMsgHandler> handlerMap = new HashMap<>();

    public WeChatMsgHandler getHandlerByMsgType(String msgType) {
        WeChatMsgTypeEnum msgTypeEnum = WeChatMsgTypeEnum.getByMsgType(msgType);
        return handlerMap.get(msgTypeEnum);
    }

    @Override
    public void afterPropertiesSet() {
        for (WeChatMsgHandler weChatMsgHandler : weChatMsgHandlerList) {
            handlerMap.put(weChatMsgHandler.getMsgType(), weChatMsgHandler);
        }
    }
}
