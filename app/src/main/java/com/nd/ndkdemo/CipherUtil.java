package com.nd.ndkdemo;


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.MessageFormat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加解密辅助类。
 *
 * @author <a href="mailto:hzg329@163.com">HuangZhongGui</a>
 * @version 2.0.0.1110
 */
@SuppressWarnings("restriction")
public final class CipherUtil {

    /**
     * @param source 待解密数据。
     * @param key    密钥。
     * @return 解密成功后的数据，如果解密失败，返回默认值。
     */
    public static String desDecrypt(String source, String key) {
        return desDecrypt(source, key, Charset.defaultCharset());
    }

    /**
     * @param source  待解密数据。
     * @param key     密钥。
     * @param charset 字符集。
     * @return 解密成功后的数据，如果解密失败，返回默认值。
     */
    public static String desDecrypt(String source, String key, Charset charset) {
        return desDecrypt(source, key, charset, "");
    }


    /**
     * @param source       待解密数据。
     * @param key          密钥。
     * @param charset      字符集。
     * @param defaultValue 默认值。
     * @return 解密成功后的数据，如果解密失败，返回默认值。
     */
    public static String desDecrypt(String source, String key, Charset charset, String defaultValue) {
        String resultValue = defaultValue;
        try {
            byte[] byteForKey = key.getBytes(charset);
            DESKeySpec ks = new DESKeySpec(byteForKey);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey sk = skf.generateSecret(ks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] byteForIv = byteForKey;
            IvParameterSpec ivps = new IvParameterSpec(byteForIv);
            cipher.init(Cipher.DECRYPT_MODE, sk, ivps);
            byte[] data = ConvertUtil.toByteFromHexString(source);
            byte decryptedData[] = cipher.doFinal(data);
            resultValue = new String(decryptedData, charset.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    /**
     * @param source 待加密数据。
     * @param key    密钥。
     * @return 加密成功后的数据，如果加密失败，返回默认值。
     */
    public static String desEncrypt(String source, String key) {
        return desEncrypt(source, key, Charset.defaultCharset());
    }

    /**
     * @param source  待加密数据。
     * @param key     密钥。
     * @param charset 字符集。
     * @return 加密成功后的数据，如果加密失败，返回默认值。
     */
    public static String desEncrypt(String source, String key, Charset charset) {
        return desEncrypt(source, key, charset, "");
    }


    /**
     * @param source       待加密数据。
     * @param key          密钥。
     * @param charset      字符集。
     * @param defaultValue 默认值。
     * @return 加密成功后的数据，如果加密失败，返回默认值。
     */
    public static String desEncrypt(String source, String key, Charset charset, String defaultValue) {
        String resultValue = defaultValue;
        try {
            byte[] byteForKey = key.getBytes(charset);
            DESKeySpec ks = new DESKeySpec(byteForKey);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey sk = skf.generateSecret(ks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] byteForIv = byteForKey;
            IvParameterSpec ivps = new IvParameterSpec(byteForIv);
            cipher.init(Cipher.ENCRYPT_MODE, sk, ivps);
            byte[] data = source.getBytes(charset);
            byte[] decryptedData = cipher.doFinal(data);
            resultValue = ConvertUtil.toHexString(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resultValue;
    }

    /**
     * @param source       待加密数据。
     * @param key          密钥。
     * @param charset      字符集。
     * @param defaultValue 默认值。
     * @return 加密成功后的数据，如果加密失败，返回默认值。
     */
    public static String desEncrypt2(String source, String key, String iv, Charset charset, String defaultValue) {
        String resultValue = defaultValue;
        try {
            byte[] byteForKey = key.getBytes(charset);
            DESKeySpec ks = new DESKeySpec(byteForKey);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey sk = skf.generateSecret(ks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] byteForIv = iv.getBytes(charset);
            IvParameterSpec ivps = new IvParameterSpec(byteForIv);
            cipher.init(Cipher.ENCRYPT_MODE, sk, ivps);
            byte[] data = source.getBytes(charset);
            byte[] decryptedData = cipher.doFinal(data);
            resultValue = ConvertUtil.toHexString(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resultValue;
    }

    /**
     * md5密码
     *
     * @param source 明文
     * @return 密文
     */
    public static String md5EncryptFromPassword(String source) {
        return md5EncryptFromPassword(source, Charset.defaultCharset());
    }

    /**
     * md5密码
     *
     * @param source  明文
     * @param charset 字符集
     * @return
     */
    public static String md5EncryptFromPassword(String source, Charset charset) {
        String resultValue = "";
        try {
            //第一段加盐。
            byte[] sourceStringData1 = source.getBytes(charset);
            //来源："，。"
            byte[] sourceStringData2 = new byte[]{-93, -84, -95, -93};
            //密码算法加盐第三段。
            byte[] sourceStringData3 = "fdjf,jkgfkl".getBytes(charset);
            byte[] sourceStringData = new byte[sourceStringData1.length + sourceStringData2.length
                    + sourceStringData3.length];
            System.arraycopy(sourceStringData1, 0, sourceStringData, 0, sourceStringData1.length);
            System.arraycopy(sourceStringData2, 0, sourceStringData, sourceStringData1.length,
                    sourceStringData2.length);
            System.arraycopy(sourceStringData3, 0, sourceStringData,
                    sourceStringData1.length + sourceStringData2.length, sourceStringData3.length);
            resultValue = md5Encrypt(sourceStringData, "");
        } catch (Exception ex) {
        }
        return resultValue;
    }

    /**
     * 密码二次加密运算。
     *
     * @param userName
     * @param oncePassword
     * @return
     */
    public static String md5EncryptFromPassword(String userName, String oncePassword) {
        return md5EncryptFromPassword(userName, oncePassword, Charset.forName("utf-8"));
    }

    /**
     * 密码二次加密运算。
     *
     * @param userName
     * @param oncePassword
     * @param charset
     * @return
     */
    public static String md5EncryptFromPassword(String userName, String oncePassword, Charset charset) {
        String resultValue = "";
        try {
            //第一段加盐
            byte[] sourceStringData1 = MessageFormat.format("{0}{1}", userName, oncePassword).getBytes(charset);
            // 来源："，。"
            byte[] sourceStringData2 = new byte[]{-93, -84, -95, -93};
            // 密码算法加盐第三段。
            byte[] sourceStringData3 = "7ui8,jkgfkl".getBytes(charset);
            byte[] sourceStringData = new byte[sourceStringData1.length + sourceStringData2.length
                    + sourceStringData3.length];
            System.arraycopy(sourceStringData1, 0, sourceStringData, 0, sourceStringData1.length);
            System.arraycopy(sourceStringData2, 0, sourceStringData, sourceStringData1.length,
                    sourceStringData2.length);
            System.arraycopy(sourceStringData3, 0, sourceStringData,
                    sourceStringData1.length + sourceStringData2.length, sourceStringData3.length);
            resultValue = md5Encrypt(sourceStringData, "");
        } catch (Exception ex) {
        }
        return resultValue;
    }


    /**
     * md5
     *
     * @param source 明文
     * @return
     */
    public static String md5Encrypt(String source) {
        return md5Encrypt(source, Charset.defaultCharset());
    }


    /**
     * md5
     *
     * @param source  明文
     * @param charset 字符集GB2312,UTF-8...
     * @return
     */
    public static String md5Encrypt(String source, Charset charset) {
        return md5Encrypt(source, charset, "");
    }


    /**
     * md5
     *
     * @param source       明文
     * @param charset      字符集GB2312,UTF-8...
     * @param defaultValue 默认值
     * @return
     */
    public static String md5Encrypt(String source, Charset charset, String defaultValue) {
        String resultValue = defaultValue;
        try {
            byte[] buffer = source.getBytes(charset);
            resultValue = md5Encrypt(buffer, defaultValue);
        } catch (Exception e) {

        }
        return resultValue;
    }


    /**
     * md5
     *
     * @param buffer       明文byte
     * @param defaultValue 默认值
     * @return
     */
    private static String md5Encrypt(byte[] buffer, String defaultValue) {
        String resultValue = defaultValue;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(buffer);
            byte[] md5buffer = messageDigest.digest();
            resultValue = ConvertUtil.toHexString(md5buffer);
        } catch (Exception e) {

        }
        return resultValue;
    }

}
