package com.desmond.codebase.debug;

/**
 * Created by presleyli on 2017/6/28.
 */
public class TestPrintGc {
    public static void main(String[] args) {
        byte[] b = null;
        for(int i = 0; i< 1;i++) {
            b =new byte[1*1024*1024];
        }
    }
}
