package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/7/29 0029.
 */
public class ShopRoomPicsBean {
    /**
     * code : 1
     * message : [{"shopId":8,"roomId":0,"picUrl":"http://www.whateverblake.com/shop_room_pic_2.jpg","picName":"卧室","picDesc":"房间信息图片"},{"shopId":8,"roomId":0,"picUrl":"http://www.whateverblake.com/shop_room_pic_5.jpg","picName":"卧室","picDesc":"房间信息图片"},{"shopId":8,"roomId":0,"picUrl":"http://www.whateverblake.com/shop_room_pic_1.jpg","picName":"卧室","picDesc":"房间信息图片"},{"shopId":8,"roomId":0,"picUrl":"http://www.whateverblake.com/shop_room_pic_3.jpg","picName":"卧室","picDesc":"房间信息图片"},{"shopId":8,"roomId":0,"picUrl":"http://www.whateverblake.com/shop_room_pic_4.jpg","picName":"卧室","picDesc":"房间信息图片"}]
     * type : getShopRoomPic
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * roomId : 0
     * picUrl : http://www.whateverblake.com/shop_room_pic_2.jpg
     * picName : 卧室
     * picDesc : 房间信息图片
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
        private int roomId;
        private String picUrl;
        private String picName;
        private String picDesc;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
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
