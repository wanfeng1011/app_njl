package com.app.njl.subject.hotel.model.entity.shopdetails;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiaxx on 2016/7/26 0026.
 */
public class ShopSpecialtyDetailData {
    /**
     * code : 1
     * message : [{"shopId":8,"specialtyId":2,"specialtyPrice":78,"specialtyComment":"特产，来自大自然","specialtyPictureUrl":"http://www.whateverblake.com/shop_specialty_item_2.jpg","specialWeight":1522,"weightUnit":1,"specialtyNumber":83}]
     * type : getShopSpecialtyInfo
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * specialtyId : 2
     * specialtyPrice : 78
     * specialtyComment : 特产，来自大自然
     * specialtyPictureUrl : http://www.whateverblake.com/shop_specialty_item_2.jpg
     * specialWeight : 1522
     * weightUnit : 1
     * specialtyNumber : 83
     */

    private List<MessageBean> message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean extends BaseEntity implements Serializable {
        private int shopId;
        private int specialtyId;
        private double specialtyPrice;
        private String specialtyComment;
        private String specialtyPictureUrl;
        private int specialWeight;
        private int weightUnit;
        private int specialtyNumber;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getSpecialtyId() {
            return specialtyId;
        }

        public void setSpecialtyId(int specialtyId) {
            this.specialtyId = specialtyId;
        }

        public double getSpecialtyPrice() {
            return specialtyPrice;
        }

        public void setSpecialtyPrice(double specialtyPrice) {
            this.specialtyPrice = specialtyPrice;
        }

        public String getSpecialtyComment() {
            return specialtyComment;
        }

        public void setSpecialtyComment(String specialtyComment) {
            this.specialtyComment = specialtyComment;
        }

        public String getSpecialtyPictureUrl() {
            return specialtyPictureUrl;
        }

        public void setSpecialtyPictureUrl(String specialtyPictureUrl) {
            this.specialtyPictureUrl = specialtyPictureUrl;
        }

        public int getSpecialWeight() {
            return specialWeight;
        }

        public void setSpecialWeight(int specialWeight) {
            this.specialWeight = specialWeight;
        }

        public int getWeightUnit() {
            return weightUnit;
        }

        public void setWeightUnit(int weightUnit) {
            this.weightUnit = weightUnit;
        }

        public int getSpecialtyNumber() {
            return specialtyNumber;
        }

        public void setSpecialtyNumber(int specialtyNumber) {
            this.specialtyNumber = specialtyNumber;
        }
    }
}
