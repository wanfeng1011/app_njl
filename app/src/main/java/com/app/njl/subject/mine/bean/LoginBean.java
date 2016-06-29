package com.app.njl.subject.mine.bean;

/**
 * Created by jiaxx on 2016/6/2 0002.
 */
public class LoginBean {
    private int code;
    private String message;
    private String type;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
