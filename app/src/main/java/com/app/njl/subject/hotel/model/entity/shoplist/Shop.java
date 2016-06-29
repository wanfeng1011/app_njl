package com.app.njl.subject.hotel.model.entity.shoplist;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

/**
 * Created by root on 2016/6/25.
 */
public class Shop extends BaseEntity {
    private int resortId;
    private int shopId;
    private String shopName;
    private String shopPic;
    private int adShop;
    private int liveState;
    private int foodState;
    private int specialState;
    private String addressProvince;
    private String addressCity;
    private String addressDistinct;
    private String addressDetail;
    private Location location;
    private int landmarkId;
    private int l_totalCommentNum;
    private int l_goodCommentNum;
    private int l_recommendNum;
    private int f_totalCommentNum;
    private int f_goodCommentNum;
    private int f_recommendNum;
    private int t_totalCommentNum;
    private int t_goodCommentNum;
    private int t_recommendNum;
    private int s_totalCommentNum;
    private int s_goodCommentNum;
    private int s_recommendNum;
    private int liveLowestPrice;
    private int foodTotalNum;
    private int foodAveragePrice;
    private int travelTicketLowestPrice;
    private int travelGuideLowesrPrice;
    private int travelSpecialLowestPrice;
    private int specialFoodLowestPrice;
    private int defaultSort;
    private int travelSpecialState;
    private int travelTicketState;
    private int travelGuideState;

    public int getResortId() {
        return resortId;
    }

    public void setResortId(int resortId) {
        this.resortId = resortId;
    }

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

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public int getAdShop() {
        return adShop;
    }

    public void setAdShop(int adShop) {
        this.adShop = adShop;
    }

    public int getLiveState() {
        return liveState;
    }

    public void setLiveState(int liveState) {
        this.liveState = liveState;
    }

    public int getFoodState() {
        return foodState;
    }

    public void setFoodState(int foodState) {
        this.foodState = foodState;
    }

    public int getSpecialState() {
        return specialState;
    }

    public void setSpecialState(int specialState) {
        this.specialState = specialState;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public int getL_totalCommentNum() {
        return l_totalCommentNum;
    }

    public void setL_totalCommentNum(int l_totalCommentNum) {
        this.l_totalCommentNum = l_totalCommentNum;
    }

    public int getL_goodCommentNum() {
        return l_goodCommentNum;
    }

    public void setL_goodCommentNum(int l_goodCommentNum) {
        this.l_goodCommentNum = l_goodCommentNum;
    }

    public int getL_recommendNum() {
        return l_recommendNum;
    }

    public void setL_recommendNum(int l_recommendNum) {
        this.l_recommendNum = l_recommendNum;
    }

    public int getF_totalCommentNum() {
        return f_totalCommentNum;
    }

    public void setF_totalCommentNum(int f_totalCommentNum) {
        this.f_totalCommentNum = f_totalCommentNum;
    }

    public int getF_goodCommentNum() {
        return f_goodCommentNum;
    }

    public void setF_goodCommentNum(int f_goodCommentNum) {
        this.f_goodCommentNum = f_goodCommentNum;
    }

    public int getF_recommendNum() {
        return f_recommendNum;
    }

    public void setF_recommendNum(int f_recommendNum) {
        this.f_recommendNum = f_recommendNum;
    }

    public int getT_totalCommentNum() {
        return t_totalCommentNum;
    }

    public void setT_totalCommentNum(int t_totalCommentNum) {
        this.t_totalCommentNum = t_totalCommentNum;
    }

    public int getT_goodCommentNum() {
        return t_goodCommentNum;
    }

    public void setT_goodCommentNum(int t_goodCommentNum) {
        this.t_goodCommentNum = t_goodCommentNum;
    }

    public int getT_recommendNum() {
        return t_recommendNum;
    }

    public void setT_recommendNum(int t_recommendNum) {
        this.t_recommendNum = t_recommendNum;
    }

    public int getS_totalCommentNum() {
        return s_totalCommentNum;
    }

    public void setS_totalCommentNum(int s_totalCommentNum) {
        this.s_totalCommentNum = s_totalCommentNum;
    }

    public int getS_goodCommentNum() {
        return s_goodCommentNum;
    }

    public void setS_goodCommentNum(int s_goodCommentNum) {
        this.s_goodCommentNum = s_goodCommentNum;
    }

    public int getS_recommendNum() {
        return s_recommendNum;
    }

    public void setS_recommendNum(int s_recommendNum) {
        this.s_recommendNum = s_recommendNum;
    }

    public int getLiveLowestPrice() {
        return liveLowestPrice;
    }

    public void setLiveLowestPrice(int liveLowestPrice) {
        this.liveLowestPrice = liveLowestPrice;
    }

    public int getFoodTotalNum() {
        return foodTotalNum;
    }

    public void setFoodTotalNum(int foodTotalNum) {
        this.foodTotalNum = foodTotalNum;
    }

    public int getFoodAveragePrice() {
        return foodAveragePrice;
    }

    public void setFoodAveragePrice(int foodAveragePrice) {
        this.foodAveragePrice = foodAveragePrice;
    }

    public int getTravelTicketLowestPrice() {
        return travelTicketLowestPrice;
    }

    public void setTravelTicketLowestPrice(int travelTicketLowestPrice) {
        this.travelTicketLowestPrice = travelTicketLowestPrice;
    }

    public int getTravelGuideLowesrPrice() {
        return travelGuideLowesrPrice;
    }

    public void setTravelGuideLowesrPrice(int travelGuideLowesrPrice) {
        this.travelGuideLowesrPrice = travelGuideLowesrPrice;
    }

    public int getTravelSpecialLowestPrice() {
        return travelSpecialLowestPrice;
    }

    public void setTravelSpecialLowestPrice(int travelSpecialLowestPrice) {
        this.travelSpecialLowestPrice = travelSpecialLowestPrice;
    }

    public int getSpecialFoodLowestPrice() {
        return specialFoodLowestPrice;
    }

    public void setSpecialFoodLowestPrice(int specialFoodLowestPrice) {
        this.specialFoodLowestPrice = specialFoodLowestPrice;
    }

    public int getDefaultSort() {
        return defaultSort;
    }

    public void setDefaultSort(int defaultSort) {
        this.defaultSort = defaultSort;
    }

    public int getTravelSpecialState() {
        return travelSpecialState;
    }

    public void setTravelSpecialState(int travelSpecialState) {
        this.travelSpecialState = travelSpecialState;
    }

    public int getTravelTicketState() {
        return travelTicketState;
    }

    public void setTravelTicketState(int travelTicketState) {
        this.travelTicketState = travelTicketState;
    }

    public int getTravelGuideState() {
        return travelGuideState;
    }

    public void setTravelGuideState(int travelGuideState) {
        this.travelGuideState = travelGuideState;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "resortId=" + resortId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopPic='" + shopPic + '\'' +
                ", adShop=" + adShop +
                ", liveState=" + liveState +
                ", foodState=" + foodState +
                ", specialState=" + specialState +
                ", addressProvince='" + addressProvince + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressDistinct='" + addressDistinct + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", location=" + location +
                ", landmarkId=" + landmarkId +
                ", l_totalCommentNum=" + l_totalCommentNum +
                ", l_goodCommentNum=" + l_goodCommentNum +
                ", l_recommendNum=" + l_recommendNum +
                ", f_totalCommentNum=" + f_totalCommentNum +
                ", f_goodCommentNum=" + f_goodCommentNum +
                ", f_recommendNum=" + f_recommendNum +
                ", t_totalCommentNum=" + t_totalCommentNum +
                ", t_goodCommentNum=" + t_goodCommentNum +
                ", t_recommendNum=" + t_recommendNum +
                ", s_totalCommentNum=" + s_totalCommentNum +
                ", s_goodCommentNum=" + s_goodCommentNum +
                ", s_recommendNum=" + s_recommendNum +
                ", liveLowestPrice=" + liveLowestPrice +
                ", foodTotalNum=" + foodTotalNum +
                ", foodAveragePrice=" + foodAveragePrice +
                ", travelTicketLowestPrice=" + travelTicketLowestPrice +
                ", travelGuideLowesrPrice=" + travelGuideLowesrPrice +
                ", travelSpecialLowestPrice=" + travelSpecialLowestPrice +
                ", specialFoodLowestPrice=" + specialFoodLowestPrice +
                ", defaultSort=" + defaultSort +
                ", travelSpecialState=" + travelSpecialState +
                ", travelTicketState=" + travelTicketState +
                ", travelGuideState=" + travelGuideState +
                '}';
    }
}
