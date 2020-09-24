#include <jni.h>
#include <string>

#include "util/md5_util.h"
#include "Log.h"
#include "util/base64_util.h"
#include "util/aes_util.h"
#include "util/des_util.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_nd_ndkdemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    unsigned char test[] = "NetDragon1";
    LOGE("MD5:%s", MD5Util::md5(test));
    char *base64EncRet = Base64Util::base64Enc((const char *) test, strlen((char *) test));
    LOGE("Base64_ENC:%s", base64EncRet);
    char *base64DecRet = Base64Util::base64Dec(base64EncRet, strlen(base64EncRet));
    LOGE("Base64_DEC:%s", base64DecRet);

    unsigned char key[] = "1234567812345678";
    unsigned char iv[] = "1234567812345678";
    char *aesEnc = AESUtil::aes_128_cbc_enc(test, key, iv);
    LOGE("AES_ENC:%s", aesEnc);
    char *aesDec = AESUtil::aes_128_cbc_dec((const unsigned char *) aesEnc, key, iv);
    LOGE("AES_DEC:%s", aesDec);
    unsigned char key2[] = "12345678";
    unsigned char iv2[] = "12345678";
    unsigned char iv3[] = "12345678";
    char *desCBC_PKCS5Enc = DESUtil::des_cbc_enc(test, key2, iv2);
    LOGE("DES_CBC_PKCS5_enc:%s", desCBC_PKCS5Enc);
    char *desCBC_PKCS5Dec = DESUtil::des_cbc_dec((unsigned char *) desCBC_PKCS5Enc, key2,
                                                 iv3);
    LOGE("DES_CBC_PKCS5_dec:%s", desCBC_PKCS5Dec);
    return env->NewStringUTF("ssss");
}
