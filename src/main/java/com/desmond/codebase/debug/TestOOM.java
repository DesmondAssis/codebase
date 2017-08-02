package com.desmond.codebase.debug;

import org.apache.hadoop.yarn.util.ApplicationClassLoader;

import java.util.HashMap;
import java.util.Map;
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
        for(int i =0 ; i < 1; i++) {
            v.add(new byte[10*1024*1024]);
        }
    }
}
