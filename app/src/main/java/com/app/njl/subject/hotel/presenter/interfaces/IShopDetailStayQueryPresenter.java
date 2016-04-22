package com.app.njl.subject.hotel.presenter.interfaces;

import com.app.njl.subject.hotel.presenter.base.BasePresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.model.entity.Fruit;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public abstract class IShopDetailStayQueryPresenter extends BasePresenter<CommonView<Fruit>> {
    public abstract void queryShopDetailStay();
}
