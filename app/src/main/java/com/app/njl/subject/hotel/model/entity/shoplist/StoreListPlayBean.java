package com.app.njl.subject.hotel.model.entity.shoplist;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 2016/7/3.
 */
public class StoreListPlayBean {

    /**
     * code : 1
     * message : {"result":[{"travelTicketState":1,"travelGuideState":0,"travelSpecialState":0,"travelGuideLowestPrice":0,"travelSpecialLowestPrice":0,"travelTicketLowestPrice":11,"resortId":1,"shopId":5,"shopName":"店家-5","shopPic":"http://www.whateverblake.com/shoplist_5.jpg","adShop":1,"addressProvince":"安徽","addressCity":"池州","addressDistinct":"九华山","addressDetail":"街道5","location":{"lon":14,"lat":50},"landmarkId":1,"totalCommentNum":1882,"goodCommentNum":1448,"recommendNum":1138,"defaultSort":18},{"travelTicketState":1,"travelGuideState":1,"travelSpecialState":0,"travelGuideLowestPrice":95,"travelSpecialLowestPrice":0,"travelTicketLowestPrice":317,"resortId":1,"shopId":9,"shopName":"店家-9","shopPic":"http://www.whateverblake.com/shoplist_9.jpg","adShop":0,"addressProvince":"安徽","addressCity":"池州","addressDistinct":"九华山","addressDetail":"街道9","location":{"lon":2,"lat":66},"landmarkId":1,"totalCommentNum":1928,"goodCommentNum":984,"recommendNum":1928,"defaultSort":18}],"totalNumber":20,"pageSize":2,"pageNum":1}
     * type : getShopTravelListPaging
     */

    private int code;
    /**
     * result : [{"travelTicketState":1,"travelGuideState":0,"travelSpecialState":0,"travelGuideLowestPrice":0,"travelSpecialLowestPrice":0,"travelTicketLowestPrice":11,"resortId":1,"shopId":5,"shopName":"店家-5","shopPic":"http://www.whateverblake.com/shoplist_5.jpg","adShop":1,"addressProvince":"安徽","addressCity":"池州","addressDistinct":"九华山","addressDetail":"街道5","location":{"lon":14,"lat":50},"landmarkId":1,"totalCommentNum":1882,"goodCommentNum":1448,"recommendNum":1138,"defaultSort":18},{"travelTicketState":1,"travelGuideState":1,"travelSpecialState":0,"travelGuideLowestPrice":95,"travelSpecialLowestPrice":0,"travelTicketLowestPrice":317,"resortId":1,"shopId":9,"shopName":"店家-9","shopPic":"http://www.whateverblake.com/shoplist_9.jpg","adShop":0,"addressProvince":"安徽","addressCity":"池州","addressDistinct":"九华山","addressDetail":"街道9","location":{"lon":2,"lat":66},"landmarkId":1,"totalCommentNum":1928,"goodCommentNum":984,"recommendNum":1928,"defaultSort":18}]
     * totalNumber : 20
     * pageSize : 2
     * pageNum : 1
     */

    private MessageBean message;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class MessageBean {
        private int totalNumber;
        private int pageSize;
        private int pageNum;
        /**
         * travelTicketState : 1
         * travelGuideState : 0
         * travelSpecialState : 0
         * travelGuideLowestPrice : 0
         * travelSpecialLowestPrice : 0
         * travelTicketLowestPrice : 11
         * resortId : 1
         * shopId : 5
         * shopName : 店家-5
         * shopPic : http://www.whateverblake.com/shoplist_5.jpg
         * adShop : 1
         * addressProvince : 安徽
         * addressCity : 池州
         * addressDistinct : 九华山
         * addressDetail : 街道5
         * location : {"lon":14,"lat":50}
         * landmarkId : 1
         * totalCommentNum : 1882
         * goodCommentNum : 1448
         * recommendNum : 1138
         * defaultSort : 18
         */

        private List<ResultBean> result;

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean extends BaseEntity implements Serializable {
            private int travelTicketState;
            private int travelGuideState;
            private int travelSpecialState;
            private double travelGuideLowestPrice;
            private double travelSpecialLowestPrice;
            private double travelTicketLowestPrice;
            private int resortId;
            private int shopId;
            private String shopName;
            private String shopPic;
            private int adShop;
            private String addressProvince;
            private String addressCity;
            private String addressDistinct;
            private String addressDetail;
            /**
             * lon : 14
             * lat : 50
             */

            private LocationBean location;
            private int landmarkId;
            private int totalCommentNum;
            private int goodCommentNum;
            private int recommendNum;
            private int defaultSort;

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

            public int getTravelSpecialState() {
                return travelSpecialState;
            }

            public void setTravelSpecialState(int travelSpecialState) {
                this.travelSpecialState = travelSpecialState;
            }

            public double getTravelGuideLowestPrice() {
                return travelGuideLowestPrice;
            }

            public void setTravelGuideLowestPrice(double travelGuideLowestPrice) {
                this.travelGuideLowestPrice = travelGuideLowestPrice;
            }

            public double getTravelSpecialLowestPrice() {
                return travelSpecialLowestPrice;
            }

            public void setTravelSpecialLowestPrice(double travelSpecialLowestPrice) {
                this.travelSpecialLowestPrice = travelSpecialLowestPrice;
            }

            public double getTravelTicketLowestPrice() {
                return travelTicketLowestPrice;
            }

            public void setTravelTicketLowestPrice(double travelTicketLowestPrice) {
                this.travelTicketLowestPrice = travelTicketLowestPrice;
            }

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

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
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

            public static class LocationBean {
                private int lon;
                private int lat;

                public int getLon() {
                    return lon;
                }

                public void setLon(int lon) {
                    this.lon = lon;
                }

                public int getLat() {
                    return lat;
                }

                public void setLat(int lat) {
                    this.lat = lat;
                }
            }
        }
    }
}
