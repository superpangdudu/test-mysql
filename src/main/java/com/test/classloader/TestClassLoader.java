package com.test.classloader;

import com.test.mysql.test.IHelloWorld;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2020/1/15.
 */
public class TestClassLoader extends ClassLoader {

    private String path;

    public TestClassLoader(String path) {
        this.path = path;

        System.out.println("TestClassLoader - " + this);
    }

    public TestClassLoader(ClassLoader parent, String path) {
        super(parent);
        this.path = path;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        try {
            name = name.replace(".", "//");
            FileInputStream fis = new FileInputStream(new File(path + name + ".class"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int b;
            while ((b = fis.read()) != -1)
                baos.write(b);

            return baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //===================================================================================
    public static void main(String[] args) throws Exception {

        TestClassLoader classLoader = new TestClassLoader("e:/tmp/class/");
        Class<?> clazz = classLoader.loadClass("com.test.mysql.test.HelloWorld");

        IHelloWorld helloWorld = (IHelloWorld) clazz.newInstance();
        helloWorld.hello();

        //
        TestClassLoader classLoader2 = new TestClassLoader("e:/tmp/class/");
        Class<?> clazz2 = classLoader2.loadClass("com.test.mysql.test.HelloWorld");

        IHelloWorld helloWorld2 = (IHelloWorld) clazz2.newInstance();
        helloWorld2.hello();
        helloWorld2 = (IHelloWorld) clazz2.newInstance();
        helloWorld2.hello();
    }
}
