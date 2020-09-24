//
// Created by cwj on 2020/9/24.
//

#include "base64_util.h"
#include <openssl/ossl_typ.h>
#include <cstring>
#include <cstdlib>
#include <openssl/bio.h>
#include <openssl/evp.h>
#include <openssl/buffer.h>

char *Base64Util::base64Enc(const char *content, int length) {

    BIO *bmem = NULL;
    BIO *b64 = NULL;
    BUF_MEM *bptr;
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new(BIO_s_mem());
    b64 = BIO_push(b64, bmem);
    BIO_write(b64, content, length);
    BIO_flush(b64);
    BIO_get_mem_ptr(b64, &bptr);
    BIO_set_close(b64, BIO_NOCLOSE);
    char *buff = (char *) calloc(sizeof(char *), bptr->length + 1);
    memcpy(buff, bptr->data, bptr->length);
    buff[bptr->length] = 0;
    BIO_free_all(b64);
    return buff;
}


char *Base64Util::base64Dec(char *content, int length) {
    BIO *b64 = NULL;
    BIO *bmem = NULL;
    char *buffer = (char *) calloc(sizeof(char *), length);
    memset(buffer, 0, length);
    b64 = BIO_new(BIO_f_base64());
    bmem = BIO_new_mem_buf(content, length);
    bmem = BIO_push(b64, bmem);
    BIO_read(bmem, buffer, length);
    BIO_free_all(bmem);
    return buffer;
}