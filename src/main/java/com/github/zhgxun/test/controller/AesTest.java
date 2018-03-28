package com.github.zhgxun.test.controller;

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
 * 对微信小程序用户加密数据进行解密
 * AES是高级加密标准(Advanced Encryption Standard)的简称
 *
 * @link https://mp.weixin.qq.com/debug/wxadoc/dev/api/signature.html#wxchecksessionobject
 * @link https://magiclen.org/java-base64/ Java如何進行Base64的編碼(Encode)與解碼(Decode)
 */
public class AesTest {

    /**
     * jdk 8开始内置的Base64相关操作做过优化, 性能较高, 不需要使用第三方库
     */
    private final static Base64.Decoder decoder = Base64.getDecoder();

    public static void main(String args[]) {

        // 应用ID
        String appId = "wx4f4bc4dec97d474b";
        // 安全字符串
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        // 加密后的字符串
        String encrypt = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
        // 初始向量Iv(Initialization Vector), 使用除ECB以外的其他加密模式均需要传入一个初始向量, 其大小与块大小相等, AES块大小是128bit, 所以Iv的长度是16字节, 初始向量可以加强算法强度
        String iv = "r7BXXKkLb8qrSNn05n0qiA==";
    }

    // 解析加密字符串
    private static void test(String sessionKey, String encrypt, String iv) {
        if (sessionKey.length() != 24) {
            System.out.println("encodingAesKey 非法");
        }
        if (iv.length() != 24) {
            System.out.println("iv非法");
        }

        try {
            // 填充方式(Padding)决定了最后的一个块需要填充的内容, 填充方式有PKCS5Padding, PKCS7Padding, NOPADDING三种, 但是JDK只提供了PKCS5Padding, NOPADDING两种, 填充方式为PKCS5Padding时, 最后一个块需要填充X个字节，填充的值就是X; 填充方式为NOPADDING时, 最后的一个块填充的内容由程序员自己决定, 通常填充0
            // @link https://docs.oracle.com/javase/8/docs/api/index.html 支持的模式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decoder.decode(sessionKey), "AES"), new IvParameterSpec(decoder.decode(iv)));
            byte[] bytes = cipher.doFinal(decoder.decode(encrypt.getBytes("UTF-8")));
            System.out.println(new String(bytes));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
