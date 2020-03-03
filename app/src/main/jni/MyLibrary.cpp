//
// Created by Iznan on 2020-03-03.
//

#include "com_iznan_bookapps_MyNDK.h"

JNIEXPORT jstring JNICALL Java_com_iznan_bookapps_MyNDK_getMyString(JNIEnv *env, jclass){
    return (*env).NewStringUTF("thi is my library");
}


JNIEXPORT jstring JNICALL Java_com_iznan_bookapps_MyNDK_getApiKey(JNIEnv *env, jclass){
    return (*env).NewStringUTF("&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948");
}