package com.iznan.bookapps;

public class MyNDK {

    static {
        System.loadLibrary("MyLibrary");
    }

    public static native String getMyString();
    public static native String getApiKey();

}
