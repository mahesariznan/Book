package com.iznan.bookapps;

public class MyNDK {

    static {
        System.loadLibrary("MyLibrary");
    }

    public native String getMyString();

}
