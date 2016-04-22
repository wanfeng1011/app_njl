package com.app.njl.subject.hotel.model.impl;

import android.util.Log;

import com.app.njl.subject.hotel.http.ShopListClient;
import com.app.njl.subject.hotel.http.ShopListService;
import com.app.njl.subject.hotel.listener.ICommonQueryListener;
import com.app.njl.subject.hotel.model.entity.PickerListEntity;
import com.app.njl.subject.hotel.model.interfaces.IShopDetailOrderQueryModel;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.utils.Utils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public class ShopDetailOrderQueryModelImpl implements IShopDetailOrderQueryModel {
    ShopListClient msgClient;
    Subscription mSubscription;

    private List<String> monthList;
    private List<String> dayList;
    private List<String> orderList;
    int day;

    /**
     * 服务器查询商家餐饮详情
     * @param listener
     */
    @Override
    public void queryShopDetailOrder(final ICommonQueryListener listener) {
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

    /**
     * 获取滚动控件的数据集合
     * @return
     */
    @Override
    public PickerListEntity getPickerListEntity() {
        PickerListEntity entity = new PickerListEntity();
        monthList = new ArrayList<>();
        dayList = new ArrayList<>();
        orderList = new ArrayList<>();
        initMonthList();
        initDaysList(Utils.getMonth());
        initOrderList();
        entity.setMonthsList(monthList);
        entity.setDaysList(dayList);
        entity.setOrdersList(orderList);
        return entity;
    }

    /**
     * 根据月份实时加载天数，返回对应的数据集合
     * @param month
     * @return
     */
    @Override
    public List<String> loadDaysList(int month) {
        initDaysList(month);
        return dayList;
    }

    /**
     * 初始化月份数组
     */
    private void initMonthList() {
        monthList.clear();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i < 10 ? "0" + i : "" + i);
        }
    }

    /**
     * 根据月份初始化日数组
     * @param month
     */
    private void initDaysList(int month) {
        calDays(0, month);
        dayList.clear();
        for (int i = 1; i <= day; i++) {
            dayList.add(i < 10 ? "0" + i : "" + i);
        }
    }

    /**
     * 初始化餐次数组
     */
    private void initOrderList() {
        orderList.clear();
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                orderList.add("中");
            if (i == 1) {
                orderList.add("晚");
            }
        }
    }

    /**
     * 计算每月多少天
     *
     * @param month
     * @param year
     */
    public void calDays(int year, int month) {
        year = Utils.getYear();
        Log.i("calDays", "days:" + month);
        boolean leayyear = false;
        if (year % 4 == 0 && year % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    this.day = 31;
                    break;
                case 2:
                    if (leayyear) {
                        this.day = 29;
                    } else {
                        this.day = 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    this.day = 30;
                    break;
            }
        }
    }

    @Override
    public void detachSubscription() {
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
