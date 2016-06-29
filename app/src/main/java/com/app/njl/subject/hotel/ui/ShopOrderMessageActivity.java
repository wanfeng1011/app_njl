package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.hotel.model.entity.Fruit;
import com.app.njl.ui.quickadapter.BaseAdapterHelper;
import com.app.njl.ui.quickadapter.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by root on 2016/5/5.
 */
public class ShopOrderMessageActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView mListView;
    QuickAdapter<Fruit> adapter;
    @Bind(R.id.title)
    TextView mTitleTv;
    @Override
    protected void initContentView() {
        setContentView(R.layout.shop_order_message);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTitleTv.setText("订餐信息");
    }

    @Override
    protected void initControl() {
        adapter = new QuickAdapter<Fruit>(getApplicationContext(), R.layout.recommend_shop_list_item) {
            @Override
            protected void convert(BaseAdapterHelper helper, Fruit shop) {
                helper.setText(R.id.name, shop.getName())
                        .setText(R.id.favorable, shop.getContent())
                        .setText(R.id.star, shop.getStar())
                        .setText(R.id.state, "预定动态")
                        .setText(R.id.price, shop.getPrice() + "起")
                        .setImageUrl(R.id.logo, shop.getUrl()); // 自动异步加载图片
            }
        };
        mListView.setAdapter(adapter);
        List<Fruit> list = new ArrayList<>();
        Fruit fruit = new Fruit();
        fruit.setContent("土豆");
        fruit.setStar("2份");
        fruit.setPrice("20元");
        list.add(fruit);
        list.add(fruit);
        list.add(fruit);
        adapter.addAll(list);
    }

    @Override
    public void onClick(View v) {

    }
}
