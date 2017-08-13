package com.desmond.codebase.reflect;

import java.lang.reflect.Method;

/**
 * Created by Li.Xiaochuan on 17/8/7.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        People people = new People();
        Class clazz = people.getClass();

        Method say = clazz.getDeclaredMethod("talkTo", String.class);
        String name = "Desmond";
        say.invoke(people, name);
    }
}
