package com.desmond.codebase.jdk8;

import java.util.*;
import java.util.stream.Collectors;

import static com.desmond.codebase.print.Print.print;
import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 16/1/15.
 */
public class StreamTest {
    public static void main(String[] args) {
//        sort();
        collect();
        listOrder();
    }

    private static void listOrder() {
        List<Integer> list = Arrays.asList(5, 3, 1, 2, 100, 67);
        for (int i = 0; i < 30; i++) {
            list.stream().forEach(source -> print(source));
            printnb("");
            list.forEach(source -> print(source));
            printnb("");
            printnb("");
            printnb("");
        }
    }

    private static void collect() {
        List<Integer> resultList = new ArrayList<>();
        List<Integer> testList = Arrays.asList(1,2,3,4,5,6,7);
        testList.stream().forEach(item -> testApply(item, resultList));
        resultList.forEach(item -> System.out.println(item));
        System.out.println();
        testList.stream().filter(item -> item % 2 == 0)
                .collect(Collectors.toList()).forEach(item -> System.out.print(item));
    }

    private static void testApply(int item, List<Integer> list) {
        if(item % 2 == 0) {
            list.add(10000);
        }
    }

    private static void sort() {
        List<String> stringList = new ArrayList<>();
        List<String> tableNames = Arrays.asList("data_visit_20161215", "data_visit_20161201"
                , "data_visit_20160112", "data_visit_20150230", "data_visit_20160530", "data_visit_user", "data_visit_archive");
        tableNames.stream().forEach(table -> System.out.print(table + " "));
        tableNames.stream()
                .filter(tableName -> tableName.matches("data_visit_\\d{8}"))
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        String o1N = o1.substring(o1.lastIndexOf("_") + 1);
                        String o2N = o2.substring(o2.lastIndexOf("_") + 1);
                        return o1N.compareTo(o2N);
                    }
                }).forEach(table -> stringList.add(table));
        System.out.println("");
        stringList.stream().forEach(table -> System.out.print(table + " "));
    }
}
