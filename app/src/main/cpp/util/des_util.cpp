//
// Created by cwj on 2020/9/24.
//

#include <openssl/des.h>
#include <cstdlib>
#include <cstring>
#include <string>
#include "des_util.h"
#include "../Log.h"
#include "base64_util.h"

char *DESUtil::des_cbc_enc(unsigned char *content, unsigned char *key, unsigned char *iv) {

    DES_cblock *keyBlock = reinterpret_cast<DES_cblock *>(key);

    //密钥表
    DES_key_schedule key_schedule;
    //生成一个 key
    //设置密钥，且不检测密钥奇偶性
    DES_set_key_unchecked(keyBlock, &key_schedule);
    int contentSize = strlen((char *) content);
    size_t len = contentSize % 8 == 0 ? contentSize + 8 : contentSize + (8 - contentSize % 8);
    unsigned char output[len];
    //IV
    DES_cblock *ivec = (DES_cblock *) iv;
    // 循环加密，每8字节一次
    unsigned char inputText[9] = {0};
    unsigned char outputText[9] = {0};
    unsigned char tmp[8];
    for (int i = 0; i < contentSize / 8; i++) {
        memcpy(inputText, content + i * 8, 8);
        DES_ncbc_encrypt(inputText, outputText, 8, &key_schedule, ivec, DES_ENCRYPT);  //加密
        memcpy(tmp, outputText, 8);
        for (int j = 0; j < 8; j++) {
            output[8 * i + j] = tmp[j];
        }
        //重置ivec
        memcpy(ivec, outputText, 8);
    }
    //不是8的倍数
    if (contentSize % 8 != 0) {
        int tmp1 = contentSize / 8 * 8;
        int tmp2 = contentSize - tmp1;
        memset(inputText, (8 - tmp2), 8);
        memcpy(inputText, content + tmp1, tmp2);
    } else {
        memset(inputText, 8, 8);
    }
    //最后一块
    //加密
    DES_ncbc_encrypt(inputText, outputText, 8, &key_schedule, ivec, DES_ENCRYPT);
    memcpy(tmp, outputText, 8);
    for (int j = 0; j < 8; j++) {
        output[len - 8 + j] = tmp[j];
    }
    //输出加密以后的内容
    return Base64Util::base64Enc((char *) output, strlen((char *) output));
}


char *DESUtil::des_cbc_dec(unsigned char *content, unsigned char *key, unsigned char *iv) {

    char *cipherData = Base64Util::base64Dec((char *) content, strlen((char *) content));

    DES_cblock *keyBlock = reinterpret_cast<DES_cblock *>(key);
    //密钥表
    DES_key_schedule key_schedule;
    //生成一个 key
    //设置密钥，且不检测密钥奇偶性
    DES_set_key_unchecked(keyBlock, &key_schedule);
    int contentSize = strlen(cipherData);

    size_t len = contentSize;
    unsigned char output[len];
    //IV
    DES_cblock *ivec = (DES_cblock *) iv;
    //循环解密，每8字节一次
    unsigned char inputText[9];
    unsigned char outputText[9];
    unsigned char tmp[8];
    for (int i = 0; i < len / 8; i++) {
        memcpy(inputText, content + i * 8, 8);
        LOGE("=================%s", inputText);
        DES_ncbc_encrypt(inputText, outputText, 8, &key_schedule, ivec, DES_DECRYPT);  //加密
        memcpy(tmp, outputText, 8);
        for (int j = 0; j < 8; j++) {
            output[8 * i + j] = tmp[j];
        }
        LOGE("=================%s", output);
        //重置ivec
//        memcpy(ivec, outputText, 8);
    }
    //输出加密以后的内容
    return (char *) output;
}