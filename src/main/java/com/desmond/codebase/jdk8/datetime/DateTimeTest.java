package com.desmond.codebase.jdk8.datetime;

import com.desmond.codebase.date.DateTimeUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.*;
import java.util.*;

import static com.desmond.codebase.print.Print.print;
import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 16/4/12.
 */
public class DateTimeTest {

    public static void main(String[] args) {
        sumMonthActiveNum(1472659200);
        sumMonthActiveNum(1469980800);
        sumMonthActiveNum(1467302400);
        sumMonthActiveNum(1464710400);
        sumMonthActiveNum(1462032000);
        sumMonthActiveNum(1459440000);

    }

    public static void sumMonthActiveNum(Integer month){
        long startTime = month;
        Date date = new Date(startTime*1000l);
        Date newDate = DateUtils.addMonths(date, 1);

        long endTime = newDate.getTime()/1000l;

        System.out.println("start: " + new Date(startTime*1000l) + "," + new Date(endTime*1000l));

    }
}
