package com.desmond.codebase.thread.base;

/**
 * 方式一 extend thread
 * Created by Li.Xiaochuan on 17/8/11.
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        print();
    }

    public static void print() {
        for(int i= 0; i< 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
