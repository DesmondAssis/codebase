package com.desmond.codebase.random;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

/**
 * 真随机，伪随机
 * Created by Li.Xiaochuan on 15/9/16.
 */
public class RandomUtil {

    public static void main(String[] args) {
//        RandomRunnable randomRunnable = new RandomRunnable();
//        for(int i = 0; i < 10; i++) {
//            Thread t = new Thread(randomRunnable, "t"+i);
//            t.start();
//        }
        for(int i=0;i<2000;i++) {
            if (new Random().nextInt(10) == 0) {
                throw new RuntimeException("test");
            }
        }
    }

    static void print(Object arg) {
        System.out.println(arg);
    }


}

class RandomRunnable implements Runnable {

    public void run() {

        RandomUtil.print(Thread.currentThread().getName() + ":" + RandomUtils.nextInt(10, 99));
    }
}
