package com.test.sync;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2020/3/12.
 */
public class MyReentrantLock {
    private static final ThreadLocal TL_LOCK_COUNT = new ThreadLocal<Long>();

    private void log(String content) {
        System.out.println(System.currentTimeMillis() + "[" + Thread.currentThread().getName() + "]: " + content);
    }

    private AtomicBoolean locked = new AtomicBoolean(false);

    public void lock() {

        while (true) {
            Integer count = (Integer) TL_LOCK_COUNT.get();

            if (count != null && count != 0) { // already get the lock
                count += 1; // update reference count
                TL_LOCK_COUNT.set(count);

                log("reentrant lock, count = " + count);
                return;

            } else { // haven't get the lock
                boolean succ = locked.compareAndSet(false, true);
                if (succ) {
                    TL_LOCK_COUNT.set(1);
                    log("get lock successfully, count = 1");
                    return;

                } else {
                    synchronized (this) {
                        try {
                            log("lock failed, start waiting......");
                            wait();
                            log("waiting done");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void unlock() {
        Integer count = (Integer) TL_LOCK_COUNT.get();
        if (count == null || count == 0) {
            log("invalid unlock operation");
            return;
        }

        count -= 1;
        TL_LOCK_COUNT.set(count);

        log("unlock, count = " + count);

        if (count == 0) {
            synchronized (this) {
                log("release lock");

                locked.compareAndSet(true, false);

                notifyAll();
            }
        }
    }


    public static void main(String[] args) throws Exception {

        class TestThreadA extends Thread {
            private MyReentrantLock lock;

            TestThreadA(MyReentrantLock lock) {
                this.lock = lock;
                setName("Thread-A - ");
            }

            public void run() {
                try {
                    lock.lock();
                    sleep(2000);
                    lock.lock();
                    sleep(1000);
                    lock.lock();

                    sleep(500);
                    lock.unlock();
                    sleep(500);
                    lock.unlock();
                    sleep(500);
                    lock.unlock();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        class TestThreadB extends Thread {
            private MyReentrantLock lock;

            TestThreadB(MyReentrantLock lock) {
                this.lock = lock;
                setName("Thread-B - ");
            }

            public void run() {
                try {
                    lock.lock();
                    sleep(2000);
                    lock.lock();
                    sleep(1000);
                    lock.lock();

                    sleep(500);
                    lock.unlock();
                    sleep(500);
                    lock.unlock();
                    sleep(500);
                    lock.unlock();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        MyReentrantLock lock = new MyReentrantLock();
        TestThreadA a = new TestThreadA(lock);
        TestThreadB b = new TestThreadB(lock);

        a.start();
        b.start();

        a.join();
        b.join();
    }
}
