package com.app.njl.subject.hotel.view;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public interface ShopDetailOrderView<T extends BaseEntity> {
    void loadOtherData(T t);
    void loadDaysList(List<String> lists);
}
