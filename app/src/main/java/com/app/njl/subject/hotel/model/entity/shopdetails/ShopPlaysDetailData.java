package com.app.njl.subject.hotel.model.entity.shopdetails;

import java.util.List;

/**
 * Created by jiaxx on 2016/7/26 0026.
 */
public class ShopPlaysDetailData {
    /**
     * code : 1
     * message : {"ticketVoList":[{"ticketVo":3,"resortId":8,"resortName":"九华山","ticketType":"老年票","isCurrentDayValid":1,"isTotalTicket":1,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_ticket_item_3.jpg","maxBookNumber":3},{"ticketVo":1,"resortId":8,"resortName":"九华山","ticketType":"成人票","isCurrentDayValid":2,"isTotalTicket":2,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_ticket_item_1.jpg","maxBookNumber":26},{"ticketVo":2,"resortId":8,"resortName":"九华山","ticketType":"儿童片","isCurrentDayValid":2,"isTotalTicket":2,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_ticket_item_2.jpg","maxBookNumber":25}],"familyActivityVoList":[{"familyActiveId":2,"familyActivityPrice":257,"familyActivityName":"赏花","familyActivityComment":"好玩的自家组织的游玩项目","tourTimeElapse":0,"personNumMin":2,"personNumMax":4,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_familyactivity_item_2.jpg","maxBookNumber":4},{"familyActiveId":1,"familyActivityPrice":9,"familyActivityName":"游船","familyActivityComment":"好玩的自家组织的游玩项目","tourTimeElapse":0,"personNumMin":2,"personNumMax":4,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_familyactivity_item_1.jpg","maxBookNumber":17},{"familyActiveId":3,"familyActivityPrice":165,"familyActivityName":"漂流","familyActivityComment":"好玩的自家组织的游玩项目","tourTimeElapse":2,"personNumMin":0,"personNumMax":2,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_familyactivity_item_3.jpg","maxBookNumber":4}],"guideVoList":[{"guideId":1,"resortId":8,"describe":"到本店消费，免费提供导游服务","comments":"好的导游","carService":1,"tourGuideService":1,"shopId":8,"picUrl":"http://www.whateverblake.com/shop_travel_guide_item_1.jpg","tourGuardPrice":187,"guideNumber":1,"allowBookNumber":-3}]}
     * type : getShopTravelInfo
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
         * ticketVo : 3
         * resortId : 8
         * resortName : 九华山
         * ticketType : 老年票
         * isCurrentDayValid : 1
         * isTotalTicket : 1
         * shopId : 8
         * picUrl : http://www.whateverblake.com/shop_travel_ticket_item_3.jpg
         * maxBookNumber : 3
         */

        private List<TicketVoListBean> ticketVoList;
        /**
         * familyActiveId : 2
         * familyActivityPrice : 257
         * familyActivityName : 赏花
         * familyActivityComment : 好玩的自家组织的游玩项目
         * tourTimeElapse : 0
         * personNumMin : 2
         * personNumMax : 4
         * shopId : 8
         * picUrl : http://www.whateverblake.com/shop_travel_familyactivity_item_2.jpg
         * maxBookNumber : 4
         */

        private List<FamilyActivityVoListBean> familyActivityVoList;
        /**
         * guideId : 1
         * resortId : 8
         * describe : 到本店消费，免费提供导游服务
         * comments : 好的导游
         * carService : 1
         * tourGuideService : 1
         * shopId : 8
         * picUrl : http://www.whateverblake.com/shop_travel_guide_item_1.jpg
         * tourGuardPrice : 187
         * guideNumber : 1
         * allowBookNumber : -3
         */

        private List<GuideVoListBean> guideVoList;

        public List<TicketVoListBean> getTicketVoList() {
            return ticketVoList;
        }

        public void setTicketVoList(List<TicketVoListBean> ticketVoList) {
            this.ticketVoList = ticketVoList;
        }

        public List<FamilyActivityVoListBean> getFamilyActivityVoList() {
            return familyActivityVoList;
        }

        public void setFamilyActivityVoList(List<FamilyActivityVoListBean> familyActivityVoList) {
            this.familyActivityVoList = familyActivityVoList;
        }

        public List<GuideVoListBean> getGuideVoList() {
            return guideVoList;
        }

        public void setGuideVoList(List<GuideVoListBean> guideVoList) {
            this.guideVoList = guideVoList;
        }

        public static class TicketVoListBean {
            private int ticketVo;
            private int resortId;
            private String resortName;
            private String ticketType;
            private int isCurrentDayValid;
            private int isTotalTicket;
            private int shopId;
            private String picUrl;
            private int maxBookNumber;

            public int getTicketVo() {
                return ticketVo;
            }

            public void setTicketVo(int ticketVo) {
                this.ticketVo = ticketVo;
            }

            public int getResortId() {
                return resortId;
            }

            public void setResortId(int resortId) {
                this.resortId = resortId;
            }

            public String getResortName() {
                return resortName;
            }

            public void setResortName(String resortName) {
                this.resortName = resortName;
            }

            public String getTicketType() {
                return ticketType;
            }

            public void setTicketType(String ticketType) {
                this.ticketType = ticketType;
            }

            public int getIsCurrentDayValid() {
                return isCurrentDayValid;
            }

            public void setIsCurrentDayValid(int isCurrentDayValid) {
                this.isCurrentDayValid = isCurrentDayValid;
            }

            public int getIsTotalTicket() {
                return isTotalTicket;
            }

            public void setIsTotalTicket(int isTotalTicket) {
                this.isTotalTicket = isTotalTicket;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getMaxBookNumber() {
                return maxBookNumber;
            }

            public void setMaxBookNumber(int maxBookNumber) {
                this.maxBookNumber = maxBookNumber;
            }
        }

        public static class FamilyActivityVoListBean {
            private int familyActiveId;
            private double familyActivityPrice;
            private String familyActivityName;
            private String familyActivityComment;
            private int tourTimeElapse;
            private int personNumMin;
            private int personNumMax;
            private int shopId;
            private String picUrl;
            private int maxBookNumber;

            public int getFamilyActiveId() {
                return familyActiveId;
            }

            public void setFamilyActiveId(int familyActiveId) {
                this.familyActiveId = familyActiveId;
            }

            public double getFamilyActivityPrice() {
                return familyActivityPrice;
            }

            public void setFamilyActivityPrice(double familyActivityPrice) {
                this.familyActivityPrice = familyActivityPrice;
            }

            public String getFamilyActivityName() {
                return familyActivityName;
            }

            public void setFamilyActivityName(String familyActivityName) {
                this.familyActivityName = familyActivityName;
            }

            public String getFamilyActivityComment() {
                return familyActivityComment;
            }

            public void setFamilyActivityComment(String familyActivityComment) {
                this.familyActivityComment = familyActivityComment;
            }

            public int getTourTimeElapse() {
                return tourTimeElapse;
            }

            public void setTourTimeElapse(int tourTimeElapse) {
                this.tourTimeElapse = tourTimeElapse;
            }

            public int getPersonNumMin() {
                return personNumMin;
            }

            public void setPersonNumMin(int personNumMin) {
                this.personNumMin = personNumMin;
            }

            public int getPersonNumMax() {
                return personNumMax;
            }

            public void setPersonNumMax(int personNumMax) {
                this.personNumMax = personNumMax;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getMaxBookNumber() {
                return maxBookNumber;
            }

            public void setMaxBookNumber(int maxBookNumber) {
                this.maxBookNumber = maxBookNumber;
            }
        }

        public static class GuideVoListBean {
            private int guideId;
            private int resortId;
            private String describe;
            private String comments;
            private int carService;
            private int tourGuideService;
            private int shopId;
            private String picUrl;
            private double tourGuardPrice;
            private int guideNumber;
            private int allowBookNumber;

            public int getGuideId() {
                return guideId;
            }

            public void setGuideId(int guideId) {
                this.guideId = guideId;
            }

            public int getResortId() {
                return resortId;
            }

            public void setResortId(int resortId) {
                this.resortId = resortId;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public int getCarService() {
                return carService;
            }

            public void setCarService(int carService) {
                this.carService = carService;
            }

            public int getTourGuideService() {
                return tourGuideService;
            }

            public void setTourGuideService(int tourGuideService) {
                this.tourGuideService = tourGuideService;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public double getTourGuardPrice() {
                return tourGuardPrice;
            }

            public void setTourGuardPrice(double tourGuardPrice) {
                this.tourGuardPrice = tourGuardPrice;
            }

            public int getGuideNumber() {
                return guideNumber;
            }

            public void setGuideNumber(int guideNumber) {
                this.guideNumber = guideNumber;
            }

            public int getAllowBookNumber() {
                return allowBookNumber;
            }

            public void setAllowBookNumber(int allowBookNumber) {
                this.allowBookNumber = allowBookNumber;
            }
        }
    }
}
