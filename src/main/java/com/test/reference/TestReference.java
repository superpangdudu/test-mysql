package com.test.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2020/2/14.
 */
public class TestReference {

    public void testWeakReference() throws Exception {
        ReferenceQueue queue = new ReferenceQueue<String>();

        //String s = new String("hahaha");
        String s = "jj";
        WeakReference reference = new WeakReference<String>(s, queue);
        System.out.println(reference.get());

        s = null;
        System.gc();

        System.out.println(reference.get());
        Reference ref = queue.remove();

        System.out.println("WeakReference = " + reference);
        System.out.println("Reference = " + ref);
    }

    public void testPhantomReference() throws Exception {
        ReferenceQueue queue = new ReferenceQueue<String>();

        String s = new String("hahaha");

        PhantomReference reference = new PhantomReference<String>(s, queue);
        System.out.println(reference.get());

        s = null;
        System.gc();

        System.out.println(reference.get());

        Reference ref = queue.remove();

        System.out.println("PhantomReference = " + reference);
        System.out.println("Reference = " + ref);
    }

    //===================================================================================
    public static void main(String[] args) throws Exception {
        TestReference obj = new TestReference();
        obj.testPhantomReference();

    }
}
