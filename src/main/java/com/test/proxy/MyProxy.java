package com.test.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2020/4/7.
 */
public class MyProxy implements InvocationHandler, Serializable {

    public MyProxy() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("called: " + method.getDeclaringClass() + "." + method.getName());
        return null;
    }
}
