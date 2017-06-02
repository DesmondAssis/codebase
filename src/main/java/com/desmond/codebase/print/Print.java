package com.desmond.codebase.print;

import com.desmond.codebase.string.StringUtils;

/**
 * Created by Li.Xiaochuan on 15/9/22.
 */
public class Print {
    public static void print(Object ... args) {
        for(Object arg : args) {
            System.out.print(arg + ",");
        }
    }

    public static void printnb(Object ... args) {
        for(Object arg : args) {
            System.out.println(arg);
        }
    }

    public static void print(String str, Object ... args) {
        if(StringUtils.isNotBlank(str)) {
            for(Object arg : args) {
                str = str.replaceFirst("\\{\\}", java.lang.String.valueOf(arg));
            }
        }

        System.out.println(str);
    }

    public static String format(String str, Object ... args) {
        if(StringUtils.isNotBlank(str)) {
            for(Object arg : args) {
                str = str.replaceFirst("\\{\\}", java.lang.String.valueOf(arg));
            }
        }

        return str;
    }

    public static void printnb(String str, String b) {
        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("1234567891123456t".substring(0,16).length());

    }

    public void pr() {
        print("{},{},{}", this.getClass().getName(), this.getClass().getCanonicalName(), this.getClass().getSimpleName());
    }
}
