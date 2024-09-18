package com.gyzjc.wx.handler;

import java.util.Map;

public interface WeChatMsgHandler {

    WeChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String, String> msgMap);

}
