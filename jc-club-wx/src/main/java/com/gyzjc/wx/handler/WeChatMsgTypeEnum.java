package com.gyzjc.wx.handler;

import com.gyzjc.wx.redis.RedisUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 微信消息枚举
 */
@Slf4j
@Getter
public enum WeChatMsgTypeEnum {

    SUBSCRIBE("event.subscribe", "用户关注事件") {
        @Override
        public String dealMsg(Map<String, String> msgMap, RedisUtil redisUtil) {
            log.info("触发用户关注事件！");
            String fromUserName = msgMap.get("FromUserName");
            String toUserName = msgMap.get("ToUserName");

            return getReplyContent("感谢您的关注!", fromUserName, toUserName);
        }
    },
    TEXT_MSG("text", "用户文本消息") {
        @Override
        public String dealMsg(Map<String, String> msgMap, RedisUtil redisUtil) {
            log.info("接收到文本消息");
            String content = msgMap.get("Content");

            if (!VALIDATE_CODE.equals(content)) {
                return "暂未开发该功能";
            }

            // openId
            String fromUserName = msgMap.get("FromUserName");
            // 订阅号
            String toUserName = msgMap.get("ToUserName");

            Random random = new Random();
            int validateCode = random.nextInt(90000) + 10000;
            String validateKey = redisUtil.buildKey(LOGIN_PREFIX, String.valueOf(validateCode));
            redisUtil.setNx(validateKey, fromUserName, 5L, TimeUnit.MINUTES);

            String validateContent = "您当前的验证码是：" + validateCode + ", 五分钟内有效";

            return getReplyContent(validateContent, fromUserName, toUserName);
        }
    };

    private static String getReplyContent(String content, String fromUserName, String toUserName) {
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[" + content + "]]></Content>\n" +
                "</xml>";
    }

    private final String msgType;
    private final String desc;

    WeChatMsgTypeEnum(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    private static final String VALIDATE_CODE = "验证码";
    private static final String LOGIN_PREFIX = "loginCode";

    private static final RedisUtil redisUtil = new RedisUtil();

    public abstract String dealMsg(Map<String, String> msgMap, RedisUtil redisUtil);

    public static WeChatMsgTypeEnum getByMsgType(String msgType) {
        for (WeChatMsgTypeEnum wxChatMsgTypeEnum : WeChatMsgTypeEnum.values()) {
            if (wxChatMsgTypeEnum.msgType.equals(msgType)) {
                return wxChatMsgTypeEnum;
            }
        }
        return null;
    }

    public static WeChatMsgTypeEnum match(String msgType) {
        return Stream.of(WeChatMsgTypeEnum.values())
                .filter(e -> e.getMsgType()
                        .equals(msgType))
                .findFirst()
                .orElse(null);
    }

}
