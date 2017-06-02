package com.desmond.codebase.geo;


import static com.desmond.codebase.print.Print.printnb;

/**
 * Created by Li.Xiaochuan on 15/11/14.
 */
public class GeohashUtil {
    public static void main(String[] args) {
        encode(31.224868d, 121.448383d);
    }

    public static void encode(double lat, double lon) {
        Geohashs geohash = new Geohashs();
        System.out.println(geohash.encode(lat, lon));
//        printnb(GeoHash.geoHashStringWithCharacterPrecision(lat, lon, 12));

    }
}
