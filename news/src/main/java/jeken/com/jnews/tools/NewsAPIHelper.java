package jeken.com.jnews.tools;

import jeken.com.jnews.jni.JNI;

/**
 * Created by Administrator on 2017-04-23.
 */

public class NewsAPIHelper {
    private static JNI jni = new JNI();
    public  static String getAppid(){
        return jni.getAppId();
    }
    public  static String getSecret(){
        return jni.getSecret();
    }
}
