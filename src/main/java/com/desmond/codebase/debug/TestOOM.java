package com.desmond.codebase.debug;

import java.util.Vector;

/**
 * Created by presleyli on 2017/6/15.
 */
public class TestOOM {
    private int a;

    private synchronized void print() {
        a +=1;
    }

    public static void main(String[] args) {
        Vector v = new Vector();
        for(int i =0 ; i < 25; i++) {
            v.add(new byte[1024*1024*1024]);
        }
    }
}
