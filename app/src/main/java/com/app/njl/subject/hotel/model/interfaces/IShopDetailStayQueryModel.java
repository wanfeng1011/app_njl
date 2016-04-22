package com.app.njl.subject.hotel.model.interfaces;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.interfaces.base.BaseModel;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public interface IShopDetailStayQueryModel extends BaseModel{
    void queryShopDetailStay(ICommonQueryListener listener);
}
