package com.app.njl.subject.hotel.presenter.interfaces;

import com.app.njl.subject.hotel.presenter.base.BasePresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.model.entity.Fruit;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public abstract class IShopListQueryPresenter extends BasePresenter<CommonView<Fruit>> {
    public abstract void searchAllShop();
}
