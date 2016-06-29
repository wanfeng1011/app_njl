package com.app.njl.subject.hotel.model.entity.shoplist;

/**
 * Created by root on 2016/6/25.
 */
public class Location {
    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
