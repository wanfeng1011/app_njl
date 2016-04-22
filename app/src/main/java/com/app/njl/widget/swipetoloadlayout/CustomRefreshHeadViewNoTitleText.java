package com.app.njl.widget.swipetoloadlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;

/**
 * Created by jiaxx on 2016/3/30 0030.
 */
public class CustomRefreshHeadViewNoTitleText extends SwipeRefreshHeaderLayout {

    public CustomRefreshHeadViewNoTitleText(Context context) {
        this(context, null);
    }

    public CustomRefreshHeadViewNoTitleText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshHeadViewNoTitleText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onSwipe(int y, boolean isComplete) {

    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void complete() {

    }

    @Override
    public void onReset() {

    }
}