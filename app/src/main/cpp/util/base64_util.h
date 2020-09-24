
//
// Created by cwj on 2020/9/24.
//

#ifndef NDKDEMO_BASE64_UTIL_H
#define NDKDEMO_BASE64_UTIL_H


class Base64Util {

public:
    /**
     * base64编码
     * @param content 待编码内容
     * @param length  内容长度
     * @return
     */
    static char *base64Enc(const char *content, int length);

    /**
     * base64解码
     * @param content 待解码内容
     * @param length  内容长度
     * @return
     */
    static char *base64Dec(char *content, int length);
};


#endif //NDKDEMO_BASE64_UTIL_H
