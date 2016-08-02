package com.app.njl.subject.hotel.presenter.impl;

import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.entity.PickerListEntity;
import com.app.njl.subject.hotel.model.impl.ShopDetailOrderQueryModelImpl;
import com.app.njl.subject.hotel.model.interfaces.IShopDetailOrderQueryModel;
import com.app.njl.subject.hotel.presenter.interfaces.IShopDetailOrderPresenter;
import com.app.njl.subject.hotel.ui.fragment.ShopDetailOrderFragment;
import com.app.njl.subject.hotel.view.CommonView;
import com.app.njl.subject.hotel.model.entity.Fruit;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public class ShopDetailOrderQueryPresenterImpl extends IShopDetailOrderPresenter {
    private IShopDetailOrderQueryModel shopDetailOrderQueryModel;
    @Override
    public void attachView(CommonView<Fruit> mvpView) {
        super.attachView(mvpView);
        shopDetailOrderQueryModel = new ShopDetailOrderQueryModelImpl();
    }

    @Override
    public void detachView() {
        super.detachView();
        if(shopDetailOrderQueryModel != null) shopDetailOrderQueryModel.detachSubscription();
    }

    @Override
    public boolean isEmpty(String verifData, CommonView<Fruit> view, String showMessage) {
        return super.isEmpty(verifData, view, showMessage);
    }

    @Override
    public void queryShopDetailOrder() {
        shopDetailOrderQueryModel.queryShopDetailOrder(new ICommonQueryListener<Fruit>() {
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

    @Override
    public void getPickListEntity() {
        PickerListEntity entity = shopDetailOrderQueryModel.getPickerListEntity();
//        ((ShopDetailOrderFragment)getMvpView()).loadOtherData(entity);
    }

    @Override
    public void loadDaysList(int month) {
        List<String> lists = shopDetailOrderQueryModel.loadDaysList(month);
//        ((ShopDetailOrderFragment)getMvpView()).loadDaysList(lists);
    }
}
