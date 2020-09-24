//
// Created by cwj on 2020/9/24.
//

#include "aes_util.h"
#include "../Log.h"
#include "base64_util.h"
#include <cstring>
#include <openssl/evp.h>


char *AESUtil::aes_128_cbc_enc(const unsigned char *content, const unsigned char *key,
                               const unsigned char *iv) {
    int outlen = 0, cipherText_len = 0;
    int src_Len = strlen((char *) content);
    unsigned char *out = (unsigned char *) malloc((src_Len / 16 + 1) * 16);
    //清空内存空间
    memset(out, 0, (src_Len / 16 + 1) * 16);

    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_init(ctx);
    EVP_EncryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv);
    EVP_EncryptUpdate(ctx, out, &outlen, content, src_Len);
    cipherText_len = outlen;
    EVP_EncryptFinal_ex(ctx, out + outlen, &outlen);
    cipherText_len += outlen;
    char *baseEnc = Base64Util::base64Enc((const char *) out, cipherText_len);
    EVP_CIPHER_CTX_cleanup(ctx);
    free(out);
    return baseEnc;
}

char *AESUtil::aes_128_cbc_dec(const unsigned char *content, const unsigned char *key,
                               const unsigned char *iv) {
    // base64解码
    char *encData = Base64Util::base64Dec((char *) content, strlen((char *) content));
    int src_Len = strlen((char *) encData);
    unsigned char *out = (unsigned char *) calloc(sizeof(unsigned char *), src_Len);
    memset(out, 0, src_Len);
    int outLen = 0, plaintextLen = 0;
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_init(ctx);
    EVP_DecryptInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv);
    EVP_DecryptUpdate(ctx, out, &outLen, (const unsigned char *) encData, src_Len);
    plaintextLen = outLen;
    EVP_DecryptFinal_ex(ctx, out, &outLen);
    plaintextLen += outLen;
    EVP_CIPHER_CTX_cleanup(ctx);
    char *ret = (char *) calloc(sizeof(char *), plaintextLen + 1);
    memcpy(ret, out, plaintextLen);
    free(out);
    return ret;
}

