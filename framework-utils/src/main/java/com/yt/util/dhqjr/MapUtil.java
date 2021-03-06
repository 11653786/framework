package com.yt.util.dhqjr;

public class MapUtil {

    private static final double EARTH_RADIUS = 6378137.0;

    /**
     * @param lat_a 纬度 latitude a
     * @param lng_a 经度 longitude a
     * @param lat_b 纬度 latitude b
     * @param lng_b 经度 longitude b
     * @return
     */
    public static double gps2m(double lat_a, double lng_a, double lat_b,
                               double lng_b) {

        double radLat1 = (lat_a * Math.PI / 180.0);
        double radLat2 = (lat_b * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));

        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
