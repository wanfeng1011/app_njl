package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/8/3 0003.
 */
public class ShopPlaySelfServicePicsBean {
    /**
     * code : 1
     * message : [{"shopId":8,"activityId":1,"picName":"美丽的导游","picUrl":"http://www.whateverblake.com/shop_familyactivity_picture_3.jpg","picDesc":"导游图片"},{"shopId":8,"activityId":1,"picName":"美丽的导游","picUrl":"http://www.whateverblake.com/shop_familyactivity_picture_2.jpg","picDesc":"导游图片"},{"shopId":8,"activityId":1,"picName":"美丽的导游","picUrl":"http://www.whateverblake.com/shop_familyactivity_picture_1.jpg","picDesc":"导游图片"},{"shopId":8,"activityId":1,"picName":"好玩的项目","picUrl":"http://www.whateverblake.com/shop_familyactivity_picture_3.jpg","picDesc":"好玩的农家游玩项目"},{"shopId":8,"activityId":1,"picName":"好玩的项目","picUrl":"http://www.whateverblake.com/shop_familyactivity_picture_2.jpg","picDesc":"好玩的农家游玩项目"},{"shopId":8,"activityId":1,"picName":"好玩的项目","picUrl":"http://www.whateverblake.com/shop_familyactivity_picture_1.jpg","picDesc":"好玩的农家游玩项目"}]
     * type : getShopTravelFamilyActivityPic
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * activityId : 1
     * picName : 美丽的导游
     * picUrl : http://www.whateverblake.com/shop_familyactivity_picture_3.jpg
     * picDesc : 导游图片
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

    public static class MessageBean {
        private int shopId;
        private int activityId;
        private String picName;
        private String picUrl;
        private String picDesc;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getPicName() {
            return picName;
        }

        public void setPicName(String picName) {
            this.picName = picName;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getPicDesc() {
            return picDesc;
        }

        public void setPicDesc(String picDesc) {
            this.picDesc = picDesc;
        }
    }
}
