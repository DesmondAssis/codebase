package com.desmond.codebase.thread;

import com.desmond.codebase.date.DateTimeUtils;

/**
 *
 * Created by presleyli on 2017/6/27.
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(true){
                System.out.println(DateTimeUtils.getCurrentTimeInSeconds());
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(Thread.currentThread().isDaemon());
        thread.setDaemon(true);
        thread.start();

//        try {
//            Thread.sleep(1000 * 3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("exit");
    }
}
