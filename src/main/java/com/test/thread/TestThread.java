package com.test.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/19.
 */
public class TestThread {

    static class MyThread extends Thread {

        List<Integer> valueList;

        public void setList(List<Integer> valueList) {
            this.valueList = valueList;
        }

        @Override public void run() {

            synchronized (valueList) {
                try {
                    System.out.println("[" + getName() + "] -- going to wait");
                    valueList.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("[" + getName() + "] -- wait done");

                if (valueList.size() <= 0) {
                    System.out.println("[" + getName() + "] -- got no value");
                    return;
                }

                int value = valueList.remove(0);
                System.out.println("[" + getName() + "] -- value = " + value);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        List values = new ArrayList<Integer>();
        for (int n = 0; n < 10; n++)
            values.add(n);


        MyThread[] threads = new MyThread[15];
        for (int n = 0; n < 15; n++) {
            threads[n] = new MyThread();
            threads[n].setList(values);
            threads[n].setName("Thread_" + n);

            threads[n].start();
        }

        Thread.sleep(3000);

        synchronized (values) {
            values.notifyAll();
        }

    }
}
