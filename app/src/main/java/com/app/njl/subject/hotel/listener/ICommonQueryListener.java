package com.app.njl.subject.hotel.listener;

import com.app.njl.subject.hotel.model.entity.BaseEntity;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public interface ICommonQueryListener<T extends BaseEntity> {

    void loadSuccess(List<T> fruits);

    void loadFailed();

    void loadCompleted();

}
