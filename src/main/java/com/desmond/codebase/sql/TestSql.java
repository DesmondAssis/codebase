package com.desmond.codebase.sql;

import com.desmond.codebase.file.FileUtils;
import com.desmond.codebase.number.NumberUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Li.Xiaochuan on 16/3/29.
 */
public class TestSql {

    public static void main(String[] args) throws IOException, Exception {
        String file = "/Users/gk/Downloads/马甲-感兴趣.csv";
        List<String> strList = FileUtils.getContentByLine(file);
        if(strList.size() != 676) {
            throw new Exception("size error");
        }

        List<String> list =strList.stream().map(source -> source.substring(0, source.length() - 1)).filter(s -> NumberUtil.isDigits(s))
                .collect(Collectors.toList());

        if(list.size() != 676) {
            throw new Exception("size2 error");
        }
        List<String> act_80620 = new ArrayList<>(), act_80223 = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++) {
            if(i > 266) {
                act_80223.add(list.get(i));
            } else {
                act_80620.add(list.get(i));
            }
        }

        StringBuilder sb_277 = new StringBuilder("insert into behaviour (user_id, activity_id, type) values ");
        act_80620.stream().forEach(s -> {
            sb_277.append("(").append(s);
            sb_277.append(",80620,'other'),");
        });
        sb_277.append(";");

        StringBuilder sb_400 = new StringBuilder("insert into behaviour (user_id, activity_id, type) values ");
        act_80223.stream().forEach(s -> {
            sb_400.append("(").append(s);
            sb_400.append(",80223,'other'),");
        });
        sb_400.append(";");

        System.out.println("277: " + act_80620.size() + ", 400 " + act_80223.size());

        System.out.println(sb_277.toString());
        System.out.println(sb_400.toString());
    }
}
