package com.desmond.codebase.thread.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Li.Xiaochuan on 17/8/11.
 */
public class Main {
    public static void main(String[] args) {
//        createdByWay1();
//        createdByWay2();
//        createdByWay3();
        createdByWay3_2();


        while(true) {
            System.out.println(Thread.activeCount());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * extend Thread.
     */
    private static void createdByWay1() {
        for(int i = 0; i < 5; i++) {
            MyThread m1 = new MyThread();
            m1.start();

            MyThread m2 = new MyThread();
            m2.start();
        }
    }

    /**
     * implement Runnable.
     */
    private static void createdByWay2() {
        new Thread(new MyRunnable("r1")).start();

        new Thread(new MyRunnable("r2")).start();
    }

    /**
     * implemnt Callable.
     */
    private static void createdByWay3() {
        Callable<Integer> myCallable = new MyCallable();

        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable);

        new Thread(ft).start();

        int sum = 0;
        try {
            sum = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("sum:" + sum);
    }

    private static void createdByWay3_2() {
        Callable<Integer> myCallable = new MyCallable();

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future> futureList = new ArrayList<>();

        for(int i =0; i<5;i++) {
            futureList.add(executorService.submit(myCallable));
        }


        while(true) {
            futureList.stream()
                    .forEach(f -> {
                        try {
                            System.out.println(f.get());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    });
            boolean isDone = futureList.stream()
                    .allMatch(data -> data.isDone());

            if(isDone) {
                System.out.println("all done!");
                break;
            }
        }
    }
}
