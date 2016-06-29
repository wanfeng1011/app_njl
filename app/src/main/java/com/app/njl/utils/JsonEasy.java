package com.app.njl.utils;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import android.text.TextUtils;

/**
 * JSON工具类
 * @author holmes
 *
 */
public final class JsonEasy {

    /**
     * 解析json,  并获取建为key的json元素的字符串
     * @param json
     * @param key
     * @return
     */
    public static String getString(String json, String key){
        if (!TextUtils.isEmpty(json) && !TextUtils.isEmpty(key)){
            try {
                JSONObject obj = JSON.parseObject(json);
                if (obj != null){
                    String result = obj.getString(key);
                    return result;
                }
            } catch (Exception e) {
            	e.printStackTrace();
            	 return null;
            }
        }
        return null;
    }
    
    /**
     * json 解析为一个对象
     * @param json
     * @param clss  对象的Class
     * @return
     */
    public static <T> T toObject(String json, Class<T> clss){
        if (!TextUtils.isEmpty(json)){
            try{
                T obj = JSON.parseObject(json, clss);
                return obj;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * json 解析为一个对象列表
     * @param json
     * @param clss  对象的Class
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> clss){
        if (!TextUtils.isEmpty(json)){
            try {
                List<T> objList = JSON.parseArray(json, clss);
                return objList;
            } catch (Exception e) {
                // It is Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
