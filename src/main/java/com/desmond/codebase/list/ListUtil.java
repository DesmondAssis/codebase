package com.desmond.codebase.list;

import com.desmond.codebase.list.vo.Activity;
import com.desmond.codebase.list.vo.Shop;
import com.desmond.codebase.string.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/11/23.
 */
public class ListUtil {
    public static void main(String[] args) {
        getProperPagination(5, 2);
        getProperPagination(10, 5);
        getProperPagination(3, 5);
        getProperPagination(300, 300);
    }

    private static List<Long[]> getProperPagination(long count, long step) {
        List<Long[]> resultList = new ArrayList<>();
        if(step > count) {
            resultList.add(new Long[] {0l, count});
        } else {
            for(long i = 0; i < count;) {
                long fromIdx = i;
                i += step;

                long toIdx = Math.min(i, count);
                resultList.add(new Long[] {fromIdx, toIdx});
            }
        }

        return resultList;
    }

    public static void outOrder(List<Activity> list) {
        LinkedList<Activity> linkedList = new LinkedList<>(list);
        List<Activity> addList = new ArrayList<>();
        // 0 1 2 3 4
        // 0 2 3 4 1
        // 0 1 3 4 2
        int k = 0;
        for(int i = 1; i < linkedList.size(); i++) {
            if(linkedList.get(i).getShop().getId() == linkedList.get(k).getShop().getId()) {
                Activity activity = linkedList.remove(i);
                addList.add(activity);
                i--;
            } else {
                k = i;
            }
        }
        linkedList.addAll(addList);

        for(Activity a : linkedList) {
            printnb(a.getId());
        }
    }

    private static List<Activity> init() {
        List<Activity> activityList = new ArrayList<>();
        activityList.add(new Activity(43701, new Shop(30667)));
        activityList.add(new Activity(43708, new Shop(30668)));
        activityList.add(new Activity(43537, new Shop(30667)));
        activityList.add(new Activity(43707, new Shop(30668)));
        activityList.add(new Activity(43709, new Shop(30668)));
        activityList.add(new Activity(43706, new Shop(30668)));
        activityList.add(new Activity(43257, new Shop(23280)));
        activityList.add(new Activity(43498, new Shop(30667)));
        activityList.add(new Activity(43507, new Shop(23280)));
        activityList.add(new Activity(38320, new Shop(28224)));
        activityList.add(new Activity(43316, new Shop(30667)));
        activityList.add(new Activity(41805, new Shop(29242)));
        activityList.add(new Activity(43710, new Shop(30668)));
        activityList.add(new Activity(43186, new Shop(27016)));
        activityList.add(new Activity(34012, new Shop(27085)));
        activityList.add(new Activity(38330, new Shop(28224)));
        activityList.add(new Activity(38385, new Shop(28228)));
        activityList.add(new Activity(37984, new Shop(26471)));
        activityList.add(new Activity(38387, new Shop(28228)));
        activityList.add(new Activity(38006, new Shop(28224)));
        activityList.add(new Activity(38111, new Shop(26471)));
        activityList.add(new Activity(37950, new Shop(28103)));
        activityList.add(new Activity(37057, new Shop(27016)));
        activityList.add(new Activity(36290, new Shop(27016)));
        activityList.add(new Activity(36122, new Shop(27114)));
        activityList.add(new Activity(36158, new Shop(27114)));
        activityList.add(new Activity(32691, new Shop(27114)));
        activityList.add(new Activity(32819, new Shop(27114)));
        activityList.add(new Activity(33183, new Shop(27114)));
        activityList.add(new Activity(32548, new Shop(24390)));
        activityList.add(new Activity(30290, new Shop(23518)));
        activityList.add(new Activity(43054, new Shop(26812)));
        activityList.add(new Activity(42982, new Shop(30416)));
        activityList.add(new Activity(37257, new Shop(23610)));
        activityList.add(new Activity(36370, new Shop(27770)));
        return activityList;
    }
}
