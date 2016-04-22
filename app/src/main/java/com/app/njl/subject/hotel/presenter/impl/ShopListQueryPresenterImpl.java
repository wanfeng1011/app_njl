package com.app.njl.subject.hotel.presenter.impl;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.impl.ShopListQueryModelImpl;
import com.app.njl.subject.hotel.model.interfaces.IShopListQueryModel;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListQueryPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.socks.library.KLog;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public class ShopListQueryPresenterImpl extends IShopListQueryPresenter {
    IShopListQueryModel loadAllShopModel;
    CustomCommonQueryListener customLoadAllShopListener;

    @Override
    public void attachView(CommonView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(loadAllShopModel != null) loadAllShopModel.detachSubscription();
    }
    @Override
    public void searchAllShop() {
        customLoadAllShopListener = new CustomCommonQueryListener();
        loadAllShopModel = new ShopListQueryModelImpl();
        loadAllShopModel.loadAllShops(customLoadAllShopListener);
    }

    class CustomCommonQueryListener implements ICommonQueryListener<Fruit> {

        @Override
        public void loadSuccess(List<Fruit> fruits) {
            KLog.i("loadSuccess-------");
            getMvpView().loadSuccess(fruits);
        }

        @Override
        public void loadFailed() {
            KLog.i("loadFailed-------");
            getMvpView().loadFailed();
        }

        @Override
        public void loadCompleted() {
            getMvpView().loadCompleted();
        }

    }
}
