package com.desmond.codebase.file;

import com.desmond.codebase.date.DateTimeUtils;
import com.desmond.codebase.exception.NotEqualsException;
import com.desmond.codebase.number.NumberUtil;
import com.desmond.codebase.print.Print;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

import static com.desmond.codebase.print.Print.print;
import static com.desmond.codebase.print.Print.printnb;

/**
 * 文件 IO类.
 * Created by Li.Xiaochuan on 15/10/15.
 */
public class FileUtils {
    public static String readToString(File file, String split) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str = null;
            while ((str = br.readLine()) != null) {
                if(StringUtils.isNotBlank(split)) {
                    sb.append(str).append(split);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();;
        }

        return sb.toString();
    }

    public static void getPoi() {
        String fileName = "/Users/gk/java/tmp/poi-final.csv";
        String content = readToString(new File(fileName), "@@");
        String[] lineArr = content.split("@@");
        if (lineArr.length != 48) {
            throw new RuntimeException("error: 48");
        }
        int sn = 1;
        for(String line : lineArr) {
            String[] strArr = line.split(",");
            if (strArr.length != 7) {
                throw new RuntimeException("error: 7");
            }

            StringBuilder sb = new StringBuilder();
            sb.append(strArr[0]).append("_").append(getCityName(strArr[0]))
                    .append("_").append(sn++).append("=")
                    .append(strArr[2]).append("@@")
                    .append(strArr[5]).append("@@")
                    .append(strArr[6]).append("@@")
                    .append(strArr[4]).append("@@")
                    .append(strArr[3]);
            // 0_0_sn=2@@5@@6@@4@@3
            //10896_shanghai_1=123456@@121.12@@32.232@@愚园东路28号@@东海广场3号楼1

            printnb(sb.toString());
        }
    }

    private static String getCityName(String cityIdStr) {
        switch (cityIdStr) {
            case "10896" : return "shanghai";
            case "10894" : return "guangzhou";
            case "10893" : return "shenzhen";
            case "10898" : return "beijing";
            case "7502" : return "hangzhou";
        }

        return "";
    }

    private static String getProperFileName(String originFileName, int limitLength) {
        String fileName = originFileName;
        if(StringUtils.isNotBlank(originFileName)) {
            if(originFileName.length() > limitLength) {
                int dotIdx = originFileName.lastIndexOf(".");
                if(dotIdx > 0) {
                    String preFileName = originFileName.substring(0, dotIdx);
                    String extension = originFileName.substring(dotIdx);
                    fileName = preFileName.substring(0, limitLength - extension.length()) + extension;
                } else {
                    fileName = originFileName.substring(originFileName.length() - limitLength -1);
                }
            }
        }

        return fileName;
    }

    public static void main(String[] args) throws IOException {
        List<String> l = FileUtils.getContentByLine("/Users/presleyli/tmp/project_ids");
        StringBuilder s = new StringBuilder();

        l.stream()
                .filter(data -> StringUtils.isNotBlank(data))

                .forEach(str -> {
                    System.out.println();
                    s.append(str).append(",");
                });

        System.out.println(s.toString());
    }

    private static void check(Calendar cal, Map<String, List<String>> jobMap) {
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        if(mins < 30) {
            mins = 0; // 检测 hour:00
        } else {
            mins = 30; // 检测 hour:30
        }

        String key = hour + "_" + mins;

        Date startTime = cal.getTime(),
                endTime = cal.getTime();

        // 检测上一个小时后35分钟
        if(mins == 0) {
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(cal.getTime());
            cStart.add(Calendar.HOUR_OF_DAY, -1);
            cStart.set(Calendar.MINUTE, 30);

            Calendar cEnd = Calendar.getInstance();
            cEnd.setTime(cal.getTime());
            cEnd.set(Calendar.MINUTE, 0);

            startTime = cStart.getTime();
            endTime = cEnd.getTime();
        } else {
            // 检测本小时内0~25分钟
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(cal.getTime());
            cStart.set(Calendar.MINUTE, 0);

            Calendar cEnd = Calendar.getInstance();
            cEnd.setTime(cal.getTime());
            cEnd.set(Calendar.MINUTE, 30);

            startTime = cStart.getTime();
            endTime = cEnd.getTime();
        }

        if(jobMap.get(key) != null) {
            String reuslt = jobMap.get(key).stream().reduce((s,a) -> s+",\n"+a).orElse("");
            System.out.println("key:" + key + "\n" + reuslt + "\n" + startTime + "," + endTime);

            System.out.println("\n\n");
        }
    }

    private static void diff(List<String> old, List<String> news) {
        final List<Integer> list = new ArrayList<>();
        old.forEach(source -> {
            if(!news.contains(source)) {
                printnb(source);
                list.add(1);
            }
        });

        printnb("count: ", list.size());

    }

    public static List<String> getContentByLine(String fileName) throws IOException {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String lineStr = null;
        while ( (lineStr = bufferedReader.readLine()) != null ) {
            stringList.add(lineStr.trim());
        }

        return stringList;
    }

    public static void assertEquals(int expect, int act) throws NotEqualsException{
        if(expect != act) {
            throw new NotEqualsException(Print.format("expect: {}, act: {}", expect, act));
        }
    }
    public static void writeToFile(String file, String content) {
        try {
            BufferedWriter writers = new BufferedWriter(new FileWriter(file, false));
            writers.write(content);
            writers.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
