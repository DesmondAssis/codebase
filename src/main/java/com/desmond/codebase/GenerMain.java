package com.desmond.codebase;


import com.desmond.codebase.date.DateTimeUtils;
import com.desmond.codebase.url.UrlUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/5/26.
 */
public class GenerMain {

    public static void main(String[] args) {

    }

    private static void compare() {
        int[] arrOrg = {
                44914, 44912, 44877, 44915, 44839, 44834, 44820, 44683, 44890, 44850, 44876, 44867, 44852, 44845, 44844, 44843, 44833, 44848, 44784, 44634, 44558, 43809, 44905, 44734

        };

        int[] arrNew = {

                44914, 44912, 44877, 44915, 44839, 44834, 44820, 44683, 44890, 44850, 44876, 44867, 44852, 44845, 44844, 44843, 44833, 44848, 44784, 44634, 44558, 43809, 44905, 44734

        };
        for (int i = 0; i < arrOrg.length; i++) {
            if (arrOrg[i] != arrNew[i]) {
                throw new RuntimeException("tttt");
            }
        }

        System.out.print("\n\nnew not in org: ");

        printArr(arrNew, arrOrg);

        System.out.print("\n\norg not in new: ");

        printArr(arrOrg, arrNew);
    }

    private static void printArr(int[] arr1, int[] arr2) {
        for (int i : arr1) {
            boolean isTrue = false;
            for (int j : arr2) {
                if (i == j) {
                    isTrue = true;
                    break;
                }
            }
            if (!isTrue) {
                System.out.print(" " + i);
            }
        }
    }

    private static void parseUrl() {
        String[] urls = {
                "/activity/homeList?UUID=ffffffff-fc21-ff0e-8fc2-84750033c587&city_id=10896&fields_version=3.3&genre_id=41&lat=0.0&lon=0.0&offset=0&pagesize=15&timestamp=1466061991&v=3.0",
                "/activity/homelist?UUID=2046056C-7DAC-4861-B842-737BF6AD8452&fields_version=3.3&genre_id=41&lat=31.226363&lon=121.448436&pagesize=15&source=actcategory&timestamp=1465889984&v=3.0",
                "/activity/homelist?UUID=ffffffff-fc21-ff0e-8fc2-84750033c587&city_id=10896&fields_version=3.3&genre_id=41&lat=0.0&lon=0.0&offset=0&pagesize=15&sort=price_asc&timestamp=1466068235&v=3.0",
                "/activity/homelist?UUID=2046056C-7DAC-4861-B842-737BF6AD8452&fields_version=3.3&genre_id=41&lat=31.226363&lon=121.448436&pagesize=15&sort=price_asc&source=actcategory&timestamp=1465895573&v=3.0",

                "/activity/homelist?UUID=ffffffff-fc21-ff0e-8fc2-84750033c587&city_id=10898&fields_version=3.3&genre_id=115&offset=0&pagesize=15&timestamp=1466070922&v=3.0",
                "/activity/homelist?UUID=2046056C-7DAC-4861-B842-737BF6AD8452&city_id=10898&fields_version=3.3&genre_id=115&pagesize=15&source=actcategory&timestamp=1465898547&v=3.0",
                "/activity/homelist?UUID=ffffffff-fc21-ff0e-8fc2-84750033c587&city_id=10898&fields_version=3.3&genre_id=115&offset=0&pagesize=15&sort=collection_most&timestamp=1466070926&v=3.0",
                "/activity/homelist?UUID=2046056C-7DAC-4861-B842-737BF6AD8452&city_id=10898&fields_version=3.3&genre_id=115&pagesize=15&sort=collection_most&source=actcategory&timestamp=1465898551&v=3.0"
        };
        Map<String, String> totalMap = new HashMap<>();
        int i = 0;
        for (String url : urls) {
            Map<String, String> map = UrlUtil.parseUrl(url);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                totalMap.put(entry.getKey(), entry.getValue());
            }
            System.out.println(i + "::" + map.size());
        }

        System.out.println("total::" + totalMap.size());
        for (Map.Entry<String, String> entry : totalMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    private static String[][] hotCities = new String[][]{
            {"10898", "北京", "beijing"},
            {"10896", "上海", "shanghai"},
            {"10894", "广州", "guangzhou"},
            {"10893", "深圳", "shenzhen"},
            {"2269", "南京", "nanjing"},
            {"7502", "杭州", "hangzhou"},
            {"2267", "苏州", "suzhou"},
            {"10891", "成都", "chengdu"},
            {"10824", "重庆", "chongqing"},
            {"10897", "青岛", "qingdao"},
            {"2268", "厦门", "xiamen"},
            {"10889", "武汉", "wuhan"},
            {"10821", "郑州", "zhengzhou"},
            {"10895", "西安", "xian"},
            {"10892", "天津", "tianjin"},
            {"1070", "济南", "jinan"}
    };

    static int jbb = 100;
    static int jba = 100;

    private static void setDetailScore() {
        int now = DateTimeUtils.getCurrentTimeInSeconds();
        int pva = 0, sva = 0, orderN = 0, followN = 0;

        BigDecimal pv = BigDecimal.valueOf(pva);
        BigDecimal sv = BigDecimal.valueOf(sva);
        BigDecimal orderNum = BigDecimal.valueOf(orderN);
        BigDecimal followNum = BigDecimal.valueOf(followN);
        int onlineTime = 1465892894;
        int startTime = 1465890894;

        BigDecimal x = pv.intValue() == 0 ? BigDecimal.ZERO : sv.divide(pv, 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal y = orderNum.intValue() == 0 ? BigDecimal.ZERO : pv.divide(orderNum, 6, BigDecimal.ROUND_HALF_EVEN);
        double absonline = Math.abs(now - onlineTime);
        double absstart = Math.abs(now - startTime);
        BigDecimal pday = new BigDecimal(absonline).divide(new BigDecimal(86400), 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal sday = new BigDecimal(absstart).divide(new BigDecimal(86400), 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal m = new BigDecimal(Math.ceil(pday.floatValue()));
        BigDecimal n = new BigDecimal(Math.ceil(sday.floatValue()));


        BigDecimal z = new BigDecimal(0.1f);

        BigDecimal xBd = BigDecimal.ONE.divide(x.add(BigDecimal.valueOf(36)), 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal yBd = BigDecimal.ONE.divide(y.add(BigDecimal.valueOf(41)), 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal mBd = BigDecimal.ONE.divide(m.add(BigDecimal.ONE), 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal nBd = BigDecimal.ONE.divide(n.add(BigDecimal.TEN), 6, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal oBd = followNum.divide(BigDecimal.valueOf(5), 7, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal finalScore = xBd.add(yBd).add(z).add(mBd).add(nBd).add(oBd);

        if (m.intValue() <= 2) {
            finalScore = finalScore.add(BigDecimal.valueOf(2));
        }

        float score = finalScore.floatValue();

        System.out.println("::" + score);
        System.out.println(BigDecimal.ONE.divide(BigDecimal.valueOf(3), 3, BigDecimal.ROUND_HALF_EVEN));
    }

    public static String getDauDayKey(int day, String type, String item, int platform) {
        return new StringBuilder("daud:").append(day).append("|").append(type).append("|")
                .append(item).append("|").append(platform).toString();
    }
}
