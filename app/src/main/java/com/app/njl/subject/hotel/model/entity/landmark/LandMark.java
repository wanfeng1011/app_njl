package com.app.njl.subject.hotel.model.entity.landmark;

/**
 * Created by root on 2016/6/26.
 */
public class LandMark {
    private int landmarkId;
    private int resortId;
    private String landmarkName;
    private double lon;
    private double lat;
    private int isValid;

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public int getResortId() {
        return resortId;
    }

    public void setResortId(int resortId) {
        this.resortId = resortId;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

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

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "LandMark{" +
                "landmarkId=" + landmarkId +
                ", resortId=" + resortId +
                ", landmarkName='" + landmarkName + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", isValid=" + isValid +
                '}';
    }
}
