package com.test.common;

/**
 * Created by Administrator on 2020/3/12.
 */
public class FunnelRateLimiter {

    private int capacity;
    private int quota;
    private long period;

    private int leftQuota;
    private long lastAccessTime;

    public FunnelRateLimiter(int capacity, int quota, long period) {
        this.capacity = capacity;
        this.quota = quota;
        this.period = period;

        leftQuota = capacity;
        lastAccessTime = System.currentTimeMillis();
    }

    public boolean tryToAccess() {
        long currentTime = System.currentTimeMillis();
        long passedTime = currentTime - lastAccessTime;

        int newQuota = (int) (passedTime * quota / period);
        leftQuota += newQuota;

        if (leftQuota > capacity)
            leftQuota = capacity;

        leftQuota -= 1;
        if (leftQuota < 0) {
            leftQuota = 0;
            return false;
        }

        lastAccessTime = currentTime;
        return true;
    }

    public static void main(String[] args) throws Exception {
        FunnelRateLimiter rateLimiter = new FunnelRateLimiter(3, 10, 30000);
        System.out.println("" + rateLimiter.tryToAccess());
        System.out.println("" + rateLimiter.tryToAccess());
        System.out.println("" + rateLimiter.tryToAccess());
        System.out.println("" + rateLimiter.tryToAccess());
        System.out.println("" + rateLimiter.tryToAccess());

        Thread.sleep(3000);
        System.out.println("" + rateLimiter.tryToAccess());
        System.out.println("" + rateLimiter.tryToAccess());
        Thread.sleep(1000);
        System.out.println("" + rateLimiter.tryToAccess());
        Thread.sleep(3000);
        System.out.println("" + rateLimiter.tryToAccess());
    }
}
