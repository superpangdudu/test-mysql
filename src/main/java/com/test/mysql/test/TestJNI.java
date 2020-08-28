package com.test.mysql.test;

/**
 * Created by Administrator on 2019/11/27.
 */
public class TestJNI {

    static {
        System.loadLibrary("testJni");
    }

    public native void test(int value, String text);

    public static void main(String[] args) {
        TestJNI obj = new TestJNI();
        obj.test(14, "Hello JNI");
    }
}
