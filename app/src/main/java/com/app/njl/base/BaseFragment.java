package com.app.njl.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jiaxx on 2016/4/7 0007.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    private BaseActivity mActivity;
    private View mLayoutView;

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();

    /**
     * 初始化视图
     */
    public abstract void initView();

    /**
     * 初始化Presenter
     */
    public abstract void initPresenter();

    /**
     * 初始化本地数据
     */
    public abstract void initLocalData();

    /**
     * 初始化网络数据
     */
    public abstract void initRemoteData();

    /**
     * 控件添加监听
     */
    public abstract void setListener();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = getCreateView(inflater, container);
        ButterKnife.bind(this, mLayoutView);
        initView();
        initLocalData();
        initPresenter();
        initRemoteData();
        setListener();
        return mLayoutView;
    }

    /**
     * 获取Fragment布局文件的View
     *
     * @param inflater
     * @param container
     * @return
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

}
