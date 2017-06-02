package com.desmond.codebase.jdk8;

import java.util.Optional;
import java.util.OptionalLong;

/**
 * Created by Li.Xiaochuan on 16/8/7.
 */
public class OptionalTest {

    public static void main(String[] args) {
        Long l1 = 0l, l2 = 2l, l3 = null;

        System.out.println(OptionalLong.of(l1).orElse(100));
        System.out.println(OptionalLong.of(l2).orElse(100));

        System.out.println();
        System.out.println(Optional.ofNullable(l1).orElse(0l));
        System.out.println(Optional.ofNullable(l2).orElse(0l));
        System.out.println(Optional.ofNullable(l3).orElse(0l));
    }

}
