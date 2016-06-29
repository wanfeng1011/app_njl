package com.app.njl.subject.hotel.model.entity.landmark;

import java.util.List;

/**
 * Created by root on 2016/6/26.
 */
public class SceneryLandMarkBean {
    private int code;
    private SceneryLandMarkMessage message;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SceneryLandMarkMessage getMessage() {
        return message;
    }

    public void setMessage(SceneryLandMarkMessage message) {
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
        return "SceneryLandMarkBean{" +
                "code=" + code +
                ", message=" + message +
                ", type='" + type + '\'' +
                '}';
    }

    public class SceneryLandMarkMessage {
        private List<LandMark> result;
        private int totalNumber;
        private int pageSize;
        private int pageNum;

        public List<LandMark> getResult() {
            return result;
        }

        public void setResult(List<LandMark> result) {
            this.result = result;
        }

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        @Override
        public String toString() {
            return "SceneryLandMarkMessage{" +
                    "result=" + result +
                    ", totalNumber=" + totalNumber +
                    ", pageSize=" + pageSize +
                    ", pageNum=" + pageNum +
                    '}';
        }
    }
}
