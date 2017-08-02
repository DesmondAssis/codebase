package com.desmond.codebase.thread;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by presleyli on 2017/6/27.
 */
public class ExecutorThreadPool {
    public static int add(int a, int b) {
        int c = 0;
        c = a+b;
        return c;
    }
}
