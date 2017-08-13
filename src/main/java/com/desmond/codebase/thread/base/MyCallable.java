package com.desmond.codebase.thread.base;

import java.util.concurrent.Callable;

/**
 * Created by Li.Xiaochuan on 17/8/11.
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i= 0; i< 10; i++) {
            sum ++;
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return sum;
    }
}
