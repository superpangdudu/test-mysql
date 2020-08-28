package com.test.mysql.test;

/**
 * Created by Administrator on 2020/1/15.
 */
public class HelloWorld2 implements IHelloWorld {
    public HelloWorld2() {
        System.out.println("HelloWorld2 - class loader = " + this.getClass().getClassLoader());
    }

    public void hello() {
        System.out.println("Hello world 2.");
    }
}
