package com.desmond.codebase.string;


import com.desmond.codebase.number.NumberUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.desmond.codebase.print.Print.print;
import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/10/15.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
    private static Map<String, String> map = new HashMap<>();
    static {
        map.put("intro_show", "StringUtil.nl2br(activity.getIntro())");
        map.put("tips_show", "StringUtil.isNotBlank(activity.getTips()) ? activity.getTips() + \"\n\" : \"\"");
        map.put("order_url_show", "getOrderUrlShow(activity)");
        map.put("partake_explain_show", "this.getPartakeExplainShow(activity, extra)");
        map.put("isfree", "trade != null && trade.getLowestPrice() == 0 ? \"1\" : \"0\"");
        map.put("discount_status", "0");
        map.put("discount_show", "discountShow()");
        map.put("discount_status_show", "\"\"");
        map.put("order_num_available", "0");
        map.put("pic_details_show", "this.getActivityImageList(activity.getPic())");
        map.put("poi_name", "poi != null ? poi.getTitle() : \"\"");
        map.put("price_show", "priceShow(activity, trade, extra)");

    }

    public static String listToString(List<Integer> objectList) {
        StringBuilder listStrSb = new StringBuilder("[");
        if(CollectionUtils.isNotEmpty(objectList)) {
            for(int i = 0; i < objectList.size(); i++) {
                listStrSb.append(objectList.get(i));
                if(i < objectList.size() - 1) {
                    listStrSb.append(",");
                }
            }
        }

        listStrSb.append("]");

        return listStrSb.toString();
    }

    public static String toUpperFirstChar(String str) {
        if(isBlank(str)) {
            return str;
        }

        String tmp = str.substring(0, 1).toUpperCase();
        return tmp + str.substring(1, str.length());
    }

    public static void main(String[] args) {
        String[] s = {",0,",",10898,",",10896,0,",",10898,10896,10894,0,"};
        for(String t : s) {
            String[] arr = t.split(",");
            if(arr != null && arr.length > 0) {
                for(String a : arr) {
                    int i = NumberUtil.toInt(a, -1);
                    if(i > -1) {
                        System.out.print(i + " ");
                    }
                }
            }

            System.out.println();
        }
    }

    public static void generateMethod1() {
        String s = "addRecommendNum,address,adminId,bestSeason,cityPoiId,cost,createTime,desType,genreId,hasChild,icon,id,isEdit,isvip,keywords,lastAdminId,lat,lon,offlineReason,openTime,orderUrl,parentId,pic,pinyin,recommendNum,sort,status,suggestPlayTime,tags,tel,title,updateTime,weatherCode";
        String[] strArr = s.split(",");

        for(String str : strArr) {
            StringBuilder sb = new StringBuilder();
            String tmpStr = StringUtils.capitalize(str);
            sb.append("\r").append("jPoiAll.set" +  tmpStr + "(convertObj(poi.get" + tmpStr + "()));");

            sb.append("");
            printnb(sb);
        }
    }

    public static void generateMethod() {
        String s = "pic_show,pic_details_show,pic_list_show,order_url_show,parent.title,statis.comment_num,statis.follow_num,citypoi.title,pic_list_thumb,icon_show,mapurl,citypoi.pinyin,ended_activity_count";
        String[] strArr = s.split(",");

        for(String str : strArr) {
            String[] subArr = str.split("_");
            StringBuilder sb = new StringBuilder();

            sb.append("\r@JsonProperty(\"" + str + "\")\n");
            sb.append("\rprivate String ");
            for(int i = 0; i < subArr.length; i++) {
                if(i != 0) {
                    sb.append(StringUtils.capitalize(subArr[i]));
                } else {
                    sb.append(subArr[i]);
                }
            }
            sb.append(";\n");
            printnb(sb);
        }
    }

    public static void regx() {
        String reg = "<h3.*?>([^<]+)<\\/h3>";
        String tr = "testt<h3>行程安排</h3><image src/><h3>第 1 天。</h3><h3>第 2 天：早收费）。</h3>tt";
        String str = "a<h3d>test1</h3>b<h3>test2</h3>";
        printnb(tr.replaceAll(reg, "@@@@@$1@@@@@"));
        String[] ss = str.split(reg);
        for(String s : ss) {
//            printnb(s);
        }

        Pattern p = Pattern.compile("<h3.*?>([^<]+)<\\/h3>");
        Matcher m = p.matcher(tr);
        List<String> matches = new ArrayList<String>();
        String result = "";
        int start = 0;
        while(m.find()) {
            matches.add(tr.substring(start, m.start()));
            matches.add(m.group(1));
            start = m.end();
        }
        matches.add(tr.substring(start));
        for(String s : matches) {
            printnb(s);
        }
//        printnb(result);
    }
    public static void str2Asci(String str) {
        String code = Integer.toHexString(str.charAt(0));
        int decimal = Integer.parseInt(code, 16);
        System.out.println(decimal);
    }
    public static int ord(String str) {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            str = str.substring(0, 1);
            byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
            if(bytes != null && bytes.length > 0) {
                return Integer.parseInt(String.format("%02X",bytes[0]), 16);
            }
        }

        return 0;
    }

    public static void strToArr() {
        String s1 = ",63,41,";
        String s2 = ",41,".trim();
        String[] as1 = s1.split(",");
        String[] as2 = s2.split(",");

        for(String s : as1) {
            System.out.print(s);
        }

        System.out.println();
        for(String s : as2) {
            System.out.print(s);
        }
    }

    public static String nl2br(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            str = str.replaceAll("(\r\n|\n)", "<br />$0");
        }

        return str;
    }


    /**
     * view ::: 字段
     * <p>
     * shop=>id : shop: {id} :::: JShop(shop)
     * extra-admin_offline: admin_offline ::: admin_offline(jsonproperty)
     * shop.title: shop.title ::: shopTitile(jsonproperty)
     * pic_details_show: pic_details_show ::: picDetailsShow(jsonproperty)
     */
    private static void generateFields() {
        StringBuilder sb = new StringBuilder();
        for (String str : verArr[2]) {
            if (str.indexOf("=>") > 0) {
                String[] spArr = str.split("=>");
                if(map.get(spArr[0]) != null) {
                    sb.append("\t" + "resultMap.put(\"" + spArr[0] + "\", " + map.get(spArr[0])  + ");" + "\n");
                } else {
                    sb.append("\t" + "resultMap.put(\"" + spArr[0] + "\", " + str  + ");" + "\n");
                }
            } else if (str.indexOf("-") > 0) {
                String[] spArr = str.split("-");
                String method = spArr[1];
                String[] fields = method.split("_");
                String f = ".get";
                for(int i = 0; i < fields.length; i++) {
                    f += org.apache.commons.lang3.StringUtils.capitalize(fields[i]);
                }
                f = spArr[0] + f + "()";

                if(map.get(spArr[1]) != null) {
                    sb.append("\t" + "resultMap.put(\"" + spArr[1] + "\", " + map.get(spArr[1])  + ");" + "\n");
                } else {
                    sb.append("\t" + "resultMap.put(\"" + spArr[1] + "\", " + f  + ");" + "\n");
                }
            } else if (str.indexOf(".") > 0) {
                String[] spArr = str.split("\\.");
                String method = spArr[1];
                String[] fields = method.split("_");
                String f = ".get";
                for(int i = 0; i < fields.length; i++) {
                    f += org.apache.commons.lang3.StringUtils.capitalize(fields[i]);
                }
                f = spArr[0] + f + "()";

                if(map.get(str) != null) {
                    sb.append("\t" + "resultMap.put(\"" + str + "\", " + map.get(str)  + ");" + "\n");
                } else {
                    sb.append("\t" + "resultMap.put(\"" + str + "\", " + f  + ");" + "\n");
                }
            } else {
                String method = str;
                String[] fields = method.split("_");
                String f = ".get";
                for(int i = 0; i < fields.length; i++) {
                    f += org.apache.commons.lang3.StringUtils.capitalize(fields[i]);
                }
                f = "activity" + f + "()";

                if(map.get(str) != null) {
                    sb.append("\t" + "resultMap.put(\"" + str + "\", " + map.get(str)  + ");" + "\n");
                } else {
                    sb.append("\t" + "resultMap.put(\"" + str + "\", " + f  + ");" + "\n");
                }
            }

        }

        System.out.print(sb);
    }

    private static void filter() {
        Map<String, Integer> uniqueMap = new HashMap<>();
        List<String> baseList = new ArrayList<>(),
                ver32List = new ArrayList<>(),
                ver33List = new ArrayList<>(),
                defaultList = new ArrayList<>();
        for (String[] strArr : verArr) {
            for (String str : strArr) {
                if (uniqueMap.containsKey(str)) {
                    int count = uniqueMap.get(str);
                    uniqueMap.put(str, ++count);
                } else {
                    uniqueMap.put(str, 1);
                }
            }
        }

        System.out.println(":::::::defaut::::" + verArr[2].length);
        StringBuilder dsb = new StringBuilder();
        for (String str : verArr[2]) {
            dsb.append("\"").append(str).append("\",");
        }
        System.out.println(dsb);

        for (Map.Entry<String, Integer> entry : uniqueMap.entrySet()) {
            if (entry.getValue() > 1) {
                baseList.add(entry.getKey());
            }
        }

        System.out.println("base list: size===" + baseList.size());
        int i = 0;
        for (String[] strArr : verArr) {
            for (String str : strArr) {
                if (i == 0) {
                    if (!baseList.contains(str)) {
                        ver32List.add(str);
                    }
                } else if (i == 1) {
                    if (!baseList.contains(str)) {
                        ver33List.add(str);
                    }
                } else if (i == 2) {
                    if (!baseList.contains(str)) {
                        defaultList.add(str);
                    }
                }
            }
            i++;
        }


        System.out.println("base list: size===" + baseList.size() + "\n3.2:" + (ver32List.size())
                + "\n3.3: " + (ver33List.size())
                + "\ndefault: " + (defaultList.size()));

        StringBuilder sb = new StringBuilder();

        // base


        sb.append("\tprivate String s;\n").append("\tprivate String s2;\n");
        System.out.println(sb);
    }

    public static String utfSubstr(String name, int length) {
        String value = name;
        int len = 0; //定义返回的字符串长度
        int j = 0;
        int idx = 0; // 当前index 的位置.
        //按照指定编码得到byte[]
        byte[] b_name = new byte[0];
        try {
            b_name = name.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (b_name.length > 0) {
            while (true) {
                short tmpst = (short) (b_name[j] & 0xF0);
                if (tmpst >= 0xB0) {
                    if (tmpst < 0xC0) {
                        j += 2;
                        len += 2;
                    } else if ((tmpst == 0xC0) || (tmpst == 0xD0)) {
                        j += 2;
                        len += 2;
                    } else if (tmpst == 0xE0) {
                        j += 3;
                        len += 2;
                    } else if (tmpst == 0xF0) {
                        short tmpst0 = (short) (((short) b_name[j]) & 0x0F);
                        if (tmpst0 == 0) {
                            j += 4;
                            len += 2;
                        } else if ((tmpst0 > 0) && (tmpst0 < 12)) {
                            j += 5;
                            len += 2;
                        } else if (tmpst0 > 11) {
                            j += 6;
                            len += 2;
                        }
                    }
                } else {
                    j += 1;
                    len += 1;
                }
                idx++;
                if (j > b_name.length - 1) {
                    break;
                } else if (len >= length) {
                    value = value.substring(0, idx) + "...";
                    break;
                }
            }
        }

        return value;
    }

    private static StringBuilder getFields(String comment, List<String> fieldList) {
        System.out.println();
        for (String str : fieldList) {

        }

        return null;
    }

    // // 3.2-43, 3.3-54, default-78

    private final static String[] FIELDS_VERSION32 = {
            "id", "title", "title_vice", "time_txt", "intro_show", "tips_show", "tel", "order_url", "order_url_show", "genre_main_show", "partake_explain_show", "is_follow", "lat", "lon", "trade-buy_type", "trade-isfree", "trade-discount_status", "trade-discount_show", "trade-discount_status_show", "trade-sold_out", "trade-book_endtime", "book_status", "statis-order_num", "statis-order_num_available", "statis-follow_num", "order_start_time", "order_end_time", "isfree", "unsubscribe", "pic_details_show", "price_show", "consult", "input_type", "input_type_id", "shop.title", "poi=>id", "poi=>title", "poi=>address", "poi=>lon", "poi=>lat", "address", "poi_name", "trade-is_not_buy"
    };

    private final static String[] FIELDS_VERSION33 = {
            "id", "input_activity_id", "title", "title_vice", "time_txt", "end_time", "intro_show", "tips_show", "offline_reason", "status", "tel", "order_url", "order_url_show", "genre_main_show", "partake_explain_show", "is_follow", "lat", "lon", "recommendation", "mapurl", "requirments", "trade-buy_type", "trade-isfree", "trade-discount_status", "trade-discount_show", "trade-discount_status_show", "trade-sold_out", "trade-book_endtime", "book_status", "statis-order_num", "statis-order_num_available", "statis-follow_num", "order_start_time", "order_end_time", "isfree", "unsubscribe", "pic_details_show", "price_show", "consult", "input_type", "input_type_id", "poi=>id", "poi=>title", "poi=>address", "poi=>lon", "poi=>lat", "address", "poi_name", "trade-is_not_buy", "shop=>id", "shop=>title", "shop=>icon", "shop=>summary", "extra-admin_offline"
    };

    private final static String[] FIELDS_DEFAULT = {
            "pic_show","pic_list_show","pic_details_show","poi_all","mapurl","poi_name_app","poi_id_show","intro_show","order_url_show","friend_follow","is_valid","tips_show","price_show","partake_explain_show","genre_main_show","genre_main_pic_show","genre_main_pic_android_show","price_explain_str","extra.partake_explain","data_tag","citypoi.title","citypoi.pinyin","shop.title","shop.show_title","statis.comment_num","statis.follow_num","wu_explain_show","trade.buy_type","trade.is_not_buy","shop.pic_show","shop.id","recommendation","shop.avatar_show","statis.order_num_available","pic_h5list","id","title","title_vice","pic","poi_id","city_poi_id","genre_id","genre_main","lat","lon","geohash","start_time","end_time","tags","keywords","order_url","time_txt","cost","intro","tips","tel","address","weather_code","recommend_num","add_recommend_num","status","create_time","update_time","offline_reason","admin_id","last_admin_id","isshow_close","isshow_far","is_sortcity","input_type","input_type_id","input_activity_id","isshow_week","show_endtime","is_lucky","shop_auth","anytime","time_detail"
    };

    private final static String verArr[][] = new String[][] {FIELDS_VERSION32, FIELDS_VERSION33, FIELDS_DEFAULT};
}
