package com.desmond.codebase.date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.desmond.codebase.print.Print.*;

/**
 * Created by Li.Xiaochuan on 15/9/2.
 */
public class DateTimeUtils extends DateUtils{
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    public static final String TO_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static class vo {
        private int id;

        public vo(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }


    public static void day() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrowDate = DateUtils.truncate(now.getTime(), Calendar.DAY_OF_MONTH);
        int tomorrow = getTimeInSeconds(tomorrowDate);
        int dayAfter7days = tomorrow + 604799;
        printnb(tomorrow == getTime("2016-01-11", DateTimeUtils.DEFAULT_FORMAT, false));
        printnb(dayAfter7days == getTime("2016-01-17", DateTimeUtils.TO_SECOND_FORMAT, true));
    }

    public static void days() {
        String sd = "", ed = "";
        print("start:" + (1120752000 == getTime("2005-07-08", DateTimeUtils.DEFAULT_FORMAT, false)));
        print("start:" + (1121356800==getTime("2005-07-15", DateTimeUtils.DEFAULT_FORMAT, false)));
        print("start:" + (1121961600==getTime("2005-07-22", DateTimeUtils.DEFAULT_FORMAT, false)));
        printnb("start:" + (1406822400 == getTime("2014-08-01", DateTimeUtils.DEFAULT_FORMAT, false)));

        printnb("end1:" + (1451404800 == getTime("2015-12-30 00:00:00", DateTimeUtils.TO_SECOND_FORMAT, false)));
        printnb("end2:" + (1451491199 == getTime("2015-12-30", DateTimeUtils.TO_SECOND_FORMAT, true)));
        print("start:" + (1121961600==getTime("2005-07-22", DateTimeUtils.DEFAULT_FORMAT, false)));
        print("start:" + (1406822400==getTime("2014-08-01", DateTimeUtils.DEFAULT_FORMAT, false)));
    }

    public static void test1() {
        List<vo> s = new ArrayList<>();
        s.add(new vo(0));
        s.add(new vo(1));
        s.add(new vo(2));
        s.add(new vo(3));
        vo v = s.get(2);
        s.remove(2);
        s.add(v);
        s.size();



        String timeTxt = "2015年12月11日 - 2015年12月13日 09:00-17:01";
        List<String> timeList = Arrays.asList(timeTxt.split("，"));


//        testGetXYearsXMonthsXDays3();
//        System.out.print(getCurrentTimeInSeconds());
//        print(getCurrentWeekend());

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 8);
        c1.set(Calendar.DAY_OF_MONTH, 1);
        print(new SimpleDateFormat("yyyy-MM-dd").format(c1.getTime()));
        printnb(getTimeInSeconds(c1.getTime()));

        printnb("\n\nvvv " + getCurrentTimeInSeconds());
        printnb("\n\n\n\n\n");
        printnb(",7502,10893,10896,10898,".contains("10896"));
    }

    public static int getTime(String timeStr, String format, boolean isAppendhms) {
        int time = 0;
        if(StringUtils.isNotBlank(timeStr)) {
            timeStr += isAppendhms ? " 23:59:59" : "";
            Date date = parse(timeStr, format);
            if(date != null) {
                time = getTimeInSeconds(date);
            }
        }

        return time;
    }

    public static String format(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TO_SECOND_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String formatNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TO_SECOND_FORMAT);
        return simpleDateFormat.format(new Date());
    }

    public static Date parse(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            //throw new RuntimeException(e);
        }
        return null;
    }

    public static int getDayStartTime() {
        return getDayStartTime(null);
    }

    /**
     * 获取指定时间的当天开始时间(0:0:0)<br/>
     * 如果未指定，默认为当前天的开始时间
     *
     * @param date
     * @return 时间：秒
     */
    public static int getDayStartTime(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return getTimeInSeconds(c.getTime());
    }

    public static int getTimeInSeconds(Date date) {
        long milliseconds = date.getTime();

        return (int)(milliseconds / 1000l);
    }

    public static int getCurrentTimeInSeconds() {
        long milliseconds = new Date().getTime();

        return (int)(milliseconds / 1000l);
    }

    /**
     * 获取本周六的日期格式
     * @return
     */
    public static String getCurrentWeekend() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    public static void testGetXYearsXMonthsXDays2() {
        // 2013.09.02
//        Date d1 = new Date(2013-1900,8,6);
//        long ts = d1.getTime();
//        int t = (int)(ts / 1000l);
//        System.out.println(d1+"="+getXYearsXMonthsXDays2(t));
//
//        // 2014.09.06
//        Date d2 = new Date(2014-1900,8,1);
//        t = (int)(d2.getTime() / 1000l);
//        System.out.println(d2+"="+getXYearsXMonthsXDays2(t));
//
//        // 2015.06.06
//        Date d3 = new Date(2015-1900,5,6);
//        t = (int)(d3.getTime() / 1000l);
//        System.out.println(d3+"="+getXYearsXMonthsXDays2(t));

        // 2015.08.22
        Date d4 = new Date(2013-1900,11,22);
        int t = (int)(d4.getTime() / 1000l);
        System.out.println(d4+"="+getXYearsXMonthsXDays2(t));
    }

    public static void testGetXYearsXMonthsXDays3() {
        // 2013.09.02
        Date d1 = new Date(2013-1900,8,1);
        long ts = d1.getTime();
        System.out.println(d1+"="+getXYearsXMonthsXDays3(ts));

        // 2014.09.06
        Date d2 = new Date(2014-1900,8,6);
        System.out.println(d2+"="+getXYearsXMonthsXDays3(d2.getTime()));

        // 2015.06.06
        Date d3 = new Date(2015-1900,5,6);
        System.out.println(d3+"="+getXYearsXMonthsXDays3(d3.getTime()));

        // 2015.08.22
        Date d4 = new Date(2015-1900,7,22);
        System.out.println(d4+"="+getXYearsXMonthsXDays3(d4.getTime()));

        System.out.println(d4+"="+getXYearsXMonthsXDays3(new Date(2015 - 1900, 8, 1).getTime()));
    }

    public static void testGetXYearsXMonthsXDays() {
        // 2013.09.02
        Date d1 = new Date(2013-1900,8,6);
        long ts = d1.getTime();
        int t = (int)(ts / 1000l);
        System.out.println(d1+"="+getXYearsXMonthsXDays(t));

        // 2014.09.06
        Date d2 = new Date(2014-1900,8,6);
        t = (int)(d2.getTime() / 1000l);
        System.out.println(d2+"="+getXYearsXMonthsXDays(t));

        // 2015.06.06
        Date d3 = new Date(2015-1900,5,6);
        t = (int)(d3.getTime() / 1000l);
        System.out.println(d3+"="+getXYearsXMonthsXDays(t));

        // 2015.08.22
        Date d4 = new Date(2015-1900,7,22);
        t = (int)(d4.getTime() / 1000l);
        System.out.println(d4+"="+getXYearsXMonthsXDays(t));
    }

    public static String getXYearsXMonthsXDays(int createTime) {
        Calendar now = Calendar.getInstance();
        Calendar createCal = Calendar.getInstance();
        long t = now.getTimeInMillis();
        long time = createTime * 1000l;
        createCal.setTimeInMillis(time);
        int nowYear = now.get(Calendar.YEAR);
        int pastYear = createCal.get(Calendar.YEAR);


        createCal.add(Calendar.YEAR, 1);
        if(!createCal.after(now)) {
            return (nowYear - pastYear) + " 年";
        } else {
            createCal.add(Calendar.YEAR, -1);
            createCal.add(Calendar.MONTH, 1);

            if(!createCal.after(now)) {
                createCal.add(Calendar.MONTH, -1);
                int nowMonth = now.get(Calendar.MONTH);
                int pastMonth = createCal.get(Calendar.MONTH);

                nowMonth = nowYear > pastYear ? nowMonth + 11 : nowMonth;

                return (nowMonth - pastMonth) + " 月";
            } else {
                int nowDay = now.get(Calendar.DAY_OF_MONTH);
                int pastDay = createCal.get(Calendar.DAY_OF_MONTH);
                // TODO,需要处理跨月的情况
                if(nowDay - pastDay > 1) {
                    return (nowDay - pastDay) + " 天";
                } else {
                    return "";
                }
            }

        }
    }

    public static String getXYearsXMonthsXDays2(int createTime) {
        long duration = Calendar.getInstance().getTimeInMillis() - createTime * 1000l;
//        return DurationFormatUtils.formatDuration(duration, "y - M - d");
        return DurationFormatUtils.formatPeriod(createTime * 1000l, Calendar.getInstance().getTimeInMillis(), " - M - d");
    }

    public static String getXYearsXMonthsXDays3(final long startMillis) {

        // from DurationFormatUtils.formatPeriod
        final Calendar start = Calendar.getInstance();
        start.setTime(new Date(startMillis));
        final Calendar end = Calendar.getInstance();
        end.setTime(new Date());

        // initial estimates
        int seconds = end.get(Calendar.SECOND) - start.get(Calendar.SECOND);
        int minutes = end.get(Calendar.MINUTE) - start.get(Calendar.MINUTE);
        int hours = end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY);
        int days = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
        int months = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int years = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);

        // each initial estimate is adjusted in case it is under 0
        while (seconds < 0) {
            seconds += 60;
            minutes -= 1;
        }
        while (minutes < 0) {
            minutes += 60;
            hours -= 1;
        }
        while (hours < 0) {
            hours += 24;
            days -= 1;
        }

        while (days < 0) {
            days += start.getActualMaximum(Calendar.DAY_OF_MONTH);
            months -= 1;
            start.add(Calendar.MONTH, 1);
        }

        while (months < 0) {
            months += 12;
            years -= 1;
        }

        String result = "";
        if(years >= 1) {
            result = years + " 年";
        } else if(months >= 1) {
            result = months + " 月";
        } else if(days >= 1) {
            result = days + " 天";
        }

        return result;
    }

    /**
     * 获取距离当前时间的长度
     * >= 1年显示 x 年，超过1月 显示 x 月，超过1天 显示 x 天。否则空
     * @param startSeconds
     * @return
     */
    public static String getAge(final long startSeconds) {
        // from DurationFormatUtils.formatPeriod
        final Calendar start = Calendar.getInstance();
        long startMillis = startSeconds * 1000l;
        start.setTime(new Date(startMillis));
        final Calendar end = Calendar.getInstance();
        end.setTime(new Date());

        // initial estimates
        int seconds = end.get(Calendar.SECOND) - start.get(Calendar.SECOND);
        int minutes = end.get(Calendar.MINUTE) - start.get(Calendar.MINUTE);
        int hours = end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY);
        int days = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
        int months = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int years = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);

        // each initial estimate is adjusted in case it is under 0
        while (seconds < 0) {
            seconds += 60;
            minutes -= 1;
        }
        while (minutes < 0) {
            minutes += 60;
            hours -= 1;
        }
        while (hours < 0) {
            hours += 24;
            days -= 1;
        }

        while (days < 0) {
            days += start.getActualMaximum(Calendar.DAY_OF_MONTH);
            months -= 1;
            start.add(Calendar.MONTH, 1);
        }

        while (months < 0) {
            months += 12;
            years -= 1;
        }

        String result = "";
        if(years >= 1) {
            result = years + " 年";
        } else if(months >= 1) {
            result = months + " 月";
        } else if(days >= 1) {
            result = days + " 天";
        }

        return result;
    }

    /**
     * 后去两个日期之间的天数
     * @param dateStr1
     * @param dateStr2
     * @return
     */
    public static int getDays(String dateStr1, String dateStr2) {
        Date d1 = parse(dateStr1, DEFAULT_FORMAT);
        Date d2 = parse(dateStr2, DEFAULT_FORMAT);

        return (int) ((d2.getTime() - d1.getTime() ) /(1000l * 3600 * 24));
    }

    public static void main(String[] args) {
        System.out.println(getDays("2016-08-30", "2016-12-13"));
    }
}
