package com.app.njl.subject.mine.bean;

/**
 * Created by jiaxx on 2016/6/2 0002.
 */
public class RegisterBean {
    private int code;
    private String message;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
        return "RegisterBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
