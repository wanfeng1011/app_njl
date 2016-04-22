package com.app.njl.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.app.njl.R;
import com.app.njl.common.AppManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * 基类Activity，所有activity都需要继承这个基类。
 * Created by jiaxx on 2016/4/7 0007.
 */
public abstract class BaseActivity extends FragmentActivity{
    FragmentManager fragmentManager;

    /**
     * 初始化布局
     */
    protected abstract void initContentView();

    /**
     * 初始化数据
     */
    //protected abstract void initData();

    /**
     * 初始化控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化控制中心
     */
    protected abstract void initControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

        // 修改状态栏颜色，4.4+生效
        setStatusBarStyle(R.color.status_bar_bg);
        initContentView();
        //初始化view注入
        ButterKnife.bind(this);
        initControl();
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 浸染状态栏底色背景，若使用布局的背景图浸染，设置resId为透明色即可
     * 其它情况设置resId为ToolBar(ActionBar)的背景色
     * @param resId 颜色id
     */
    public void setStatusBarStyle(int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(resId);//通知栏所需颜色
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    //--------------------------Fragment相关--------------------------//
    /**
     * 获取Fragment管理器
     *
     * @return FragmentManager
     */
    public FragmentManager getBaseFragmentManager() {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        return fragmentManager;
    }

    /**
     * 获取Fragment事物管理
     *
     * @return FragmentTransaction
     */
    public FragmentTransaction getBaseFragmentTransaction() {
        return getBaseFragmentManager().beginTransaction();
    }

    /**
     * 替换一个Fragment
     *
     * @param res 资源
     * @param fragment 具体fragment
     */
    public void replaceFragment(int res, BaseFragment fragment) {
        replaceFragment(res, fragment, false);
    }

    /**
     * 替换一个Fragment并设置是否加入回退栈
     *
     * @param res 资源
     * @param fragment 具体fragment
     * @param isAddToBackStack 加入回退栈标识
     */
    public void replaceFragment(int res, BaseFragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getBaseFragmentTransaction();
        fragmentTransaction.replace(res, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }

    /**
     * 添加一个Fragment
     *
     * @param res
     * @param fragment
     */
    public void addFragment(int res, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getBaseFragmentTransaction();
        fragmentTransaction.add(res, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 移除一个Fragment
     *
     * @param fragment
     */
    public void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getBaseFragmentTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    /**
     * 显示一个Fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        if (fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getBaseFragmentTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * 隐藏一个Fragment
     *
     * @param fragment
     */
    public void hideFragment(Fragment fragment) {
        if (null != fragment && !fragment.isHidden() && fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getBaseFragmentTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }

    //--------------------------Fragment相关end--------------------------//
}
