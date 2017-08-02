package com.desmond.codebase.debug;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by presleyli on 2017/7/3.
 */
@JsonIgnoreProperties
public class TestClass {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        Class<TestClass> c = (Class<TestClass>) testClass.getClass();
        Method[] methods = c.getMethods();
        Annotation[] annotations = c.getAnnotations();
        for(Annotation ann : annotations) {
            System.out.println(ann);
        }
    }


    private void test() {

    }
}
