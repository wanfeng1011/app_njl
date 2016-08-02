package com.app.njl.subject.hotel.model.entity.shoplist;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by jiaxx on 2016/8/2 0002.
 */
public class ShopCommonMessage extends BaseEntity implements Serializable {
    private int shopId;
    private String shopName;
    private Location location;
    private String addressProvince;
    private String addressCity;
    private String addressDistinct;
    private String addressDetail;
    private int landmarkId;
    private int totalCommentNum;
    private int goodCommentNum;
    private int recommendNum;
    private int defaultSort;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDistinct() {
        return addressDistinct;
    }

    public void setAddressDistinct(String addressDistinct) {
        this.addressDistinct = addressDistinct;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public int getTotalCommentNum() {
        return totalCommentNum;
    }

    public void setTotalCommentNum(int totalCommentNum) {
        this.totalCommentNum = totalCommentNum;
    }

    public int getGoodCommentNum() {
        return goodCommentNum;
    }

    public void setGoodCommentNum(int goodCommentNum) {
        this.goodCommentNum = goodCommentNum;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }

    public int getDefaultSort() {
        return defaultSort;
    }

    public void setDefaultSort(int defaultSort) {
        this.defaultSort = defaultSort;
    }

    public static class Location implements Serializable {
        private double lon;
        private double lat;

        public Location(double lon, double lat) {
            this.lon = lon;
            this.lat = lat;
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
    }
}
