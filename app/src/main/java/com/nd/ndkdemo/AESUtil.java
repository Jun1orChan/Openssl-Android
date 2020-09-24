package com.nd.ndkdemo;

import android.os.Build;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Base64;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加密工具类
 *
 * @author cwj
 */
public class AESUtil {

    /**
     * AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**
     * SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
     */
    private static final String SHA1PRNG = "SHA1PRNG";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        //创建IV
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        // 密钥规格
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        byte[] byteContent = content.getBytes(Charset.defaultCharset());
        byte[] result = cipher.doFinal(byteContent);
        return Base64.encodeToString(result, Base64.NO_WRAP);
    }

    /**
     * AES 解密操作，IV由Key决定
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key, String iv) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        //使用密钥初始化，设置为解密模式
        //创建IV
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        // 密钥规格
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] byteContent = Base64.decode(content, Base64.NO_WRAP);
        //执行操作
        byte[] result = cipher.doFinal(byteContent);
        return new String(result);
    }

}

