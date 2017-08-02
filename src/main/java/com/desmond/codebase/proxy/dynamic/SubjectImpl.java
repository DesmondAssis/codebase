package com.desmond.codebase.proxy.dynamic;

/**
 * Created by presleyli on 2017/7/26.
 */
public class SubjectImpl implements SubjectIntf {
    @Override
    public void doSomething(String name) {
        System.out.println("name:" + name);
    }
}
