package com.app.njl.subject.hotel.model.impl;

import com.app.njl.subject.hotel.http.ShopListClient;
import com.app.njl.subject.hotel.http.ShopListService;
import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.interfaces.IShopDetailStayQueryModel;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.socks.library.KLog;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public class ShopDetailStayQueryModelImpl implements IShopDetailStayQueryModel {
    ShopListClient msgClient;
    Subscription mSubscription;
    @Override
    public void queryShopDetailStay(final ICommonQueryListener listener) {
        msgClient = ShopListService.createService(ShopListClient.class);
        mSubscription = msgClient.queryShopDetailStay("jsonArray", "1")
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<Fruit>, List<Fruit>>() {
                    @Override
                    public List<Fruit> call(List<Fruit> fruits) {
                        return fruits;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Fruit>>() {
                    @Override
                    public void onCompleted() {
                        KLog.i("onCompleted----");
                        listener.loadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.i("onError----" + e.getMessage());
                        if(e.getMessage() != null) {
                            listener.loadFailed();
                        }
                    }

                    @Override
                    public void onNext(List<Fruit> fruits) {

                        String content = fruits.get(0).getName();
                        KLog.i("onNext----" + content);
                        listener.loadSuccess(fruits);
                    }
                });
    }

    @Override
    public void detachSubscription() {
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
