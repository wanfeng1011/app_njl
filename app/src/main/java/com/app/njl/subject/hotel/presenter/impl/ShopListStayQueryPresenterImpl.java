package com.app.njl.subject.hotel.presenter.impl;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.impl.ShopStayListQueryModelImpl;
import com.app.njl.subject.hotel.model.interfaces.IShopStayListQueryModel;
import com.app.njl.subject.hotel.presenter.interfaces.IShopListStayQueryPresenter;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.model.entity.Fruit;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public class ShopListStayQueryPresenterImpl extends IShopListStayQueryPresenter {
    IShopStayListQueryModel shopStayListQueryModel;
    @Override
    public void attachView(CommonView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(shopStayListQueryModel != null) shopStayListQueryModel.detachSubscription();

    }

    @Override
    public boolean isEmpty(String verifData, CommonView view, String showMessage) {
        return super.isEmpty(verifData, view, showMessage);
    }

    @Override
    public void queryShopListStay() {
        shopStayListQueryModel = new ShopStayListQueryModelImpl();
        shopStayListQueryModel.queryShopStayList(new ICommonQueryListener<Fruit>() {
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
