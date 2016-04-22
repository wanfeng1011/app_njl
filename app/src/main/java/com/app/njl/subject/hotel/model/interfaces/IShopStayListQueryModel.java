package com.app.njl.subject.hotel.model.interfaces;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.interfaces.base.BaseModel;
import com.app.njl.subject.hotel.model.entity.Fruit;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public interface IShopStayListQueryModel extends BaseModel {
    void queryShopStayList(ICommonQueryListener<Fruit> listener);
}
