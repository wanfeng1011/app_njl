package com.app.njl.subject.hotel.presenter.impl;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.impl.ShopDetailStayQueryModelImpl;
import com.app.njl.subject.hotel.model.interfaces.IShopDetailStayQueryModel;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailStayQueryPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.model.entity.Fruit;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public class ShopDetailStayQueryPresenterImpl extends IShopDetailStayQueryPresenter {
    IShopDetailStayQueryModel shopDetailStayQueryModel;
    @Override
    public void attachView(CommonView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(shopDetailStayQueryModel != null) shopDetailStayQueryModel.detachSubscription();
    }

    @Override
    public boolean isEmpty(String verifData, CommonView view, String showMessage) {
        return super.isEmpty(verifData, view, showMessage);
    }

    @Override
    public void queryShopDetailStay() {
        shopDetailStayQueryModel = new ShopDetailStayQueryModelImpl();
        shopDetailStayQueryModel.queryShopDetailStay(new ICommonQueryListener<Fruit>() {
            @Override
            public void loadSuccess(List<Fruit> fruits) {
                getMvpView().loadSuccess(fruits);
            }

            @Override
            public void loadFailed() {
                getMvpView().loadFailed();
            }

            @Override
            public void loadCompleted() {
                getMvpView().loadCompleted();
            }

        });
    }
}
