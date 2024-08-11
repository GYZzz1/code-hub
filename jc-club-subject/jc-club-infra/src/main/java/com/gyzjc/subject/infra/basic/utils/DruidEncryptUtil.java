package com.gyzjc.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 数据库加密
 *
 * @author gzy
 * @since 2024/06/15
 */
public class DruidEncryptUtil {
    private static final String publicKey;
    private static final String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            System.out.println("privateKey: " + privateKey);
            publicKey = keyPair[1];
            System.out.println("publicKey: " + publicKey);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException();
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt: " + encrypt);
        return encrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText); // TODO: 为什么是公钥？
        System.out.println("decrypt: " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("1234");
        System.out.println("encrypt: " + encrypt);
        System.out.println("decrypt: " + decrypt(encrypt));
    }
}
