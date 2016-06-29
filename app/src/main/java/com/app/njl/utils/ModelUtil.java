package com.app.njl.utils;

import com.app.njl.widget.filter.model.FilterEntity;
import com.app.njl.widget.filter.model.FilterTwoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 好吧，让你找到了，这是假的数据源
 *
 * Created by sunfusheng on 16/4/22.
 */
public class ModelUtil {

    public static final String type_scenery = "风景";
    public static final String type_building = "建筑";
    public static final String type_animal = "动物";
    public static final String type_plant = "植物";

    // 广告数据
    public static List<String> getAdData() {
        List<String> adList = new ArrayList<>();
        adList.add("http://img0.imgtn.bdimg.com/it/u=1270781761,1881354959&fm=21&gp=0.jpg");
        adList.add("http://img0.imgtn.bdimg.com/it/u=2138116966,3662367390&fm=21&gp=0.jpg");
        adList.add("http://img0.imgtn.bdimg.com/it/u=1296117362,655885600&fm=21&gp=0.jpg");
        return adList;
    }

    // 分类数据
    public static List<FilterTwoEntity> getCategoryData() {
        List<FilterTwoEntity> list = new ArrayList<>();
        list.add(new FilterTwoEntity(type_scenery, getFilterData()));
        list.add(new FilterTwoEntity(type_building, getFilterData()));
        list.add(new FilterTwoEntity(type_animal, getFilterData()));
        list.add(new FilterTwoEntity(type_plant, getFilterData()));
        return list;
    }

    // 排序数据
    public static List<FilterEntity> getSortData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("排序从高到低", "1"));
        list.add(new FilterEntity("排序从低到高", "2"));
        return list;
    }

    // 筛选数据
    public static List<FilterEntity> getFilterData() {
        List<FilterEntity> list = new ArrayList<>();
        list.add(new FilterEntity("中国", "1"));
        list.add(new FilterEntity("美国", "2"));
        list.add(new FilterEntity("英国", "3"));
        list.add(new FilterEntity("德国", "4"));
        list.add(new FilterEntity("西班牙", "5"));
        list.add(new FilterEntity("意大利", "6"));
        return list;
    }

}
