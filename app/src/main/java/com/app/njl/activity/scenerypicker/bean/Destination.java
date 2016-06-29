package com.app.njl.activity.scenerypicker.bean;

/**
 * Created by root on 2016/6/20.
 */
public class Destination {
    private int resortid;
    private String name;
    private String pinYin;
    private String briefPinYin;

    public Destination() {
    }

    public Destination(int resortid, String name, String pinYin, String briefPinYin) {
        this.resortid = resortid;
        this.name = name;
        this.pinYin = pinYin;
        this.briefPinYin = briefPinYin;
    }

    public int getResortid() {
        return resortid;
    }

    public void setResortid(int resortid) {
        this.resortid = resortid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getBriefPinYin() {
        return briefPinYin;
    }

    public void setBriefPinYin(String briefPinYin) {
        this.briefPinYin = briefPinYin;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "resortid=" + resortid +
                ", name='" + name + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", briefPinYin='" + briefPinYin + '\'' +
                '}';
    }
}
