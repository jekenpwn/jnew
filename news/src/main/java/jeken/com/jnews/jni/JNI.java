package jeken.com.jnews.jni;

/**
 * Created by Administrator on 2017-05-01.
 */

public class JNI {
    static {
        System.loadLibrary("JNICallc");
    }
    public native String getAppId();
    public native String getSecret();
}
