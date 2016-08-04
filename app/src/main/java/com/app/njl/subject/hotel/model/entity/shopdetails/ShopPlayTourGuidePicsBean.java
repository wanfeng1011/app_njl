package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/8/3 0003.
 */
public class ShopPlayTourGuidePicsBean {
    /**
     * code : 1
     * message : [{"shopId":1,"guideId":1,"picUrl":"http://www.whateverblake.com/shop_guide_picture_3.jpg","picName":"美丽的导游","picDesc":"导游图片"},{"shopId":1,"guideId":1,"picUrl":"http://www.whateverblake.com/shop_guide_picture_2.jpg","picName":"美丽的导游","picDesc":"导游图片"},{"shopId":1,"guideId":1,"picUrl":"http://www.whateverblake.com/shop_guide_picture_1.jpg","picName":"美丽的导游","picDesc":"导游图片"}]
     * type : getShopTravelGuidePic
     */

    private int code;
    private String type;
    /**
     * shopId : 1
     * guideId : 1
     * picUrl : http://www.whateverblake.com/shop_guide_picture_3.jpg
     * picName : 美丽的导游
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
        private int guideId;
        private String picUrl;
        private String picName;
        private String picDesc;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getGuideId() {
            return guideId;
        }

        public void setGuideId(int guideId) {
            this.guideId = guideId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getPicName() {
            return picName;
        }

        public void setPicName(String picName) {
            this.picName = picName;
        }

        public String getPicDesc() {
            return picDesc;
        }

        public void setPicDesc(String picDesc) {
            this.picDesc = picDesc;
        }
    }
}
