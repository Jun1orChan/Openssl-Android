//
// Created by cwj on 2020/9/24.
//

#ifndef NDKDEMO_MD5_UTIL_H
#define NDKDEMO_MD5_UTIL_H


class MD5Util {

public:
    /**
     * MD5
     * @param content  需要进行MD5的内容
     * @return
     */
    static char *md5(unsigned char *content);
};


#endif //NDKDEMO_MD5_UTIL_H
