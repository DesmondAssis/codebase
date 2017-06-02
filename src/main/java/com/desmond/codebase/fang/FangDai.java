package com.desmond.codebase.fang;

import com.desmond.codebase.file.FileUtils;
import com.desmond.codebase.number.NumberUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Li.Xiaochuan on 17/2/22.
 */
public class FangDai {
    private static final String filePath = "/Users/gk/java/desmond/Desmond-code-base/src/main/java/com/desmond/codebase/fang/fd.csv";
    public static void main(String[] args) throws IOException {
        System.out.println(fuliCal(60, 0.1f, 30));
        System.out.println(fuliCal(60, 0.1f, 20));
    }

    private static void fangdai() throws IOException {
        double money = 600000; // 总额
        float lv = 0.1f; // 利率
        List<String> fangCsv = FileUtils.getContentByLine(filePath);
        Map<Integer, Float> moneyMap = new TreeMap<>();
        fangCsv.forEach(str -> {
            String[] arr = str.split(",");
            int month = NumberUtil.toInt(arr[0]);
            float m = NumberUtil.toFloat(arr[1]);

            moneyMap.put(month, m);
        });

        Map<Integer, Float> yearMap = new TreeMap<>();
        moneyMap.forEach((k,v)-> {
            int ak = k%12 == 0 ? k/12 : k/12 + 1;

            if(!yearMap.containsKey(ak)) {
                yearMap.put(ak, 0f);
            }

            yearMap.put(ak, yearMap.get(ak) + v);
        });

        double m = money;
        for (Map.Entry<Integer, Float> entry : yearMap.entrySet()) {
            m = couputeSurplus(m, lv, entry.getValue());
            System.out.println(entry.getKey() + "->" + NumberUtil.getDoubleByDigits(m, 2));
        }
    }

    private static double couputeSurplus(double money, float lv, double debt) {
        double f = fuliCal(money, lv, 1);

        return f - debt;
    }

    /**
     *
     * @param money 总金额
     * @param sy 年利率
     * @param year 存放年限
     * @return
     */
    private static double fuliCal(double money, float sy, int year) {
        return money * Math.pow((1+sy), year);
    }
}
