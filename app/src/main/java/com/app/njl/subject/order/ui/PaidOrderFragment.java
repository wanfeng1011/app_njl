package com.app.njl.subject.order.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.njl.R;
import com.app.njl.activity.MainActivity;
import com.app.njl.base.BaseFragment;

import butterknife.Bind;

/**
 * 已支付Fragment
 * Created by jiaxx on 2016/4/13 0013.
 */
public class PaidOrderFragment extends BaseFragment {
    @Bind(R.id.time_rl)
    RelativeLayout time_rl;

    @Override
    public int getLayoutRes() {
        return R.layout.order_paid_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initLocalData() {

    }

    @Override
    public void initRemoteData() {

    }

    @Override
    public void setListener() {
        time_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment();
                /*Intent intent = new Intent(getContext(), ListViewActivity.class);
                getContext().startActivity(intent);*/
            }
        });
    }

    /**
     * 跳转到fragment界面
     */
    private void replaceFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new OrderDetailFragment();
        for (int j = 0; j < MainActivity.fragmentTags.size(); j++) {
            Fragment f = fragmentManager.findFragmentByTag(MainActivity.fragmentTags.get(j));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(PaidOrderFragment.this);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, "OrderDetailFragment");
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }
}
