package com.gyzjc.wx.comtroller;

import com.gyzjc.wx.handler.WeChatMsgFactory;
import com.gyzjc.wx.handler.WeChatMsgTypeEnum;
import com.gyzjc.wx.redis.RedisUtil;
import com.gyzjc.wx.utils.MessageUtil;
import com.gyzjc.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName : CallBackController
 * @Description : 回调controller
 * @Author : GYZzz
 * @Date: 2024-09-17 15:11
 */
@RestController
@Slf4j
public class CallBackController {

    @Resource
    private RedisUtil redisUtil;
    private static final String TOKEN = "sadasdsafdggfjnvb";

    @RequestMapping("/test")
    public String test() {
        return "hello wx";
    }

    /**
     * 回调验签
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature: {}, timestamp: {}, nonce: {}, echostr: {}",
                signature, timestamp, nonce, echostr);
        String sha1Str = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        if (signature.equals(sha1Str)) {
            // 验签成功
            return echostr;
        }
        return "unknown";
    }

    /**
     * 操作回调
     * @return
     */
    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String callback(@RequestBody String requestBody,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信的请求: requetBody: {}, signature: {}, timestamp: {}, nonce: {}, msgSignature:{}",
                requestBody, signature, timestamp, nonce, msgSignature);
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event");
        log.info("msgType: {}, event: {}", msgType, event);

        String msgTypeKey = buildKey(msgType, event);
        String replyContent = Optional.ofNullable(WeChatMsgTypeEnum.match(msgTypeKey))
                                      .map(e -> e.dealMsg(msgMap, redisUtil))
                                      .orElse("");

         log.info("replyContent: {}", replyContent);

        return replyContent;
    }

    private String buildKey(String msgType, String event) {
        StringBuilder msgTypeKey = new StringBuilder(msgType);
        Optional.ofNullable(event)
                .filter(e -> !StringUtils.isEmpty(e))
                .ifPresent(e -> msgTypeKey.append(".").append(e));
        return msgTypeKey.toString();
    }

    // String content = "<xml>\n" +
    //         "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
    //         "  <FromUserName><![CDATA["+ toUserName + "]]></FromUserName>\n" +
    //         "  <CreateTime>12345678</CreateTime>\n" +
    //         "  <MsgType><![CDATA[text]]></MsgType>\n" +
    //         "  <Content><![CDATA[xxx]]></Content>\n" +
    //         "</xml>";

}
