package com.desmond.codebase.sort;

import com.desmond.codebase.number.NumberUtil;
import com.desmond.codebase.sort.vo.Operating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Li.Xiaochuan on 16/1/21.
 */
public class OperatingSort {

    public static void main(String[] args) {
        List<Operating> sortList = getDateFromProd(true);

        sortList.forEach(System.out::println);

        Collections.sort(sortList, new Comparator<Operating>() {
            @Override
            public int compare(Operating o1, Operating o2) {
                String c1 = o1.getChannel() == null ? "" : o1.getChannel();
                String c2 = o2.getChannel() == null ? "" : o2.getChannel();
                int order1 = c1.compareTo(c2);
                if(order1 != 0) {
                    return order1;
                } else {
                    int s1 = NumberUtil.toInt(o1.getSort());
                    int s2 = NumberUtil.toInt(o2.getSort());
                    int order2 = s1 - s2;
                    if(order2 != 0) {
                        return - order2;
                    } else {
                        int ct1 = NumberUtil.toInt(o1.getCreateTime());
                        int ct2 = NumberUtil.toInt(o2.getCreateTime());
                        int order3 = ct1 - ct2;

                        return - order3;
                    }
                }
            }
        });

        System.out.println("\n\n\n\n");

        List<Operating> sortedList = new ArrayList<>(sortList);

        sortedList.forEach(System.out::println);
    }

    private static List<Operating> getDateFromProd(boolean isProd) {
        List<Operating> operatingList = new ArrayList<>(4);
        if(isProd) {
            operatingList.add(new Operating("猴多年货等你领","10898,10896,10894,10893,7502","0",2,1453174845));
            operatingList.add(new Operating("年终豪礼	",",7502,","0",5,1453295046));
            operatingList.add(new Operating("开年大戏，精彩速递",",7502,","0",3,1453192706));
            operatingList.add(new Operating("首届孝心跑","0",	"and-a0",1,1453187353));
            operatingList.add(new Operating("首届孝心跑1","0","and-a1",5,1453187354));
        }

        return operatingList;
    }
}
