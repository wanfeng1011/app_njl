package com.app.njl.subject.hotel.model.interfaces;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.entity.PickerListEntity;
import com.app.njl.subject.hotel.model.interfaces.base.BaseModel;

import java.util.List;

/**
 * 餐饮查询Model接口
 * Created by jiaxx on 2016/4/13 0013.
 */
public interface IShopDetailOrderQueryModel extends BaseModel {
    void queryShopDetailOrder(ICommonQueryListener listener);
    PickerListEntity getPickerListEntity();
    List<String> loadDaysList(int month);
}
