package com.desmond.codebase.proxy.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by presleyli on 2017/7/26.
 */
public class Test {
    public static void main(String[] args) {
        SubjectInvocationHandlerImpl invocationHandler = new SubjectInvocationHandlerImpl(new SubjectImpl());

        SubjectIntf subject = (SubjectIntf) Proxy.newProxyInstance(SubjectIntf.class.getClassLoader(),
                new Class[] {SubjectIntf.class}, invocationHandler);

        Class cl = subject.getClass();
        System.out.println(cl);
        subject.doSomething("testing");
        createProxyClassFile();
    }

    public static void createProxyClassFile()
    {
        String name = "subject";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { SubjectIntf.class } );
        try
        {
            String path = "/Users/presleyli/java/desmond/codebase/src/main/java/com/desmond/codebase/proxy/dynamic/";
            FileOutputStream out = new FileOutputStream( path + name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
