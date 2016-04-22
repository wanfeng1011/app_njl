package com.app.njl.subject.hotel.model.impl;

import com.app.njl.subject.hotel.http.ShopListClient;
import com.app.njl.subject.hotel.http.ShopListService;
import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.interfaces.IShopListQueryModel;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.socks.library.KLog;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public class ShopListQueryModelImpl implements IShopListQueryModel {
    public static final int SORT_GRADE = 0;  // 评分由高到低（默认按评分）
    public static final int SORT_TIME = 1; // 人气由高到低（按人气）
    public static final int SORT_DISTANCE = 2; // 距离由近到远（按距离）
    public static final int SORT_COMMENT = 3;  // 价格由低到高（按价格）
    public static final int SORT_DEFAULT = 4;  // 默认排序

    ShopListClient msgClient;
    Subscription mSubscription;
    @Override
    public void loadAllShops(final ICommonQueryListener listener) {
        msgClient = ShopListService.createService(ShopListClient.class);
        mSubscription = msgClient.getAllShops("jsonArray", "1")
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
                        /*getMvpView().showError(e.getMessage(), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });*/
                        KLog.i("onError----" + e.getMessage());
                        if(e.getMessage() != null) {
                            listener.loadFailed();
                        }
                    }

                    @Override
                    public void onNext(List<Fruit> fruits) {

                        String content = fruits.get(0).getName();

                        /*if (page == 1) {
                            getMvpView().refresh(articleListBeanList);
                        } else {
                            getMvpView().loadMore(articleListBeanList);
                        }*/
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
