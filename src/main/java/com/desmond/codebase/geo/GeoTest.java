package com.desmond.codebase.geo;


import java.util.TreeMap;

import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/10/31.
 */
public class GeoTest {
    public static void main(String[] args) {
        TreeMap<Integer, String> tree = new TreeMap<>();
        tree.put(3, "3");
        tree.put(1, "1");
        tree.put(5, "5");
        for(String str : tree.values()) {
            printnb(str);
        }
        //距离的计算 1604.7368272982
//        double userLon = 121.448723, userLat = 35.226060;
//        double actLon = 114.1362629000, actLat = 22.2829539000;
//        GeoLocation userLocation = new GeoLocation();
//        userLocation.setLatitude(Double.valueOf(userLat));
//        userLocation.setLongitude(Double.valueOf(userLon));
//
//        GeoLocation actLocation = new GeoLocation(
//                Double.valueOf(actLat),
//                Double.valueOf(actLon));
//        double abs = abs(userLocation.distance(actLocation));
//        long distance = round(abs);
//        DecimalFormat df = new DecimalFormat("#.###");
//        String format = df.format(abs);
//        printnb(NumberUtils.toDouble(format));


    }
}
