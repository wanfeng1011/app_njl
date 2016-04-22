package com.app.njl.subject.hotel.listener;

import com.app.njl.base.BaseFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import space.sye.z.library.listener.OnBothRefreshListener;

/**
 * Created by jiaxx on 2016/4/12 0012.
 */
public class MyOnBothRefreshListener implements OnBothRefreshListener {
    BaseFragment mFragment;

    public MyOnBothRefreshListener(BaseFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onPullDown() {
        //模拟网络请求
//        mFragment.initRemoteData();
    }

    @Override
    public void onLoadMore() {
        //模拟网络请求
        mFragment.initRemoteData();
    }

    @Override
    public void onScrolling(int newState) {
        switch(newState) {
            case 0:
                ImageLoader.getInstance().resume();
                break;
            case 1:
                //if(this.pauseOnScroll) {
                ImageLoader.getInstance().pause();
                //}
                break;
            case 2:
                //if(this.pauseOnFling) {
                ImageLoader.getInstance().pause();
                //}
        }
    }
}
