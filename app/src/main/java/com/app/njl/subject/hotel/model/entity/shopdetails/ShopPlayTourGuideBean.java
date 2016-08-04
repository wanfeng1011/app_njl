package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/8/3 0003.
 */
public class ShopPlayTourGuideBean {
    /**
     * code : 1
     * message : {"shopId":1,"note":["联系店家可以车接车送","店家免费提供旅游路线指导"],"guideId":1}
     * type : getShopTravelGuideNote
     */

    private int code;
    /**
     * shopId : 1
     * note : ["联系店家可以车接车送","店家免费提供旅游路线指导"]
     * guideId : 1
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
        private int guideId;
        private List<String> note;

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

        public List<String> getNote() {
            return note;
        }

        public void setNote(List<String> note) {
            this.note = note;
        }
    }
}
