package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/7/29 0029.
 */
public class ShopSpecialtyBean {
    /**
     * code : 1
     * message : [{"shopId":8,"specialtyId":2,"note":"生长环境：深山中，生长2年才能进行挖掘；"},{"shopId":8,"specialtyId":2,"note":"效用：清热解毒、丰胸养颜、益脑增发。"}]
     * type : getShopSpecialtyNote
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * specialtyId : 2
     * note : 生长环境：深山中，生长2年才能进行挖掘；
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
        private String note;

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

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
