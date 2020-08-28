package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2020/4/7.
 */
public class TestProxy {

    interface IHello {
        void hello(String content);
    }

    static class HelloImpl implements IHello {

        @Override
        public void hello(String content) {
            System.out.println("Hello " + content);
        }
    }

    static class HelloProxy implements InvocationHandler {
        private IHello target;

        IHello bind(IHello obj) {
            target = obj;

            return (IHello) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                    obj.getClass().getInterfaces(),
                    this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("HelloProxy enter method ......");

            Object result = method.invoke(target, args);

            System.out.println("HelloProxy exit method ......");

            return result;
        }
    }

    //===================================================================================
    public static void main(String[] args) {
        HelloProxy proxy = new HelloProxy();
        IHello helloObj = proxy.bind(new HelloImpl());
        helloObj.hello("world");
    }
}
