//
// Created by cwj on 2020/9/24.
//

#include <openssl/md5.h>
#include <cstring>
#include <cstdio>
#include "md5_util.h"

char *MD5Util::md5(unsigned char *content) {
    unsigned char result[MD5_DIGEST_LENGTH] = {0};
    MD5_CTX ctx;
    MD5_Init(&ctx);
    MD5_Update(&ctx, content, strlen((char *) content));
    MD5_Final(result, &ctx);
    char tmp[3] = {0}, buf[33] = {0};
    for (int i = 0; i < MD5_DIGEST_LENGTH; i++) {
        sprintf(tmp, "%02X", result[i]);
        strcat(buf, tmp);
    }
    return buf;
}