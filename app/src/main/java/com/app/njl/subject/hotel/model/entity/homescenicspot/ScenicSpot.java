package com.app.njl.subject.hotel.model.entity.homescenicspot;

import java.util.List;

/**
 * Created by root on 2016/6/13.
 */
public class ScenicSpot {
    private String resortname;
    private int resortId;
    private List<Shop> entitys;

    public String getResortname() {
        return resortname;
    }

    public void setResortname(String resortname) {
        this.resortname = resortname;
    }

    public int getResortId() {
        return resortId;
    }

    public void setResortId(int resortId) {
        this.resortId = resortId;
    }

    public List<Shop> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<Shop> entitys) {
        this.entitys = entitys;
    }

    @Override
    public String toString() {
        return "ScenicSpot{" +
                "resortname='" + resortname + '\'' +
                ", resortId=" + resortId +
                ", entitys=" + entitys +
                '}';
    }
}
