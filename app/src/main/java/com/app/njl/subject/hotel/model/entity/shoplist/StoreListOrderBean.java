package com.app.njl.subject.hotel.model.entity.shoplist;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 2016/7/3.
 */
public class StoreListOrderBean {
    /**
     * code : 1
     * message : {"result":[{"foodTotalNum":5,"foodAveragePrice":46,"resortId":1,"shopId":11,"shopName":"店家\u201411","shopPic":"http://www.whateverblake.com/shoplist_11.jpg","adShop":0,"addressProvince":"安徽","addressCity":"池州","addressDistinct":"九华山","addressDetail":"街道11","location":{"lon":52,"lat":20},"landmarkId":3,"totalCommentNum":1414,"goodCommentNum":479,"recommendNum":2836,"defaultSort":17}],"totalNumber":20,"pageSize":10,"pageNum":1}
     * type : getShopRestaurantListPaging
     */

    private int code;
    /**
     * result : [{"foodTotalNum":5,"foodAveragePrice":46,"resortId":1,"shopId":11,"shopName":"店家\u201411","shopPic":"http://www.whateverblake.com/shoplist_11.jpg","adShop":0,"addressProvince":"安徽","addressCity":"池州","addressDistinct":"九华山","addressDetail":"街道11","location":{"lon":52,"lat":20},"landmarkId":3,"totalCommentNum":1414,"goodCommentNum":479,"recommendNum":2836,"defaultSort":17}]
     * totalNumber : 20
     * pageSize : 10
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

    public static class MessageBean implements Serializable {
        private int totalNumber;
        private int pageSize;
        private int pageNum;
        /**
         * foodTotalNum : 5
         * foodAveragePrice : 46
         * resortId : 1
         * shopId : 11
         * shopName : 店家—11
         * shopPic : http://www.whateverblake.com/shoplist_11.jpg
         * adShop : 0
         * addressProvince : 安徽
         * addressCity : 池州
         * addressDistinct : 九华山
         * addressDetail : 街道11
         * location : {"lon":52,"lat":20}
         * landmarkId : 3
         * totalCommentNum : 1414
         * goodCommentNum : 479
         * recommendNum : 2836
         * defaultSort : 17
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
            private int foodTotalNum;
            private int foodAveragePrice;
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
             * lon : 52
             * lat : 20
             */

            private LocationBean location;
            private int landmarkId;
            private int totalCommentNum;
            private int goodCommentNum;
            private int recommendNum;
            private int defaultSort;

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
            }
        }
    }
}
