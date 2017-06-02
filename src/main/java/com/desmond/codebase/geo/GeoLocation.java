package com.desmond.codebase.geo;

import static java.lang.Math.*;

public class GeoLocation {

    private Double latitude;
    private Double longitude;

    public GeoLocation() {
    }

    public GeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double distance(GeoLocation location) {
        return (6371 * acos(cos(toRadians(location.getLatitude())) * cos(toRadians(getLatitude())) * cos(toRadians(getLongitude()) - toRadians(location.getLongitude())) + sin(toRadians(location.getLatitude())) * sin(toRadians(getLatitude()))));
    }

    /**
     * 获取两点之间距离
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static Double distance(String lat1,String lon1,String lat2,String lon2){

        Double latD1 = Double.parseDouble(lat1);
        Double lonD1 = Double.parseDouble(lon1);
        Double latD2 = Double.parseDouble(lat2);
        Double lonD2 = Double.parseDouble(lon2);

        GeoLocation g1 = new GeoLocation(latD1,lonD1);
        GeoLocation g2 = new GeoLocation(latD2,lonD2);

        Double dis = g1.distance(g2);

        return dis;
    }
}
