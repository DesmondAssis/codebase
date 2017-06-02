package com.desmond.codebase.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Li.Xiaochuan on 16/3/15.
 */
public class RegxTest {
    public static void main(String[] args) {
        String testStr1 = "·如需预约或有其他疑问请致电小末400-6543-179"
                , testStr2 = "·该活动不支持退款·如需预约或有其他疑问请致电小末400-6543-179",
                testStr3 = "·该活动参加时间开始0天9小时不再支持退款·如d需预约或有其他疑问请致电小末400-6543-179";
        Pattern pattern1 = Pattern.compile("该活动支持随时退款");
        Pattern pattern2 = Pattern.compile("该活动不支持退款");
        Pattern pattern3 = Pattern.compile("该活动参加时间开始.*不再支持退款");

        Matcher matcher1 = pattern1.matcher(testStr1);
        Matcher matcher2 = pattern2.matcher(testStr2);
        Matcher matcher3 = pattern3.matcher(testStr3);
        Matcher matcher11 = pattern1.matcher(testStr3);

        System.out.println(matcher1.find());
        System.out.println(matcher2.find());
        System.out.println(matcher3.replaceFirst("xxx"));
        System.out.println(matcher11.find());
    }
}
