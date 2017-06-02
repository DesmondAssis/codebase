package com.desmond.codebase.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/8/8.
 */
public class ProcessSystemRecord {
    String sql = "select  distinct visit_page from data_visit_year where visit_page in(@rp) and platform = ?";

    String[] visitPages = {
            "order.next.clicked","order.alltickets.clicked","order.alldates.clicked","order.allproducts.clicked",
            "orderconfirm.confirm.clicked","orderconfirm.back.clicked","orderconfirm.showall.clicked",
            "order.phone.confim.clicked","order.phone.request.clicked","order.phone.close.clicked",
            "pay.pay.clicked","pay.order.cancle.clicked","pay.servicer.clicked",
            "pay.order.detail.clicked","mine.order.unreview.clicked","mine.review.click",
            "myreview.unreview.clicked","orderlist.review.clicked","orderlist.review.append.clicked",
            "orderdetail.review.clicked","orderdetail.review.append.clicked","orderlist.unreview.review.clicked",
            "myreview.review.append.clicked"
    };

    ConnectionDB connectionDB = new ConnectionDB();

    public static void main(String[] args) {
        ProcessSystemRecord record = new ProcessSystemRecord();

        record.process("iphone");
//        record.process("android");
    }

    private void process(String plaform) {

        StringBuilder sb = new StringBuilder();
        List<String> parms = new ArrayList<>();
        for (int i = 0; i < visitPages.length; i++) {
            parms.add(visitPages[i]);
            sb.append("?");
            if(i < visitPages.length - 1) {
                sb.append(",");
            }
        }

        parms.add(plaform);

        sql = sql.replace("@rp", sb.toString());


        List<Map<String, Object>> objectList = connectionDB.excuteQuery(DataBaseEnum.WANZHOUMO, sql, parms.toArray());

        List<String> strList = new ArrayList<>();
        objectList.forEach(data -> strList.add(String.valueOf(data.get("visit_page"))));

        if(strList.size() != visitPages.length) {
            System.out.println(plaform + ": size not equal~, origin:" + visitPages.length + ",actual:" + strList.size());
        }

        int diffSize = 0, d = 0;
        for(String str : visitPages) {
            if(!strList.contains(str)) {
                diffSize ++;
                System.out.println(str);
            }
        }

        System.out.println("diff size:" + diffSize);
        System.out.println("\n\n\n\n");

        Map<String, Integer> map = new HashMap<>();
        for (String s : visitPages) {
            if(!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }


        }
    }
}
