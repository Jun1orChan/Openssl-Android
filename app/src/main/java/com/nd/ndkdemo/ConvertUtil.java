package com.nd.ndkdemo;

/**
 * @author cwj copy
 * @date 2020-05-15
 */
public final class ConvertUtil {

    /**
     * 字节数组转十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String toHexString(byte[] bytes) {
        String tmpString = "";
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < bytes.length; idx++) {
            tmpString = (Integer.toHexString(bytes[idx] & 0XFF));
            if (tmpString.length() == 1) {
                // 只有一位时补零。
                sb.append("0");
            }
            sb.append(tmpString);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 十六进制字符串转字节数组
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    public static byte[] toByteFromHexString(String hexString) {
        byte[] bytes = new byte[0];
        if (null != hexString && hexString.length() > 0 && (hexString.length() % 2) == 0) {
            bytes = new byte[hexString.length() / 2];
            for (int idx = 0; idx < hexString.length(); idx += 2) {
                bytes[idx / 2] = Integer.decode("0x" + hexString.substring(idx, idx + 2)).byteValue();
            }
        }
        return bytes;
    }

    /**
     * int转为byte
     *
     * @param source
     * @param lenth
     * @return
     */
    public static byte[] toByte(int source, int lenth) {
        byte[] bLocalArr = new byte[lenth];
        for (int i = 0; (i < 4) && (i < lenth); i++) {
            bLocalArr[i] = (byte) (source >> 8 * i & 0xFF);
        }
        return bLocalArr;
    }

    /**
     * long转为byte
     *
     * @param source
     * @param lenth
     * @return
     */
    public static byte[] toByte(long source, int lenth) {
        byte[] buffer = new byte[lenth];
        for (int i = 0; (i < 8) && (i < lenth); i++) {
            buffer[i] = (byte) (source >> 8 * i & 0xFF);
        }
        return buffer;
    }

    /**
     * byte转int
     *
     * @param buffer
     * @return
     */
    public static int toInt(byte[] buffer) {
        int iOutcome = 0;
        byte bLoop;
        for (int i = 0; i < buffer.length; i++) {
            bLoop = buffer[i];
            iOutcome += (bLoop & 0xFF) << (8 * i);
        }
        return iOutcome;
    }


    public static int toInt(String buffer, int defaultValue) {
        int iOutcome = defaultValue;
        try {
            iOutcome = Integer.parseInt(buffer);
        } catch (Exception e) {
        }

        return iOutcome;
    }

    public static double toDouble(String buffer, int defaultValue) {
        double iOutcome = defaultValue;
        try {
            iOutcome = Double.parseDouble(buffer);
        } catch (Exception e) {
        }

        return iOutcome;
    }

    public static long toLong(String buffer, long defaultValue) {
        long iOutcome = defaultValue;
        try {
            iOutcome = Long.parseLong(buffer);
        } catch (Exception e) {
        }

        return iOutcome;
    }

    /**
     * byte转long
     *
     * @param buffer
     * @return
     */
    public static long toLong(byte[] buffer) {
        long lOutcome = 0;
        byte bLoop;
        for (int i = 0; i < buffer.length; i++) {
            bLoop = buffer[i];
            lOutcome += (bLoop & 0xFF) << (8 * i);
        }
        return lOutcome;
    }

    /**
     * @param obj           需转成字符串类型 。
     * @param defaultString 默认字符串 。
     * @return
     */
    public static String Convert2String(Object obj, String defaultString) {
        String resultValue = defaultString;
        if (obj != null) {
            resultValue = obj.toString();
        }
        return resultValue;
    }

    public static int[] toIntArray(String buffer, String regex) {
        String[] stringArray = buffer.split(regex);
        int[] idArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            idArray[i] = Integer.parseInt(stringArray[i]);
        }
        return idArray;
    }
}
