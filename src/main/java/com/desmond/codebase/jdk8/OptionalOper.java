package com.desmond.codebase.jdk8;

import com.desmond.codebase.jdk8.vo.TestVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Li.Xiaochuan on 16/4/28.
 */
public class OptionalOper {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        List<Integer> t = Optional.ofNullable(list).orElse(new ArrayList<>());
        System.out.println();
    }
}
