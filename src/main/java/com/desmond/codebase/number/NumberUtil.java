package com.desmond.codebase.number;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;

import static com.desmond.codebase.print.Print.print;
import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/10/21.
 */
public class NumberUtil extends NumberUtils{
    public static void main(String[] args) {
//        String s = "121.2195600000,".replaceAll("\\,", "");
//        System.out.println(s);
//        System.out.println( NumberUtils.toDouble(s));

//        for (int i = 0; i < 20; i++) {
//            float d = convertYuan(1);
//            System.out.println(d);
//        }

//        float lowPrice = 1;
//        float highPrice = 4;
        final BigDecimal lowestPrice = BigDecimal.valueOf(1f),
                highestPrice = BigDecimal.valueOf(4f);

        int minDc = 2;
        int maxDc = 50;

        BigDecimal oneHundred = BigDecimal.valueOf(100),
                lowDc = BigDecimal.valueOf(minDc).divide(oneHundred),
                highDc = BigDecimal.valueOf(maxDc).divide(oneHundred);

        BigDecimal tmp = lowestPrice.divide(oneHundred);
        BigDecimal tmp2 = tmp.multiply(lowDc);
        float lowPrice = tmp2.floatValue();
        float highPrice = highestPrice.divide(oneHundred).multiply(highDc).floatValue();

        java.text.DecimalFormat df=new java.text.DecimalFormat("#.####");
        System.out.println(df.format(lowPrice) + "," + df.format(highPrice));

        Integer i = 10896;
        System.out.println(i == 10896);

        System.out.println("\n\n\n\n\n");
        System.out.println(Math.floor(1.2));
        System.out.println(Math.floor(1.5));
        System.out.println(Math.floor(1.9999));
        System.out.println(BigDecimal.valueOf(2).intValue());

    }

    public static Float convertYuan(Integer price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        return bigDecimal.divide(new BigDecimal(100)).floatValue();
    }

    private static void printNum(int size) {
        int selfSize = (int) Math.round(size * 0.4);
        printnb(size + "=== " + selfSize + ", " + (size - selfSize));
    }

    /**
     * 获取指定小数点位的数字(四舍五入)
     * e.g. 12.345 保留2位 -> 12.25
     * @param num double 型数字
     * @param digits 保留小数点的位数, digits = 0, 则返回原值
     * @return
     */
    public static String getDoubleByDigits(double num, int digits) {
        if(digits > 0) {
            StringBuilder formatSb = new StringBuilder(",####.");
            for(int i = 0; i < digits; i++) {
                formatSb.append("#");
            }

            return new DecimalFormat(formatSb.toString()).format(num);
        }

        return num + "";
    }

    public static int toInt(final Integer num) {
        return toInt(num, 0);
    }

    public static int toInt(final Integer num, int defaultValue) {
        return num != null ? num : defaultValue;
    }

}
