package com.desmond.codebase.thread.pool.custom;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * http://blog.csdn.net/hsuxu/article/details/8985931
 *
 * 线程池类，线程管理器：创建线程，执行任务，销毁线程，获取线程基本信息
 * Created by Li.Xiaochuan on 17/8/11.
 */
public class ThreadPool {
    // 线程池中默认线程的个数为5
    private static int workerNum = 5;

    // 工作线程
    private WorkThread[] workThreads;

    // 已经处理完的任务数量
    private static volatile int finishedTask = 0;

    // 任务队列
    private List<Runnable> taskQueue = new LinkedList<>();


    private static ThreadPool threadPool;

    private ThreadPool() {
        this(5);
    }

    private ThreadPool(int workNum) {
        ThreadPool.workerNum = workNum;
        workThreads = new WorkThread[workNum];

        for(int i =0 ; i < workNum; i++) {
            workThreads[i] = new WorkThread();

            workThreads[i].start();
        }
    }

    // 单例
    public static ThreadPool getThreadPool() {
        return getThreadPool(ThreadPool.workerNum);
    }

    public synchronized static ThreadPool getThreadPool(int workNum) {
        if(workNum < 0) {
            workNum = ThreadPool.workerNum;
        }

        if(threadPool == null) {
            threadPool = new ThreadPool(workNum);
        }

        return threadPool;
    }

    // 把任务加入任务队列,什么时候执行,优线程池管理器决定
    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void execute(Runnable[] tasks) {
        synchronized (taskQueue) {
            for(Runnable r : tasks) {
                taskQueue.add(r);
            }

            taskQueue.notify();
        }
    }

    // 销毁线程池,保证任务先完成
    public void destroy() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < workerNum; i++) {
            workThreads[i].stopWorker();
            workThreads[i] = null;
        }

        threadPool = null;
        taskQueue.clear();
    }

    // 返回工作线程数量
    public int getWorkThreadNumer() {
        return workerNum;
    }

    public int getFinishedTask() {
        return finishedTask;
    }

    // 返回任务队列长度
    public int getWaitTasknumber() {
        return taskQueue.size();
    }

    // 覆盖toString方法，返回线程池信息：工作线程个数和已完成任务个数
    @Override
    public String toString() {
        return "WorkThread number:" + workerNum + "  finished task number:"
                + finishedTask + "  wait task number:" + getWaitTasknumber();
    }

    /**
     * 工作线程
     * Created by Li.Xiaochuan on 17/8/11.
     */
    private class WorkThread extends Thread {
        // 该工作线程是否有效,用于结束该工作线程
        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable r = null;

            while (isRunning) {
                synchronized (taskQueue) { // 获取taskQueue这个实例锁, 从而保证对taskQueue的操作是线程安全的
                    while (isRunning && taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait(20); // 等待20ms 后,才能被notify 或者 notifyAll 唤起
                            // 在被唤起之前,释放taskQueue 的锁,并且不暂用cpu. 待被唤起后,从新进入 获取待锁 的队列,
                            // 获取同步锁之后,进入runnable状态,等待cpu 开始running.
                        } catch (InterruptedException inte) {
                            inte.printStackTrace();
                        }
                    }

                    if(!taskQueue.isEmpty()) {
                        r = taskQueue.remove(0); // 取出任务
                    }
                }

                if(r != null) {
                    r.run(); // 执行任务
                }

                finishedTask ++;
                r = null;
            }
        }

        /**
         * 停止工作, 线程自然执行完run方法,被jvm销毁(GC).
         */
        public void stopWorker() {
            isRunning = false;
        }
    }
}
