package com.app.njl.subject.hotel.model.entity.shoplist;

import java.util.List;

/**
 * Created by jiaxx on 2016/8/2 0002.
 */
public class ShopPicsBean {
    /**
     * code : 1
     * message : [{"shopId":8,"picType":1,"picName":"农家菜品图片_5","picUrl":"http://www.whateverblake.com/shop_live_logo_5.jpg","picDescribe":"舒适快乐的农家住房","sort":5},{"shopId":8,"picType":1,"picName":"农家菜品图片_4","picUrl":"http://www.whateverblake.com/shop_live_logo_4.jpg","picDescribe":"舒适快乐的农家住房","sort":4},{"shopId":8,"picType":1,"picName":"农家菜品图片_3","picUrl":"http://www.whateverblake.com/shop_live_logo_3.jpg","picDescribe":"舒适快乐的农家住房","sort":3},{"shopId":8,"picType":1,"picName":"农家菜品图片_2","picUrl":"http://www.whateverblake.com/shop_live_logo_2.jpg","picDescribe":"舒适快乐的农家住房","sort":2},{"shopId":8,"picType":1,"picName":"农家菜品图片_1","picUrl":"http://www.whateverblake.com/shop_live_logo_1.jpg","picDescribe":"舒适快乐的农家住房","sort":1}]
     * type : getShopHotelListPaging
     */

    private int code;
    private String type;
    /**
     * shopId : 8
     * picType : 1
     * picName : 农家菜品图片_5
     * picUrl : http://www.whateverblake.com/shop_live_logo_5.jpg
     * picDescribe : 舒适快乐的农家住房
     * sort : 5
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
        private int picType;
        private String picName;
        private String picUrl;
        private String picDescribe;
        private int sort;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getPicType() {
            return picType;
        }

        public void setPicType(int picType) {
            this.picType = picType;
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

        public String getPicDescribe() {
            return picDescribe;
        }

        public void setPicDescribe(String picDescribe) {
            this.picDescribe = picDescribe;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
