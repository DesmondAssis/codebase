package com.desmond.codebase.pagination;

import java.util.ArrayList;
import java.util.List;

import static com.desmond.codebase.print.Print.*;
/**
 * Created by Li.Xiaochuan on 16/5/19.
 */
public class PaginationMain {

    public static void main(String[] args) {
        int minId = 1, maxId = 104516;
        int step = (maxId - minId)/ 2;
        System.out.println(step);
        for(int i = minId; i <= maxId;) {
            print("{} : {}", i, i + step - 1);

            i = i + step;
        }

    }

    private static void test1() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 17; i++)
            list.add(i);

        List<Integer[]> list1 = getProperPagination(17, 2);
        list1.stream().forEach(pg -> {
            int from = pg[0] >= 0 ? pg[0] : 0;
            int to = pg[1] <= list.size() ? pg[1] : list.size();
            List<Integer> newList = list.subList(from, to);
            newList.forEach(System.out::println);
        });
    }


    private static List<Integer[]> getProperPagination(int count, int step) {
        List<Integer[]> resultList = new ArrayList<>();
        if(count > 0) {
            if(step > count) {
                resultList.add(new Integer[] {0, count});
            } else {
                for(int i = 0; i < count;) {
                    int fromIdx = i;
                    i += step;

                    int toIdx = Math.min(i, count);
                    resultList.add(new Integer[] {fromIdx, toIdx});
                }
            }
        }

        return resultList;
    }
}
