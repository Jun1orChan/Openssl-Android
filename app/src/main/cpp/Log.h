#ifndef LOG_H
#define LOG_H

#define NDK_LOG true
#define C_LOG false

#if NDK_LOG

#include <android/log.h>
#include <jni.h>

#endif

#define TAG "AQCENTER_NDK"

#if NDK_LOG
#define log_print_verbose(fmt, ...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, fmt,##__VA_ARGS__)
#define log_print_debug(fmt, ...) __android_log_print(ANDROID_LOG_DEBUG, TAG, fmt, ##__VA_ARGS__)
#define log_print_info(fmt, ...) __android_log_print(ANDROID_LOG_INFO, TAG, fmt, ##__VA_ARGS__)
#define log_print_warn(fmt, ...) __android_log_print(ANDROID_LOG_WARN, TAG, fmt, ##__VA_ARGS__)
#define log_print_error(fmt, ...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##__VA_ARGS__)

#elif C_LOG

#define log_print_verbose(fmt, ...)  printf(fmt, ##__VA_ARGS__)
#define log_print_debug(fmt, ...) printf(fmt, ##__VA_ARGS__)
#define log_print_info(fmt, ...) printf(fmt, ##__VA_ARGS__)
#define log_print_warn(fmt, ...)  printf(fmt, ##__VA_ARGS__)
#define log_print_error(fmt, ...) printf(fmt,  ##__VA_ARGS__)
#else
#define log_print_verbose(fmt, ...)
#define log_print_debug(fmt, ...)
#define log_print_info(fmt, ...)
#define log_print_warn(fmt, ...)
#define log_print_error(fmt, ...)
#endif


#define LOGV(fmt, ...) log_print_verbose(fmt, ##__VA_ARGS__)
#define LOGD(fmt, ...) log_print_debug(fmt, ##__VA_ARGS__)
#define LOGI(fmt, ...) log_print_info(fmt, ##__VA_ARGS__)
#define LOGW(fmt, ...) log_print_warn(fmt, ##__VA_ARGS__)
#define LOGE(fmt, ...) log_print_error(fmt, ##__VA_ARGS__)


#endif // LOG_H

