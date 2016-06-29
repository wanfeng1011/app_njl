package com.app.njl.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.adapter.StoreListViewAdapter;
import com.app.njl.utils.Utility;

public class ShoppingCarActivity extends FragmentActivity {
    private ListView storeList;
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

//        setTitle("购物车");
        titleTv = (TextView)findViewById(R.id.title);
        titleTv.setText("购物车");

        storeList = (ListView) findViewById(R.id.lvStoreList);


        storeList.setAdapter(new StoreListViewAdapter(this));
    }


}
