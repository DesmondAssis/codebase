package com.desmond.codebase.debug.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by presleyli on 2017/6/16.
 */
public class TestClassLoader {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        test2();
    }

    private static void test2() throws ClassNotFoundException {
        OrderClassLoader orderClassLoader = new OrderClassLoader("/Users/presleyli/java/desmond/codebase/build/classes/main/");
        Class clazz = orderClassLoader.loadClass("com.desmond.codebase.debug.loader.TestClassLoader");
        System.out.println(clazz.getClassLoader());

        System.out.println("===class loader tree");
        ClassLoader cls = orderClassLoader;
        while(cls != null) {
            System.out.println(cls);
            cls = cls.getParent();
        }
    }

    private static void test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        for (int i = 0;i<10000;i++) {
            HelloLoader loader = new HelloLoader();

            Method m = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
            m.setAccessible(true);

            m.invoke(loader, null);
            m.setAccessible(false);

            System.gc();
        }
    }
}
