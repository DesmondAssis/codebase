package com.desmond.codebase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Li.Xiaochuan on 16/9/8.
 */
public class BlockedQueueTest {
    public static void main(String[] args) {
        final BlockingQueue queue = new SynchronousQueue<>();

        for(int i=0;i<2;i++){
            new Thread(){
                public void run(){
                    while(true){
                        try {
                            System.out.println(Thread.currentThread().getName() + "准备放数据!");
                            queue.put(System.currentTimeMillis());
                            System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
                                    "队列目前有" + queue.size() + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }.start();
        }

        for(int i = 0; i < 5 ; i++) {
            new Thread(){
                public void run(){
                    while(true){
                        try {
                            //将此处的睡眠时间分别改为100和1000，观察运行结果
                            Thread.sleep(5000);
                            System.out.println(Thread.currentThread().getName() + "准备取数据!");
                            System.out.println(queue.take());
                            System.out.println(Thread.currentThread().getName() + "已经取走数据，" +
                                    "队列目前有" + queue.size() + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }.start();
        }
    }
}