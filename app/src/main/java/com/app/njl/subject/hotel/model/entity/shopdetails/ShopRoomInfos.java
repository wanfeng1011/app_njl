package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/7/28 0028.
 */
public class ShopRoomInfos {
    /**
     * code : 1
     * message : {"shopRoomFacilityVos":[{"shopId":8,"roomId":2,"facilityName":"wifi"},{"shopId":8,"roomId":2,"facilityName":"停车位"},{"shopId":8,"roomId":2,"facilityName":"热水"},{"shopId":8,"roomId":2,"facilityName":"吹风"},{"shopId":8,"roomId":2,"facilityName":"加床：不可加"},{"shopId":8,"roomId":2,"facilityName":"窗户：有"},{"shopId":8,"roomId":2,"facilityName":"无早"}],"shopRoomNoticeVos":[{"shopId":8,"notice":"预支付：下单后，需在线预付房费，方可生效"},{"shopId":8,"notice":"取消变更：订单支付前可随时变更；订单支付后需和店家沟通，并扣除一定手续费；"},{"shopId":8,"notice":"实名入驻：需持身份证、护照等证件入住；"}]}
     * type : getShopRoomInfo
     */

    private int code;
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
        /**
         * shopId : 8
         * roomId : 2
         * facilityName : wifi
         */

        private List<ShopRoomFacilityVosBean> shopRoomFacilityVos;
        /**
         * shopId : 8
         * notice : 预支付：下单后，需在线预付房费，方可生效
         */

        private List<ShopRoomNoticeVosBean> shopRoomNoticeVos;

        public List<ShopRoomFacilityVosBean> getShopRoomFacilityVos() {
            return shopRoomFacilityVos;
        }

        public void setShopRoomFacilityVos(List<ShopRoomFacilityVosBean> shopRoomFacilityVos) {
            this.shopRoomFacilityVos = shopRoomFacilityVos;
        }

        public List<ShopRoomNoticeVosBean> getShopRoomNoticeVos() {
            return shopRoomNoticeVos;
        }

        public void setShopRoomNoticeVos(List<ShopRoomNoticeVosBean> shopRoomNoticeVos) {
            this.shopRoomNoticeVos = shopRoomNoticeVos;
        }

        public static class ShopRoomFacilityVosBean {
            private int shopId;
            private int roomId;
            private String facilityName;

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

            public String getFacilityName() {
                return facilityName;
            }

            public void setFacilityName(String facilityName) {
                this.facilityName = facilityName;
            }
        }

        public static class ShopRoomNoticeVosBean {
            private int shopId;
            private String notice;

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
