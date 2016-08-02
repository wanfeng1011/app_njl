package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/7/29 0029.
 */
public class ShopCourseBean {

    /**
     * code : 1
     * message : [{"shopId":8,"courseId":2,"consist":"土豆，青椒，腊肉","note":"原材料全部来自农家自己耕种","taste":2}]
     * type : getShopCourseInfo
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * courseId : 2
     * consist : 土豆，青椒，腊肉
     * note : 原材料全部来自农家自己耕种
     * taste : 2
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
        private int courseId;
        private String consist;
        private String note;
        private int taste;

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

        public String getConsist() {
            return consist;
        }

        public void setConsist(String consist) {
            this.consist = consist;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getTaste() {
            return taste;
        }

        public void setTaste(int taste) {
            this.taste = taste;
        }
    }
}
