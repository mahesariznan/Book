//
// Created by Iznan on 2020-03-03.
//

#include "com_iznan_bookapps_MyNDK.h"

JNIEXPORT jstring JNICALL Java_com_iznan_bookapps_MyNDK_getMyString(JNIEnv *env, jobject){
    return (*env).NewStringUTF("thi is my library");
}