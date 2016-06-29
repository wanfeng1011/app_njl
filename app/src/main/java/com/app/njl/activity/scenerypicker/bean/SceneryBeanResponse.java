package com.app.njl.activity.scenerypicker.bean;

import java.util.List;

/**
 * Created by root on 2016/6/20.
 */
public class SceneryBeanResponse {
    private int code;
    private SceneryListMessage message;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SceneryListMessage getMessage() {
        return message;
    }

    public void setMessage(SceneryListMessage message) {
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
        return "SceneryBeanResponse{" +
                "code=" + code +
                ", message=" + message +
                ", type='" + type + '\'' +
                '}';
    }


}
