package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by admin on 2016/7/31.
 */

public class ShopPlaySelfServiceBean {
    /**
     * code : 1
     * message : {"shopId":8,"activityId":2,"note":["特别是和情侣、亲子游玩","游船过程中有全程解说","游船项目用时估计2小时，从地点1出发一直游玩到地点2"]}
     * type : getShopTravelFamilyActivityNote
     */

    private int code;
    /**
     * shopId : 8
     * activityId : 2
     * note : ["特别是和情侣、亲子游玩","游船过程中有全程解说","游船项目用时估计2小时，从地点1出发一直游玩到地点2"]
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
        private int shopId;
        private int activityId;
        private List<String> note;

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

        public List<String> getNote() {
            return note;
        }

        public void setNote(List<String> note) {
            this.note = note;
        }
    }
}
