//
// Created by cwj on 2020/9/24.
//

#ifndef NDKDEMO_DES_UTIL_H
#define NDKDEMO_DES_UTIL_H


class DESUtil {

public:
    static char *des_cbc_enc(unsigned char *content, unsigned char *key, unsigned char *iv);

    static char *des_cbc_dec(unsigned char *content, unsigned char *key, unsigned char *iv);
};


#endif //NDKDEMO_DES_UTIL_H
