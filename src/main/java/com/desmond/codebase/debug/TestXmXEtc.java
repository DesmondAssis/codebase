package com.desmond.codebase.debug;

/**
 * Created by presleyli on 2017/6/28.
 */
public class TestXmXEtc {
    public static void main(String[] args) {
        byte[] b = new byte[1*1024*1024];
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024.0 + "M");

        System.out.print("free memeory=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024.0 + "M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024.0 + "M");
    }
}
