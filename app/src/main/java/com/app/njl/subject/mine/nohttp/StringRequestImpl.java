package com.app.njl.subject.mine.nohttp;

import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.StringRequest;

/**
 * Created by jiaxx on 2016/6/1 0001.
 */
public class StringRequestImpl extends StringRequest {

    public StringRequestImpl(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override
    public String getAccept() {
        return "application/json,application/xml,text/html,application/xhtml+xml";
    }
}
