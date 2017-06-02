package com.desmond.codebase.string;

import java.util.ArrayList;
import java.util.List;

import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/11/5.
 */
public class SplitMain {

    public static void main(String[] args) {
        String s = "0", s1 = ",7502,10896,", s2 = ",7502,10893,10896,10898,";
        List<String> l = splitByDelimiter(s, ",");
        List<String> l1 = splitByDelimiter(s1, ",");
        List<String> l2 = splitByDelimiter(s2, ",");
        printnb(l.contains("10896"));
        printnb(l1.contains("222"));
        printnb(l2.contains("10896"));

        String ss = "aaa||bbb";
        String[]  ssArr = ss.split("||");
        String[] ss1Arr = ss.split("\\|\\|");
        printnb(ss1Arr);

    }

    public static List<String> splitByDelimiter(String str, String delimiter) {
        List<String> splitList = new ArrayList<>();
        if(StringUtils.isNotBlank(str)) {
            String[] arr = str.split(delimiter);
            for(String s : arr) {
                if(StringUtils.isNotBlank(s)) {
                    splitList.add(s);
                }
            }
        }

        return splitList;
    }
}
