package com.app.njl.subject.hotel.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.subject.hotel.adapter.RecyclerViewAdapter;
import com.app.njl.subject.hotel.model.entity.Bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/5/5 0005.
 */
public class ShoppingCarActivity extends BaseActivity {
    @Bind(R.id.back_ll)
    LinearLayout back_ll; //back
    @Bind(R.id.compile_tv)
    TextView compile_tv;
    @Bind(R.id.bottom_commit_relative_layout)
    RelativeLayout bottom_commit_relative_layout;
    @Bind(R.id.bottom_delete_linear_layout)
    LinearLayout bottom_delete_linear_layout;
    boolean isCompile;
    @Override
    protected void initContentView() {
        setContentView(R.layout.shopping_car_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bottom_commit_relative_layout.setVisibility(View.VISIBLE);
        bottom_delete_linear_layout.setVisibility(View.GONE);
        compile_tv.setOnClickListener(this);
        back_ll.setOnClickListener(this);
    }

    @Override
    protected void initControl() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.shopping_car_recyclerView);

//		// improve performance if you know that changes in content
//		// do not change the size of the RecyclerView
//		 mRecyclerView.setHasFixedSize(true);

        //创建布局管理器
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //初始化数据
        List<Bean> myDataset = new ArrayList<Bean>();

        myDataset.add(new Bean(Bean.X_TYPE, "杭州西湖"));
        myDataset.add(new Bean(Bean.Y_TYPE, "单人间有网络、早餐、靠窗大床间"));
        myDataset.add(new Bean(Bean.Z_TYPE, "合计：120元"));
        myDataset.add(new Bean(Bean.X_TYPE, "合肥大蜀山"));
        myDataset.add(new Bean(Bean.Y_TYPE, "单人间有网络、早餐、靠窗大床间"));
        myDataset.add(new Bean(Bean.Y_TYPE, "菜系主要有：土豆、西红柿、大豆、香菇青菜等。"));
        myDataset.add(new Bean(Bean.Y_TYPE, "农家自助游，划船、赏花项目一日游。"));
        myDataset.add(new Bean(Bean.Y_TYPE, "山货、板栗、茶叶、药材等"));
        myDataset.add(new Bean(Bean.Z_TYPE, "合计：200元"));
        //创建Adapter
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                /*Intent intent = new Intent(getApplicationContext(), ShopOrderMessageActivity.class);
                ShoppingCarActivity.this.startActivity(intent);*/
            }
        });
    }

    private void changeCompile() {
        isCompile = !isCompile;
        if(isCompile) {
            compile_tv.setText("完成");
            compile_tv.setTextColor(getApplication().getResources().getColor(R.color.orange));
            bottom_commit_relative_layout.setVisibility(View.GONE);
            bottom_delete_linear_layout.setVisibility(View.VISIBLE);
        }else {
            compile_tv.setText("编辑");
            compile_tv.setTextColor(getApplication().getResources().getColor(R.color.gray_light));
            bottom_delete_linear_layout.setVisibility(View.GONE);
            bottom_commit_relative_layout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compile_tv:
                changeCompile();
                break;
            case R.id.back_ll:
                finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                break;
        }
    }

}
