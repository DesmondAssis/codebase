package com.desmond.codebase.jdbc;

import com.desmond.codebase.date.DateTimeUtils;
import com.desmond.codebase.jdbc.vo.ExpressCompanyItem;
import com.desmond.codebase.jdbc.vo.ExpressCompanyVo;
import com.desmond.codebase.json.JsonConverterUtil;
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

//        tmp();
        saveExpressCompany();
    }


    private static void saveExpressCompany() {
        ConnectionDB connectionDB = new ConnectionDB();
        String result = "{\"status\":\"0\",\"msg\":\"ok\",\"result\":[{\"name\":\"Aramex\",\"type\":\"ARAMEX\",\"letter\":\"A\",\"tel\":\"400-631-8388\",\"number\":\"30288063886\"},{\"name\":\"安信达\",\"type\":\"ANXINDA\",\"letter\":\"A\",\"tel\":\"021-54224681\",\"number\":\"\"},{\"name\":\"安捷\",\"type\":\"ANJELEX\",\"letter\":\"A\",\"tel\":\"400-619-7776\",\"number\":\"AJ80862351\"},{\"name\":\"AAE\",\"type\":\"AAEWEB\",\"letter\":\"A\",\"tel\":\"400-6100-400 021-51008888\",\"number\":\"131685763\"},{\"name\":\"安能快递\",\"type\":\"ANEEX\",\"letter\":\"A\",\"tel\":\"\",\"number\":\"30000014025846\"},{\"name\":\"安能\",\"type\":\"ANE\",\"letter\":\"A\",\"tel\":\"40010-40088\",\"number\":\"220014594841\"},{\"name\":\"百世快运\",\"type\":\"BSKY\",\"letter\":\"B\",\"tel\":\"\",\"number\":\"10288328982\"},{\"name\":\"黑狗\",\"type\":\"BLACKDOG\",\"letter\":\"B\",\"tel\":\"400-106-1234\",\"number\":\"1002868661\"},{\"name\":\"百福东方\",\"type\":\"EES\",\"letter\":\"B\",\"tel\":\"400-706-0609\",\"number\":\"\"},{\"name\":\"蓝天\",\"type\":\"BLUESKY\",\"letter\":\"B\",\"tel\":\"0061-3-94950283\",\"number\":\"BSD211640\"},{\"name\":\"程光\",\"type\":\"FLYWAYEX\",\"letter\":\"C\",\"tel\":\"0064-09-2759536\",\"number\":\"100001362043\"},{\"name\":\"德邦\",\"type\":\"DEPPON\",\"letter\":\"D\",\"tel\":\"95353\",\"number\":\"5180435727\"},{\"name\":\"DHL\",\"type\":\"DHL\",\"letter\":\"D\",\"tel\":\"800-810-8000 400-810-8000\",\"number\":\"5846399171\"},{\"name\":\"DPEX\",\"type\":\"DPEX\",\"letter\":\"D\",\"tel\":\"0755-88297707\",\"number\":\"\"},{\"name\":\"D速\",\"type\":\"DEXP\",\"letter\":\"D\",\"tel\":\"0531-88636363\",\"number\":\"616906328376\"},{\"name\":\"大田\",\"type\":\"DTW\",\"letter\":\"D\",\"tel\":\"400-626-1166\",\"number\":\"\"},{\"name\":\"EMS\",\"type\":\"EMS\",\"letter\":\"E\",\"tel\":\"40080-11183\",\"number\":\"9572253781500\"},{\"name\":\"平安快递\",\"type\":\"EFSPOST\",\"letter\":\"E\",\"tel\":\"0773-2315320\",\"number\":\"EF990157553NZ\"},{\"name\":\"EWE\",\"type\":\"EWE\",\"letter\":\"E\",\"tel\":\"1300-09-6655\",\"number\":\"mhu00061\"},{\"name\":\"FedEx国际\",\"type\":\"FEDEXIN\",\"letter\":\"F\",\"tel\":\"800-988-1888 400-886-1888\",\"number\":\"808446554948\"},{\"name\":\"富腾达\",\"type\":\"FTD\",\"letter\":\"F\",\"tel\":\"0064-09-4432342\",\"number\":\"NZ1200523\"},{\"name\":\"凤凰\",\"type\":\"PHOENIXEXP\",\"letter\":\"F\",\"tel\":\"010-85826200\",\"number\":\"\"},{\"name\":\"FedEx\",\"type\":\"FEDEX\",\"letter\":\"F\",\"tel\":\"800-988-1888 400-886-1888\",\"number\":\"120949498648\"},{\"name\":\"飞洋\",\"type\":\"GCE\",\"letter\":\"G\",\"tel\":\"010-87785733\",\"number\":\"GC501115760US\"},{\"name\":\"国通\",\"type\":\"GTO\",\"letter\":\"G\",\"tel\":\"4001-111-123\",\"number\":\"2840737926\"},{\"name\":\"能达\",\"type\":\"ND56\",\"letter\":\"G\",\"tel\":\"400-6886-765\",\"number\":\"\"},{\"name\":\"共速达\",\"type\":\"GSD\",\"letter\":\"G\",\"tel\":\"400-111-0005\",\"number\":\"\"},{\"name\":\"百世快递\",\"type\":\"HTKY\",\"letter\":\"H\",\"tel\":\"4009565656\",\"number\":\"350630538314\"},{\"name\":\"华企\",\"type\":\"HQKY\",\"letter\":\"H\",\"tel\":\"400-626-2356\",\"number\":\"\"},{\"name\":\"恒路\",\"type\":\"HENGLU\",\"letter\":\"H\",\"tel\":\"400-182-6666\",\"number\":\"03122576\"},{\"name\":\"锦程快递\",\"type\":\"HREX\",\"letter\":\"H\",\"tel\":\"\",\"number\":\"63760347\"},{\"name\":\"鸿远\",\"type\":\"HYE\",\"letter\":\"H\",\"tel\":\"\",\"number\":\"LB603036351US\"},{\"name\":\"嘉里物流\",\"type\":\"KERRY\",\"letter\":\"J\",\"tel\":\"852-2410-3600\",\"number\":\"316B455817673\"},{\"name\":\"佳吉\",\"type\":\"JIAJI\",\"letter\":\"J\",\"tel\":\"400-820-5566\",\"number\":\"729976312\"},{\"name\":\"京东\",\"type\":\"JD\",\"letter\":\"J\",\"tel\":\"\",\"number\":\"12290972964\"},{\"name\":\"九曳\",\"type\":\"JIUYESCM\",\"letter\":\"J\",\"tel\":\"400-6199-939\",\"number\":\"JY0001964522\"},{\"name\":\"晋越\",\"type\":\"PEWKEE\",\"letter\":\"J\",\"tel\":\"台北：+886-2-25016988澳门：00853-28520722福建：0592-5569715广东：0769-88763939\",\"number\":\"\"},{\"name\":\"急先达\",\"type\":\"JOUST\",\"letter\":\"J\",\"tel\":\"400-694-1256\",\"number\":\"\"},{\"name\":\"加运美\",\"type\":\"TMS\",\"letter\":\"J\",\"tel\":\"0769-85515555 \",\"number\":\"2197050107\"},{\"name\":\"佳怡\",\"type\":\"JIAYI\",\"letter\":\"J\",\"tel\":\"400-631-9999\",\"number\":\"\"},{\"name\":\"京广\",\"type\":\"KKE\",\"letter\":\"J\",\"tel\":\"852-23329918\",\"number\":\"7102293245\"},{\"name\":\"跨越\",\"type\":\"KYEXPRESS\",\"letter\":\"K\",\"tel\":\"4008-098-098\",\"number\":\"2628904\"},{\"name\":\"快捷\",\"type\":\"FASTEXPRESS\",\"letter\":\"K\",\"tel\":\"4008-333-666\",\"number\":\"536135784093\"},{\"name\":\"龙邦\",\"type\":\"LBEX\",\"letter\":\"L\",\"tel\":\"021-59218889\",\"number\":\"686013186447\"},{\"name\":\"联昊通\",\"type\":\"LTS\",\"letter\":\"L\",\"tel\":\"400-888-8887\",\"number\":\"236012014633\"},{\"name\":\"民航\",\"type\":\"CAE\",\"letter\":\"M\",\"tel\":\"4008-17-4008\",\"number\":\"CAE602232295\"},{\"name\":\"配思航宇\",\"type\":\"PEISI\",\"letter\":\"P\",\"tel\":\"010-65489928\",\"number\":\"\"},{\"name\":\"PCA\",\"type\":\"PCA\",\"letter\":\"P\",\"tel\":\"1800518000\",\"number\":\"EAU839001631\"},{\"name\":\"全峰\",\"type\":\"QFKD\",\"letter\":\"Q\",\"tel\":\"4001-000-001\",\"number\":\"720166045326\"},{\"name\":\"全一\",\"type\":\"APEX\",\"letter\":\"Q\",\"tel\":\"400-663-1111\",\"number\":\"112393742650\"},{\"name\":\"全晨\",\"type\":\"QCKD\",\"letter\":\"Q\",\"tel\":\"0769-82026703\",\"number\":\"2233244233\"},{\"name\":\"如风达\",\"type\":\"RFD\",\"letter\":\"R\",\"tel\":\"400-010-6660\",\"number\":\"6800000635515\"},{\"name\":\"顺丰\",\"type\":\"SFEXPRESS\",\"letter\":\"S\",\"tel\":\"95338\",\"number\":\"952727764582\"},{\"name\":\"申通\",\"type\":\"STO\",\"letter\":\"S\",\"tel\":\"95543\",\"number\":\"403234843091\"},{\"name\":\"三态\",\"type\":\"SFC\",\"letter\":\"S\",\"tel\":\"400-881-8106\",\"number\":\"\"},{\"name\":\"顺达快递\",\"type\":\"SDEX\",\"letter\":\"S\",\"tel\":\"\",\"number\":\"SD100330011\"},{\"name\":\"苏宁\",\"type\":\"SUNING\",\"letter\":\"S\",\"tel\":\"95315\",\"number\":\"SN0030000041011500\"},{\"name\":\"盛辉\",\"type\":\"SHENGHUI\",\"letter\":\"S\",\"tel\":\"400-822-2222\",\"number\":\"238601965\"},{\"name\":\"速尔\",\"type\":\"SURE\",\"letter\":\"S\",\"tel\":\"400-158-9888\",\"number\":\"880218258595\"},{\"name\":\"盛丰\",\"type\":\"SFWL\",\"letter\":\"S\",\"tel\":\"4008-556688\",\"number\":\"39562381\"},{\"name\":\"天天\",\"type\":\"TTKDEX\",\"letter\":\"T\",\"tel\":\"4001-888-888\",\"number\":\"560623753489\"},{\"name\":\"TNT\",\"type\":\"TNT\",\"letter\":\"T\",\"tel\":\"800-820-9868\",\"number\":\"335939905\"},{\"name\":\"天地华宇\",\"type\":\"HOAU\",\"letter\":\"T\",\"tel\":\"400-808-6666\",\"number\":\"020286402\"},{\"name\":\"UPS\",\"type\":\"UPS\",\"letter\":\"U\",\"tel\":\"800-820-8388 400-820-8388\",\"number\":\"1ZV6509Y0468336755\"},{\"name\":\"万庚\",\"type\":\"VANGEN\",\"letter\":\"V\",\"tel\":\"\",\"number\":\"000000092332\"},{\"name\":\"万家物流\",\"type\":\"WANJIA\",\"letter\":\"W\",\"tel\":\"4001-156-561\",\"number\":\"31000001425628\"},{\"name\":\"文捷航空\",\"type\":\"GZWENJIE\",\"letter\":\"W\",\"tel\":\"020-36680069\",\"number\":\"\"},{\"name\":\"万象\",\"type\":\"EWINSHINE\",\"letter\":\"W\",\"tel\":\"400-820-8088\",\"number\":\"2225195562855\"},{\"name\":\"新邦\",\"type\":\"XBWL\",\"letter\":\"X\",\"tel\":\"4008-000-222\",\"number\":\"27454784\"},{\"name\":\"信丰\",\"type\":\"XFEXPRESS\",\"letter\":\"X\",\"tel\":\"4008-306-333\",\"number\":\"137417768454\"},{\"name\":\"圆通\",\"type\":\"YTO\",\"letter\":\"Y\",\"tel\":\"021-69777888 021-69777999\",\"number\":\"100668657244\"},{\"name\":\"韵达\",\"type\":\"YUNDA\",\"letter\":\"Y\",\"tel\":\"95546\",\"number\":\"1202237859178\"},{\"name\":\"邮政包裹\",\"type\":\"CHINAPOST\",\"letter\":\"Y\",\"tel\":\"11185\",\"number\":\"9610027635439\"},{\"name\":\"易通达\",\"type\":\"ETD\",\"letter\":\"Y\",\"tel\":\"0898-65339299\",\"number\":\"8000199455\"},{\"name\":\"易达通\",\"type\":\"QEXPRESS\",\"letter\":\"Y\",\"tel\":\"0064-09-8388681\",\"number\":\"ZY030841509NZ\"},{\"name\":\"源安达\",\"type\":\"YADEX\",\"letter\":\"Y\",\"tel\":\"0769-85021875\",\"number\":\"\"},{\"name\":\"宜送\",\"type\":\"YIEXPRESS\",\"letter\":\"Y\",\"tel\":\"\",\"number\":\"0125194699\"},{\"name\":\"运通\",\"type\":\"YTEXPRESS\",\"letter\":\"Y\",\"tel\":\"0769-81156999\",\"number\":\"666316719\"},{\"name\":\"越丰\",\"type\":\"YFEXPRESS\",\"letter\":\"Y\",\"tel\":\"(852) 2390 9969 \",\"number\":\"\"},{\"name\":\"远成\",\"type\":\"YCGWL\",\"letter\":\"Y\",\"tel\":\"400-820-1646\",\"number\":\"300011079526\"},{\"name\":\"亚风\",\"type\":\"BROADASIA\",\"letter\":\"Y\",\"tel\":\"4001-000-002\",\"number\":\"50042647157\"},{\"name\":\"优速\",\"type\":\"UC56\",\"letter\":\"Y\",\"tel\":\"400-1111-119\",\"number\":\"518148752202\"},{\"name\":\"原飞航\",\"type\":\"YFHEX\",\"letter\":\"Y\",\"tel\":\"0755-29778899 \\/ 29778100\",\"number\":\"8090337382\"},{\"name\":\"中通\",\"type\":\"ZTO\",\"letter\":\"Z\",\"tel\":\"95311\",\"number\":\"421447644512\"},{\"name\":\"宅急送\",\"type\":\"ZJS\",\"letter\":\"Z\",\"tel\":\"400-6789-000\",\"number\":\"A002083939830\"},{\"name\":\"中铁物流\",\"type\":\"ZTKY\",\"letter\":\"Z\",\"tel\":\"400-000-5566\",\"number\":\"119005886864\"},{\"name\":\"中国东方\",\"type\":\"COE\",\"letter\":\"Z\",\"tel\":\"755-83575000\",\"number\":\"\"},{\"name\":\"中铁快运\",\"type\":\"CRE\",\"letter\":\"Z\",\"tel\":\"95572\",\"number\":\"k19110633973\"},{\"name\":\"中通快运\",\"type\":\"ZTO56\",\"letter\":\"Z\",\"tel\":\"4000-270-270\",\"number\":\"201605182527\"},{\"name\":\"芝麻开门\",\"type\":\"ZMKMEX\",\"letter\":\"Z\",\"tel\":\"4001-056-056\",\"number\":\"611008025181750\"},{\"name\":\"中邮\",\"type\":\"CNPL\",\"letter\":\"Z\",\"tel\":\"11183\",\"number\":\"NE88379114242\"}]}";

        ExpressCompanyVo vo = JsonConverterUtil.jsonToObject(result, ExpressCompanyVo.class);
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO `express_company` (`code`, `name`, `keywords`, `sort`)\n" +
                "VALUES\n");
        List<Object> argsList = new ArrayList<>();
        for(ExpressCompanyItem item : vo.getResult()) {
            stringBuilder.append(" (?, ?, ?, ?),");
            argsList.add(item.getType().toLowerCase());
            argsList.add(item.getName());
            argsList.add(item.getName().toLowerCase());
            argsList.add(getSort(item.getType().toLowerCase()));
        }

        String sql = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        int id = connectionDB.executeUpdate(DataBaseEnum.WANZHOUMO, sql, argsList.toArray());
        System.out.println(id);
    }

    private static int getSort(String code) {
        switch (code) {
            case "sfexpress": return 1;
            case "sto": return 2;
            case "yto": return 3;
            case "yunda": return 4;
            case "chinapost": return 5;

            case "zto": return 6;
           case "ttkdex": return 7;
            case "zjs": return 8;
            case "htky": return 9;
            case "deppon": return 10;

            default:
                return 1000;
        }
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
