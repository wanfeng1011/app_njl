package com.app.njl.subject.mine.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.widget.wheel.view.PickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jiaxx on 2016/4/16 0016.
 */
public class MineCommentDetailActivity extends BaseActivity {
    @Bind(R.id.comment_detail_look_question_record)
    Button mLookRecordBtn;
    @Bind(R.id.comment_detail_dialog)
    LinearLayout mComment_detail_dialog;
    @Bind(R.id.comment_detail_pickerView)
    PickerView mPickView;

    @Bind(R.id.comment_detail_dialog_dismiss)
    Button mDismissBtn;
    @Bind(R.id.comment_detail_dialog_sure)
    Button mSureBtn;

    private List<String> mList;
//    private boolean isLookRecord = false;
    @Override
    protected void initContentView() {
        setContentView(R.layout.mine_comment_detail_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mList.add("住宿");
        mList.add("餐饮");
        mList.add("游玩");
        mList.add("特产");

        mLookRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickView.setData(mList);
                mPickView.setSelected(2);
                mComment_detail_dialog.setVisibility(View.VISIBLE);
            }
        });

        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mComment_detail_dialog.setVisibility(View.GONE);
            }
        });

        mDismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mComment_detail_dialog.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initControl() {

    }

    @Override
    public void onClick(View v) {

    }
}
