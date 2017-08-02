package com.desmond.codebase.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by presleyli on 2017/7/26.
 */
public class SubjectInvocationHandlerImpl implements InvocationHandler{
    private SubjectIntf subject;

    public SubjectInvocationHandlerImpl(SubjectIntf subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke....");

        Object resultObj = method.invoke(subject, args);

        System.out.println("after invoke....");

        return resultObj;
    }
}
