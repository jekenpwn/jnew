//
// Created by Administrator on 2017-05-01.
//
#include <string.h>
#include"jeken_com_jnews_jni_JNI.h"

/*
 *appid=36378
 *secrect=07f4aa1cdf174aa48375a87f78fe8a27
 */

/*
 * Class:     jeken_com_jnews_jni_JNI
 * Method:    getAppId
 * Signature: ()Ljava/lang/String;
 */

JNIEXPORT jstring JNICALL Java_jeken_com_jnews_jni_JNI_getAppId
  (JNIEnv *env, jclass jclazz){

   //jstring     (*NewStringUTF)(JNIEnv*, const jchar*, jsize);
   return (*env)->NewStringUTF(env,"36378");
}

/*
 * Class:     jeken_com_jnews_jni_JNI
 * Method:    getSecret
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jeken_com_jnews_jni_JNI_getSecret
  (JNIEnv *env, jclass jclazz){

    //jstring     (*NewStringUTF)(JNIEnv*, const jchar*, jsize);
      return (*env)->NewStringUTF(env,"07f4aa1cdf174aa48375a87f78fe8a27");
}
