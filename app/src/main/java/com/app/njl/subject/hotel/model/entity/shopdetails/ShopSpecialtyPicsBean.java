package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/7/29 0029.
 */
public class ShopSpecialtyPicsBean {
    /**
     * code : 1
     * message : [{"shopId":8,"specialtyId":2,"picUrl":"http://www.whateverblake.com/shop_specialty_pic_1.jpg","picDesc":"特产信息图片","picName":"特产"},{"shopId":8,"specialtyId":2,"picUrl":"http://www.whateverblake.com/shop_specialty_pic_2.jpg","picDesc":"特产信息图片","picName":"特产"},{"shopId":8,"specialtyId":2,"picUrl":"http://www.whateverblake.com/shop_specialty_pic_5.jpg","picDesc":"特产信息图片","picName":"特产"},{"shopId":8,"specialtyId":2,"picUrl":"http://www.whateverblake.com/shop_specialty_pic_3.jpg","picDesc":"特产信息图片","picName":"特产"},{"shopId":8,"specialtyId":2,"picUrl":"http://www.whateverblake.com/shop_specialty_pic_4.jpg","picDesc":"特产信息图片","picName":"特产"}]
     * type : getShopSpecialtyPic
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * specialtyId : 2
     * picUrl : http://www.whateverblake.com/shop_specialty_pic_1.jpg
     * picDesc : 特产信息图片
     * picName : 特产
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
        private int specialtyId;
        private String picUrl;
        private String picDesc;
        private String picName;

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

        public String getPicName() {
            return picName;
        }

        public void setPicName(String picName) {
            this.picName = picName;
        }
    }
}
