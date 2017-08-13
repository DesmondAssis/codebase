package com.desmond.codebase.thread.base;

/**
 * Created by Li.Xiaochuan on 17/8/11.
 */
public class MyRunnable implements Runnable{
    private String name;

    public MyRunnable() {
    }

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        MyThread.print();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
