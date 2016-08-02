package com.app.njl.subject.hotel.model.entity.shopdetails;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/7/17.
 */
public class ShopOrdersDetailData {
    /**
     * code : 1
     * message : [{"shopId":1,"courseId":1,"courseName":"菜品_1","spicyLevel":1,"coursePrice":204,"courseNumber":52,"isSellOut":1},{"shopId":1,"courseId":3,"courseName":"菜品_3","spicyLevel":0,"coursePrice":122,"courseNumber":72,"isSellOut":0},{"shopId":1,"courseId":9,"courseName":"菜品_9","spicyLevel":0,"coursePrice":228,"courseNumber":65,"isSellOut":0},{"shopId":1,"courseId":10,"courseName":"菜品_10","spicyLevel":1,"coursePrice":228,"courseNumber":153,"isSellOut":0},{"shopId":1,"courseId":19,"courseName":"菜品_19","spicyLevel":1,"coursePrice":182,"courseNumber":136,"isSellOut":0},{"shopId":1,"courseId":8,"courseName":"菜品_8","spicyLevel":2,"coursePrice":277,"courseNumber":78,"isSellOut":0},{"shopId":1,"courseId":12,"courseName":"菜品_12","spicyLevel":0,"coursePrice":286,"courseNumber":49,"isSellOut":1},{"shopId":1,"courseId":14,"courseName":"菜品_14","spicyLevel":2,"coursePrice":169,"courseNumber":196,"isSellOut":0},{"shopId":1,"courseId":15,"courseName":"菜品_15","spicyLevel":0,"coursePrice":180,"courseNumber":188,"isSellOut":0},{"shopId":1,"courseId":16,"courseName":"菜品_16","spicyLevel":1,"coursePrice":273,"courseNumber":31,"isSellOut":1},{"shopId":1,"courseId":17,"courseName":"菜品_17","spicyLevel":2,"coursePrice":8,"courseNumber":131,"isSellOut":0},{"shopId":1,"courseId":2,"courseName":"菜品_2","spicyLevel":2,"coursePrice":297,"courseNumber":116,"isSellOut":0},{"shopId":1,"courseId":4,"courseName":"菜品_4","spicyLevel":1,"coursePrice":97,"courseNumber":189,"isSellOut":0},{"shopId":1,"courseId":5,"courseName":"菜品_5","spicyLevel":2,"coursePrice":228,"courseNumber":115,"isSellOut":0},{"shopId":1,"courseId":6,"courseName":"菜品_6","spicyLevel":0,"coursePrice":95,"courseNumber":100,"isSellOut":0},{"shopId":1,"courseId":7,"courseName":"菜品_7","spicyLevel":1,"coursePrice":45,"courseNumber":67,"isSellOut":1},{"shopId":1,"courseId":11,"courseName":"菜品_11","spicyLevel":2,"coursePrice":11,"courseNumber":39,"isSellOut":1},{"shopId":1,"courseId":13,"courseName":"菜品_13","spicyLevel":1,"coursePrice":62,"courseNumber":77,"isSellOut":0},{"shopId":1,"courseId":18,"courseName":"菜品_18","spicyLevel":0,"coursePrice":187,"courseNumber":100,"isSellOut":0},{"shopId":1,"courseId":20,"courseName":"菜品_20","spicyLevel":2,"coursePrice":165,"courseNumber":155,"isSellOut":0}]
     * type : getShopProductByProductType
     */

    private int code;
    private String type;
    /**
     * shopId : 1
     * courseId : 1
     * courseName : 菜品_1
     * spicyLevel : 1
     * coursePrice : 204
     * courseNumber : 52
     * isSellOut : 1
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
        private int courseId;
        private String courseName;
        private int spicyLevel;
        private double coursePrice;
        private int courseNumber;
        private int isSellOut;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getSpicyLevel() {
            return spicyLevel;
        }

        public void setSpicyLevel(int spicyLevel) {
            this.spicyLevel = spicyLevel;
        }

        public double getCoursePrice() {
            return coursePrice;
        }

        public void setCoursePrice(double coursePrice) {
            this.coursePrice = coursePrice;
        }

        public int getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
        }

        public int getIsSellOut() {
            return isSellOut;
        }

        public void setIsSellOut(int isSellOut) {
            this.isSellOut = isSellOut;
        }
    }
}
