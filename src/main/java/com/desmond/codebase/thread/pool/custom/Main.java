package com.desmond.codebase.thread.pool.custom;

/**
 * Created by Li.Xiaochuan on 17/8/13.
 */
public class Main {
    public static void main(String[] args) {
        ThreadPool pool = ThreadPool.getThreadPool(3);

        pool.execute(new Runnable[] {new Task(), new Task(), new Task()});
        pool.execute(new Runnable[] {new Task(), new Task(), new Task()});

        System.out.println(pool);

        pool.destroy();

    }

    static class Task implements Runnable {
        private static volatile int i = 1;

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + (i++) + " was finished.");
        }
    }
}
