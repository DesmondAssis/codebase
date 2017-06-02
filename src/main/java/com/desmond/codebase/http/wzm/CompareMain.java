package com.desmond.codebase.http.wzm;

import com.desmond.codebase.number.NumberUtil;

import java.util.*;

import static com.desmond.codebase.print.Print.print;
import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/11/25.
 */
public class CompareMain {
    static String[][] urlArr = {
//            {
//                    "new-fixtag-本周末",
//                    "http://test5.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&lat=31.224868&lon=121.448383&offset=0&pagesize=100&start_time=2015-11-28&timestamp=1448344719&v=3.0&method=activity.homelist",
//                    "http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&lat=31.224868&lon=121.448383&offset=0&pagesize=100&start_time=2015-11-28&timestamp=1448344719&v=3.0"
//            },
//            {
//                    "old-fixtag-热门",
//                    "http://test5.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&lat=31.224868&lon=121.448383&offset=0&pagesize=100&sort=dayfollownum&timestamp=1448344796&v=3.0&method=activity.homelist",
//                    "http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&lat=31.224868&lon=121.448383&offset=0&pagesize=100&sort=dayfollownum&timestamp=1448344796&v=3.0"
//            },
//            {
//                    "old-fixtag-附近",
//                    "http://test5.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&lat=31.224868&lon=121.448383&offset=0&pagesize=100&sort=distance&timestamp=1448344757&v=3.0&method=activity.homelist",
//                    "http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&lat=31.224868&lon=121.448383&offset=0&pagesize=100&sort=distance&timestamp=1448344757&v=3.0"
//            }
//            ,
//            {
//                    "old-分类标签",
//                    "http://test5.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&genre_id=71&lat=31.224868&lon=121.448383&offset=0&pagesize=100&timestamp=1448344921&v=3.0&method=activity.homelist",
//                    "http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&genre_id=71&lat=31.224868&lon=121.448383&offset=0&pagesize=100&timestamp=1448344921&v=3.0"
//            }
//            ,
//            {
//                    "old-热门标签",
//                    "http://test5.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&free=0&is_near=0&lat=31.224868&lon=121.448383&offset=0&pagesize=100&same_city=0&sort=id&tags=87,&timestamp=1448345023&v=3.0&method=activity.homelist",
//                    "http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&city_id=10896&fields_version=3.3&free=0&is_near=0&lat=31.224868&lon=121.448383&offset=0&pagesize=100&same_city=0&sort=id&tags=87,&timestamp=1448345023&v=3.0"
//            }
//            ,
//            {
//                    "old-keywords-东海",
//                    "http://test5.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&fields_version=3.3&keyword=东海&lat=31.224868&lon=121.448383&offset=0&pagesize=100&timestamp=1448345077&v=3.0&method=activity.homelist",
//                    "http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&fields_version=3.3&keyword=东海&lat=31.224868&lon=121.448383&offset=0&pagesize=100&timestamp=1448345077&v=3.0"
//            },
            {
                    "old-首页",
                    "https://api-test110.wanzhoumo.com/wanzhoumo/activity/HomeList?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&fields_version=3.3&free=0&genre_id=0&lat=31.224868&limited_discount=0&lon=121.448383&offset=0&pagesize=100&timestamp=1448344571&v=3.0&method=activity.homelist"

                    ,"https://api-test110.wanzhoumo.com/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&fields_version=3.3&free=0&genre_id=0&lat=31.224868&limited_discount=0&lon=121.448383&offset=0&pagesize=100&timestamp=1448344571&v=3.0"
            }
    };
    public static void main(String[] args) {




        fixIsFreeOne();
    }

    private static void fixIsFreeOne() {
        compareIsFreeOne();

    }

    private static void compareIsFreeOne() {
        List<String> urlArr = new ArrayList<>();

        StringBuilder sqlSb = new StringBuilder();
        String sql = "select * from (select send_content sc, status s, from_unixtime(create_time, '%y%-%m-%d %h:%i') ut," +
                " create_time ct from data_center.log_asy_task where (@sql@) order by ct desc) t group by sc order by ct desc;";
        String subSql = " send_content = '{\"type\":\"activityInfoUpdate\",\"id\":\"@id@\"}' ";
        String url = "https://openapi.wanzhoumo.com/activity/@id@?app_key=momo&timestamp=1453263087&sign=8d7a6ca086acc9069e21cb659b11b0b1";
        int [] idas = {79336 ,78416, 78157, 77934,78575 ,75235, 76484 ,77565 ,70097 ,77292, 76484, 78341, 77934, 75859, 75870, 77276};

        Set<Integer> ids = new HashSet<>();
        for(int id : idas) {
            ids.add(id);
        }

        int k = 0;
        for(int i : ids) {
            if(k != 0) {
                sqlSb.append(" or ");
            }
            sqlSb.append(subSql.replaceAll("@id@", i + ""));

            String u = url.replaceAll("@id@", i + "");
            urlArr.add(u);

            k++;
        }

        List<Integer> list = new ArrayList<>();
        for(String str : urlArr) {
            filterDetailFreeOne(str, list);
        }

        System.out.println("\n\n\n\n\n sql: " + sql.replaceAll("@sql@", sqlSb.toString()));
        System.out.println("\n\n corrent size: " + list.size() + ",total size: " + ids.size() + "==" + (list.size() == ids.size()));
    }


    private static void filterDetailFreeOne(String url, List<Integer> list) {
        HttpClientApi httpClientApi = new HttpClientApi();
        httpClientApi.setMethod("GET");
        ResponseApi responseApi = httpClientApi.call(url);

        if(!"success".equals(responseApi.getStatus())) {
            System.out.println("result error!" + url);
            return;
        }

        Object result = responseApi.getResult();
        Map map = (Map) result;

        if(!String.valueOf(map.get("title")).contains("全民赏花季")) {
            System.out.println("content error!" + " : title: " + map.get("title")
                    + url);

            return;
        }

        list.add(1);
    }


    private static void compareHomeList() {
        for(String[] url : urlArr) {
            RequestApi requestApi = new RequestApi();
            compare(url[0], requestApi.get(url[1]), requestApi.get(url[2]));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void compare(String name,  ResponseApi oldOne, ResponseApi newOne) {
        printnb("***beginbeginbeginbeginbegin*******『" + name + "』***************************");
        boolean isSuccess = true;
        if(!oldOne.getStatus().equals(newOne.getStatus())) {
            throw new RuntimeException("error:status");
        }

        Map<String, Object> oldMap = (Map)oldOne.getResult();
        Map<String, Object> newMap = (Map)newOne.getResult();

        if(getCount(oldMap) != getCount(newMap)) {
            isSuccess = false;
            printnb("count not equal!");
        }
        List<Map<String, Object>> newList = (List<Map<String, Object>>) newMap.get("list");
        List<Map<String, Object>> oldList = (List<Map<String, Object>>) oldMap.get("list");
        if(newList.size() != oldList.size()) {
            isSuccess = false;
            printnb("list's size not equal!");
        }

        List<Integer> oldIdList = getActList(oldList);
        List<Integer> newIdList = getActList(newList);
        if(oldIdList.size() != newIdList.size()) {
            printnb("import:list's size not equal!");
        }

        printnb("old=new");
        int count = 20;boolean isEqual = true;
        StringBuilder sb = new StringBuilder("("), sb2 = new StringBuilder();
        for(int i = 0; i < oldIdList.size(); i++) {
            if(count <= 0) break;
            sb.append(oldIdList.get(i) + ",");
            sb2.append(newIdList.get(i)+":" + oldIdList.get(i)+"\n");
            if(!oldIdList.get(i).equals(newIdList.get(i))) {
                isSuccess = false;
                count --;

                print(i + ":" + oldIdList.get(i) + "=" + newIdList.get(i));
            }
            if(!newIdList.contains(oldIdList.get(i))) {
                isEqual =false;
            }
        }
        printnb(sb);

        printnb("\n\n\nreuslt:" + isSuccess + "~~" + isEqual +"*******************************endendendend『" + name + "***************************");
        printnb(sb2);
    }

    private static List<Integer> getActList(List<Map<String, Object>> list) {
        List<Integer> actIdList = new ArrayList<>(list.size());
        for(Map<String, Object> map : list) {
            String type = (String) map.get("item_type");
            if("activity".equals(type)) {
                Object idObj = map.get("id");
                actIdList.add(idObj instanceof String ? NumberUtil.toInt((String) idObj) : (int) idObj);
            }
        }

        return actIdList;
    }

    private static int getCount(Map<String, Object> resultMap) {
        Object countObj = resultMap.get("count");
        if(countObj instanceof String) {
            return NumberUtil.toInt((String) countObj);
        } else if(countObj instanceof Integer) {
            return (int) countObj;
        }

        return -1;
    }

}
