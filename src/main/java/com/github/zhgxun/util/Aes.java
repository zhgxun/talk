package com.github.zhgxun.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 解密微信小程序加密的用户信息, 并转化为用户对象
 */
public class Aes {

    /**
     * Base64工具
     */
    private static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 对微信小程序返回的加密用户信息进行解密
     *
     * @param sessionKey 安全字符
     * @param encrypt    待解密的数据
     * @param iv         初始向量
     * @return 解密后的字符串
     */
    public static String decrypt(String sessionKey, String encrypt, String iv) {
        if (sessionKey.length() != 24) {
            return null;
        }
        if (iv.length() != 24) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decoder.decode(sessionKey), "AES"), new IvParameterSpec(decoder.decode(iv)));
            byte[] bytes = cipher.doFinal(decoder.decode(encrypt.getBytes("UTF-8")));
            return new String(bytes);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }
}
