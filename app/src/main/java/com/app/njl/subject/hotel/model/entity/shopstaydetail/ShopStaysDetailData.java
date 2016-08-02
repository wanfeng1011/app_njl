package com.app.njl.subject.hotel.model.entity.shopstaydetail;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/7/17.
 */
public class ShopStaysDetailData {
    /**
     * code : 1
     * message : [{"roomId":1,"shopId":1,"roomName":"房间_1","roomType":2,"roomSquare":"11-18","isWindow":0,"bedDescribe":"舒适,美梦","roomCapacity":3,"roomFloor":"3-6","roomDescribe":"自然，清新，梦回故里","picUrl":"http://www.whateverblake.com/shop_room_bed_demo.jpg","price":319,"roomNumber":49},{"roomId":2,"shopId":1,"roomName":"房间_2","roomType":3,"roomSquare":"8-15","isWindow":0,"bedDescribe":"舒适,美梦","roomCapacity":4,"roomFloor":"0-3","roomDescribe":"自然，清新，梦回故里","picUrl":"http://www.whateverblake.com/shop_room_bed_demo.jpg","price":54,"roomNumber":24},{"roomId":0,"shopId":1,"roomName":"房间_0","roomType":1,"roomSquare":"8-15","isWindow":1,"bedDescribe":"舒适,美梦","roomCapacity":2,"roomFloor":"0-3","roomDescribe":"自然，清新，梦回故里","picUrl":"http://www.whateverblake.com/shop_room_bed_demo.jpg","price":68,"roomNumber":9}]
     * type : getShopProductByProductType
     */

    private int code;
    private String type;
    /**
     * roomId : 1
     * shopId : 1
     * roomName : 房间_1
     * roomType : 2
     * roomSquare : 11-18
     * isWindow : 0
     * bedDescribe : 舒适,美梦
     * roomCapacity : 3
     * roomFloor : 3-6
     * roomDescribe : 自然，清新，梦回故里
     * picUrl : http://www.whateverblake.com/shop_room_bed_demo.jpg
     * price : 319
     * roomNumber : 49
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
        private int roomId;
        private int shopId;
        private String roomName;
        private int roomType;
        private String roomSquare;
        private int isWindow;
        private String bedDescribe;
        private int roomCapacity;
        private String roomFloor;
        private String roomDescribe;
        private String picUrl;
        private double price;
        private int roomNumber;

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public int getRoomType() {
            return roomType;
        }

        public void setRoomType(int roomType) {
            this.roomType = roomType;
        }

        public String getRoomSquare() {
            return roomSquare;
        }

        public void setRoomSquare(String roomSquare) {
            this.roomSquare = roomSquare;
        }

        public int getIsWindow() {
            return isWindow;
        }

        public void setIsWindow(int isWindow) {
            this.isWindow = isWindow;
        }

        public String getBedDescribe() {
            return bedDescribe;
        }

        public void setBedDescribe(String bedDescribe) {
            this.bedDescribe = bedDescribe;
        }

        public int getRoomCapacity() {
            return roomCapacity;
        }

        public void setRoomCapacity(int roomCapacity) {
            this.roomCapacity = roomCapacity;
        }

        public String getRoomFloor() {
            return roomFloor;
        }

        public void setRoomFloor(String roomFloor) {
            this.roomFloor = roomFloor;
        }

        public String getRoomDescribe() {
            return roomDescribe;
        }

        public void setRoomDescribe(String roomDescribe) {
            this.roomDescribe = roomDescribe;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
        }
    }
    //{"code":1,"message":[{"roomId":1,"shopId":1,"roomName":"房间_1","roomType":2,"roomSquare":"11-18","isWindow":0,"bedDescribe":"舒适,美梦","roomCapacity":3,"roomFloor":"3-6","roomDescribe":"自然，清新，梦回故里","picUrl":"http://www.whateverblake.com/shop_room_bed_demo.jpg","price":319,"roomNumber":49},{"roomId":2,"shopId":1,"roomName":"房间_2","roomType":3,"roomSquare":"8-15","isWindow":0,"bedDescribe":"舒适,美梦","roomCapacity":4,"roomFloor":"0-3","roomDescribe":"自然，清新，梦回故里","picUrl":"http://www.whateverblake.com/shop_room_bed_demo.jpg","price":54,"roomNumber":24},{"roomId":0,"shopId":1,"roomName":"房间_0","roomType":1,"roomSquare":"8-15","isWindow":1,"bedDescribe":"舒适,美梦","roomCapacity":2,"roomFloor":"0-3","roomDescribe":"自然，清新，梦回故里","picUrl":"http://www.whateverblake.com/shop_room_bed_demo.jpg","price":68,"roomNumber":9}],"type":"getShopProductByProductType"}
    //{"code":1,"message":[{"shopId":1,"courseId":1,"courseName":"菜品_1","spicyLevel":1,"coursePrice":204.0,"courseNumber":52,"isSellOut":1},{"shopId":1,"courseId":3,"courseName":"菜品_3","spicyLevel":0,"coursePrice":122.0,"courseNumber":72,"isSellOut":0},{"shopId":1,"courseId":9,"courseName":"菜品_9","spicyLevel":0,"coursePrice":228.0,"courseNumber":65,"isSellOut":0},{"shopId":1,"courseId":10,"courseName":"菜品_10","spicyLevel":1,"coursePrice":228.0,"courseNumber":153,"isSellOut":0},{"shopId":1,"courseId":19,"courseName":"菜品_19","spicyLevel":1,"coursePrice":182.0,"courseNumber":136,"isSellOut":0},{"shopId":1,"courseId":8,"courseName":"菜品_8","spicyLevel":2,"coursePrice":277.0,"courseNumber":78,"isSellOut":0},{"shopId":1,"courseId":12,"courseName":"菜品_12","spicyLevel":0,"coursePrice":286.0,"courseNumber":49,"isSellOut":1},{"shopId":1,"courseId":14,"courseName":"菜品_14","spicyLevel":2,"coursePrice":169.0,"courseNumber":196,"isSellOut":0},{"shopId":1,"courseId":15,"courseName":"菜品_15","spicyLevel":0,"coursePrice":180.0,"courseNumber":188,"isSellOut":0},{"shopId":1,"courseId":16,"courseName":"菜品_16","spicyLevel":1,"coursePrice":273.0,"courseNumber":31,"isSellOut":1},{"shopId":1,"courseId":17,"courseName":"菜品_17","spicyLevel":2,"coursePrice":8.0,"courseNumber":131,"isSellOut":0},{"shopId":1,"courseId":2,"courseName":"菜品_2","spicyLevel":2,"coursePrice":297.0,"courseNumber":116,"isSellOut":0},{"shopId":1,"courseId":4,"courseName":"菜品_4","spicyLevel":1,"coursePrice":97.0,"courseNumber":189,"isSellOut":0},{"shopId":1,"courseId":5,"courseName":"菜品_5","spicyLevel":2,"coursePrice":228.0,"courseNumber":115,"isSellOut":0},{"shopId":1,"courseId":6,"courseName":"菜品_6","spicyLevel":0,"coursePrice":95.0,"courseNumber":100,"isSellOut":0},{"shopId":1,"courseId":7,"courseName":"菜品_7","spicyLevel":1,"coursePrice":45.0,"courseNumber":67,"isSellOut":1},{"shopId":1,"courseId":11,"courseName":"菜品_11","spicyLevel":2,"coursePrice":11.0,"courseNumber":39,"isSellOut":1},{"shopId":1,"courseId":13,"courseName":"菜品_13","spicyLevel":1,"coursePrice":62.0,"courseNumber":77,"isSellOut":0},{"shopId":1,"courseId":18,"courseName":"菜品_18","spicyLevel":0,"coursePrice":187.0,"courseNumber":100,"isSellOut":0},{"shopId":1,"courseId":20,"courseName":"菜品_20","spicyLevel":2,"coursePrice":165.0,"courseNumber":155,"isSellOut":0}],"type":"getShopProductByProductType"}
}
