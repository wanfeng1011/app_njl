package com.app.njl.subject.hotel.model.entity.shoplist;

import java.util.List;

/**
 * Created by root on 2016/6/25.
 */
public class ShopListBean {
    private int code;
    private ShopListMessage message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ShopListMessage getMessage() {
        return message;
    }

    public void setMessage(ShopListMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ShopListBean{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class ShopListMessage {
        private List<Shop> result;
        private int totalNumber;
        private int pageSize;
        private int pageNum;

        public List<Shop> getResult() {
            return result;
        }

        public void setResult(List<Shop> result) {
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
            return "ShopListMessage{" +
                    "result=" + result +
                    ", totalNumber=" + totalNumber +
                    ", pageSize=" + pageSize +
                    ", pageNum=" + pageNum +
                    '}';
        }
    }
}
