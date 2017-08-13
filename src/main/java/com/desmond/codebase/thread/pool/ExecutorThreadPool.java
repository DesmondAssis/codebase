package com.desmond.codebase.thread.pool;

import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by presleyli on 2017/6/27.
 */
public class ExecutorThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i=0;i<10;i++) {
            executorService.execute(new Liftoff());
        }

        executorService.shutdown();

    }

    static class Liftoff implements Runnable {
        private static int taskCount = 0;
        private final int id = taskCount ++;
        private int countDown = 10;

        public Liftoff() {
        }

        public Liftoff(int countDown) {
            this.countDown = countDown;
        }

        @Override
        public void run() {
            while (countDown-- > 0) {
                System.out.println(status());
                Thread.yield();
            }
        }

        public String status() {
            return "#" + id + "(" + ( countDown > 0 ? countDown : "Liftoff!") + "), ";
        }
    }
}
