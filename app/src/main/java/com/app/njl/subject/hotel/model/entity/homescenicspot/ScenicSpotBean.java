package com.app.njl.subject.hotel.model.entity.homescenicspot;

import java.util.List;

/**
 * Created by root on 2016/6/13.
 */
public class ScenicSpotBean {
    private int code;
    private ScenicSpotMessage message;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ScenicSpotMessage getMessage() {
        return message;
    }

    public void setMessage(ScenicSpotMessage message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ScenicSpotBean{" +
                "code=" + code +
                ", message=" + message +
                ", type='" + type + '\'' +
                '}';
    }

    public class ScenicSpotMessage {
        private List<ScenicSpot> resortShops;

        public List<ScenicSpot> getResortShops() {
            return resortShops;
        }

        public void setResortShops(List<ScenicSpot> resortShops) {
            this.resortShops = resortShops;
        }

        @Override
        public String toString() {
            return "ScenicSpotMessage{" +
                    "resortShops=" + resortShops +
                    '}';
        }
    }
}
