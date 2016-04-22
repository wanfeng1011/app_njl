package com.app.njl.subject.hotel.model.entity;

import java.util.List;

/**
 * Created by jiaxx on 2016/4/13 0013.
 */
public class PickerListEntity extends BaseEntity{
    private List<String> monthsList;
    private List<String> daysList;
    private List<String> ordersList;

    public List<String> getMonthsList() {
        return monthsList;
    }

    public void setMonthsList(List<String> monthsList) {
        this.monthsList = monthsList;
    }

    public List<String> getDaysList() {
        return daysList;
    }

    public void setDaysList(List<String> daysList) {
        this.daysList = daysList;
    }

    public List<String> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<String> ordersList) {
        this.ordersList = ordersList;
    }
}
