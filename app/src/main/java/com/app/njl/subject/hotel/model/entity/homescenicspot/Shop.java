package com.app.njl.subject.hotel.model.entity.homescenicspot;

/**
 * Created by root on 2016/6/13.
 */
public class Shop {
    private int shopId;
    private String shopName;
    private String shopUrl;
    private int goodNum;
    private int recommendNum;
    private int pruductType;
    private String picUrl;
    private String resortName;
    private int resortId;

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

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }

    public int getPruductType() {
        return pruductType;
    }

    public void setPruductType(int pruductType) {
        this.pruductType = pruductType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getResortName() {
        return resortName;
    }

    public void setResortName(String resortName) {
        this.resortName = resortName;
    }

    public int getResortId() {
        return resortId;
    }

    public void setResortId(int resortId) {
        this.resortId = resortId;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                ", goodNum=" + goodNum +
                ", recommendNum=" + recommendNum +
                ", pruductType=" + pruductType +
                ", picUrl='" + picUrl + '\'' +
                ", resortName='" + resortName + '\'' +
                ", resortId=" + resortId +
                '}';
    }
}
