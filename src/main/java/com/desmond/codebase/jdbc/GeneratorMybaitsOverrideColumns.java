package com.desmond.codebase.jdbc;

import com.desmond.codebase.date.DateTimeUtils;
import com.desmond.codebase.url.UrlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/11/17.
 */
public class GeneratorMybaitsOverrideColumns {
    public static void main(String[] args) {
//        syncTable();
//        processColumns();
//        compareData();

        tmp();
    }

    private static void compareData() {
        ConnectionDB dbc = new ConnectionDB();

        Map<String, String> stdMap = new HashMap<>(),
                toCheckMap = new HashMap<>();

        List<Map<String, Object>> jobs = dbc.excuteQuery(DataBaseEnum.DATA_CENER, "select name,runtime from hp_spark_job where job_type=2", null);
        jobs.stream()
                .forEach(map -> {
                    String appName = String.valueOf(map.get("name"));
                    String timStr = String.valueOf(map.get("runtime"));

                    timStr = timStr.substring(11, timStr.length() - 2);

                    stdMap.put(appName, timStr);
                });

        jobs = dbc.excuteQuery(DataBaseEnum.DATA_CENER, "select name,runtime from hp_spark_job_copy where job_type=2", null);
        jobs.stream()
                .forEach(map -> {
                    String appName = String.valueOf(map.get("name"));
                    String timStr = String.valueOf(map.get("runtime"));

                    timStr = timStr.substring(11, timStr.length() - 2);

                    toCheckMap.put(appName, timStr);
                });

        stdMap.forEach((k,v) -> {
            String tv = toCheckMap.get(k);
            if(!v.equals(tv)) {
                System.out.println(k + "=" + v + ">" + tv);
            }
        });
    }

    private static void syncTable() {
        ConnectionDB dbc = new ConnectionDB();

        List<String> orderIdNameList = new ArrayList<>();
        List<String> orderExtIdNameList = new ArrayList<>();
        List<String> businessOrderIdNameList = new ArrayList<>();

        List<Map<String, Object>> orderList = dbc.excuteQuery(DataBaseEnum.WANZHOUMO, "desc `business_order_settlement`", null);
        orderList.forEach(map -> {
            orderIdNameList.add(String.valueOf(map.get("Field")));
        });

        List<Map<String, Object>> orderExtraIdList = dbc.excuteQuery(DataBaseEnum.WANZHOUMO, "desc `order_extra`", null);
        orderExtraIdList.forEach(map -> {
            orderExtIdNameList.add(String.valueOf(map.get("Field")));
        });

        List<Map<String, Object>> businessOrderIdList = dbc.excuteQuery(DataBaseEnum.DATA_CENER, "desc `business_order`", null);
        businessOrderIdList.forEach(map -> {
            businessOrderIdNameList.add(String.valueOf(map.get("Field")));
        });


        List<String> toOrderList = new ArrayList<>(), toOrderExtraList = new ArrayList<>();

        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for(String f : businessOrderIdNameList) {
            if(orderIdNameList.contains(f)) {
                sb1.append("o.").append(f).append(",");
            } else if(orderExtIdNameList.contains(f)) {
                sb2.append("oe.").append(f).append(",");
            } else {
                throw new RuntimeException("error!!!!!");
            }
        }

        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
    }

    private static void processColumns() {
        ConnectionDB dbc = new ConnectionDB();

        List<Map<String, Object>> list = dbc.excuteQuery(DataBaseEnum.DATA_CENER, "desc business_order_settlement", null);

        list.forEach(map -> {
            String type = String.valueOf(map.get("Type"));
            if(type != null && (  type.startsWith("tinyint") || type.startsWith("smallint") )) {
                String fd = String.valueOf(map.get("Field"));
                System.out.println("<columnOverride column=\"" + fd + "\" javaType=\"java.lang.Integer\"/>");
            }
        });
    }

    private static void tmp() {
        ConnectionDB dbc = new ConnectionDB();

        List<Map<String, Object>> list = dbc.excuteQuery(DataBaseEnum.DATA_CENER, "desc key_funnel_checker_log", null);

        list.forEach(map -> {
            String type = String.valueOf(map.get("Type"));
            if(type != null && (  type.startsWith("int") || type.startsWith("tinyint") )) {
                String fd = String.valueOf(map.get("Field"));
                System.out.println("fields.add(DataTypes.createStructField(\"" + fd + "\", DataTypes.IntegerType, false));");
            } else {
                String fd = String.valueOf(map.get("Field"));
                System.out.println("fields.add(DataTypes.createStructField(\"" + fd + "\", DataTypes.StringType, false));");
            }
        });
    }
}
