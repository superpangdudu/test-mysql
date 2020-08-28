package com.test.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
public class MyProxyFactory<T> {

    private Class<T> clazz;

    public MyProxyFactory(Class<T> clazz) {
        this.clazz = clazz;
    }


    private T newInstance(MyProxy myProxy) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] {clazz},
                myProxy);
    }

    public T newInstance() {
        MyProxy myProxy = new MyProxy();
        return newInstance(myProxy);
    }

    //===================================================================================
    public static void main(String[] args) throws Exception {

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("com.test.proxy.ITest", null);

        for (String className : objectMap.keySet()) {
            Class clazz = Class.forName(className);
            MyProxyFactory factory = new MyProxyFactory(clazz);

            Object obj = factory.newInstance();
            objectMap.put(className, obj);
        }

        //
        class TestContainer {
            ITest testObj = null;
        }

        TestContainer container = new TestContainer();

        Field[] fields = container.getClass().getDeclaredFields();
        for (Field field : fields) {
            String type = field.getName();
            Class clazz = field.getType();

            String className = clazz.getName();
            Object obj = objectMap.get(className);

            field.set(container, obj);

        }

        container.testObj.test();


//        Class clazz = Class.forName("com.test.proxy.ITest");
//        MyProxyFactory factory = new MyProxyFactory(clazz);
//
//        Object obj = factory.newInstance();
//        boolean flag = obj instanceof ITest;
//
//        ITest testObj = (ITest) obj;
//        testObj.hashCode();
//        testObj.hello();
//        testObj.test();
    }
}
