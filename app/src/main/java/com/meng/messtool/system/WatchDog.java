package com.meng.messtool.system;

import com.meng.tools.app.*;

import java.util.concurrent.*;

/*
 *package  com.meng.messtool.system
 *@author  清梦
 *@date    2024/9/2 19:28
 */
public class WatchDog implements Runnable {

    public WatchDog(int thresholdMills, Runnable onBark) {
        this.thresholdMills = thresholdMills;
        this.onBark = onBark;
    }

    private final long thresholdMills;
    private long lastFeed;
    private Runnable onBark;

    public void feedDog() {
        lastFeed = System.currentTimeMillis();
    }

    public void start() {
        lastFeed = System.currentTimeMillis();
        ThreadPool.executeAtFixedRate(this, 1, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        if (!Thread.interrupted()) {
            if (System.currentTimeMillis() - lastFeed > thresholdMills && onBark != null) {
                onBark.run();
                feedDog();
            }
        }
    }
}
