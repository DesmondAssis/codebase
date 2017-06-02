package com.desmond.codebase.hash;

import com.desmond.codebase.security.MD5;

import java.util.*;

public class DataVisitConsistentHashUtil {

    private final static HashFunction hashFunction = new HashFunction();
    private final static int numberOfReplicas = 100;
    private final static SortedMap<Integer, Integer> circle = new TreeMap();

    static {
        for (int k = 0; k < 16; k++) {
            for (int i = 0; i < numberOfReplicas; i++) {
                circle.put(hashFunction.hash(k + "" + i), k);
            }
        }
    }

    public static Integer get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, Integer> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    private static class HashFunction {
        int hash(Object key) {
            return MD5.calculate(key.toString()).hashCode();
        }
    }

}