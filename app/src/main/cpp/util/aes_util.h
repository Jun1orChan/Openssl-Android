//
// Created by cwj on 2020/9/24.
//

#ifndef NDKDEMO_AES_UTIL_H
#define NDKDEMO_AES_UTIL_H


#include <openssl/ossl_typ.h>

class AESUtil {

public:
    static char *aes_128_cbc_enc(const unsigned char *content, const unsigned char *key,
                                 const unsigned char *iv);

    static char *aes_128_cbc_dec(const unsigned char *content, const unsigned char *key,
                                 const unsigned char *iv);

};


#endif //NDKDEMO_AES_UTIL_H
