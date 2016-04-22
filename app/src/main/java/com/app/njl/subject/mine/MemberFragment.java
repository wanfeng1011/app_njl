package com.app.njl.subject.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.njl.R;
import com.app.njl.activity.LoginActivity2;
import com.app.njl.activity.RegisterActivity;
import com.app.njl.subject.mine.ui.MineCommentDetailActivity;
import com.app.njl.subject.mine.ui.MineProfileActivity;
import com.app.njl.subject.mine.ui.MineWalletActivity;
import com.app.njl.ui.UIHelper;
import com.app.njl.ui.pulltozoomview.PullToZoomScrollViewEx;
import com.app.njl.widget.RoundProgressBar;
import com.app.njl.widget.wave.WaterWaveProgress;

public class MemberFragment extends Fragment implements View.OnClickListener {

    private Activity context;
    private View root;
    private PullToZoomScrollViewEx scrollView;
    private TextView tv_login;
    RoundProgressBar progressBar;
    int progress;
    private WaterWaveProgress mWaterWaveView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return root = inflater.inflate(R.layout.fragment_member, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initData();
        initView();
    }

    void initView() {
        scrollView = (PullToZoomScrollViewEx) root.findViewById(R.id.scrollView);
        View headView = LayoutInflater.from(context).inflate(R.layout.member_head_view, null, false);
        View zoomView = LayoutInflater.from(context).inflate(R.layout.member_zoom_view, null, false);
        View contentView = LayoutInflater.from(context).inflate(R.layout.mine_main_layout2, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);

        /*progressBar = (RoundProgressBar)contentView.findViewById(R.id.roundProgressBar4);
        progressBar.setProgress(60);*/

        mWaterWaveView = (WaterWaveProgress) contentView.findViewById(R.id.wave_view);
        /*mWaterWaveView.setmWaterLevel(0.8F);
        mWaterWaveView.startWave();*/
//        mWaterWaveView.setPercent(0.5f);
        mWaterWaveView.animateWave();
        mWaterWaveView.setShowProgress(false);

        headView.findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showLogin(getActivity());
            }
        });

        headView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showLogin(getActivity());
            }
        });

        contentView.findViewById(R.id.mine_main_my_wallet).setOnClickListener(this);
        contentView.findViewById(R.id.mine_main_my_profile).setOnClickListener(this);
        contentView.findViewById(R.id.mine_main_feedback).setOnClickListener(this);
        headView.findViewById(R.id.tv_login).setOnClickListener(this);
        headView.findViewById(R.id.tv_register).setOnClickListener(this);

        /*scrollView.getPullRootView().findViewById(R.id.textBalance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scrollView.getPullRootView().findViewById(R.id.textRecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scrollView.getPullRootView().findViewById(R.id.textAttention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scrollView.getPullRootView().findViewById(R.id.textHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scrollView.getPullRootView().findViewById(R.id.textCalculator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        scrollView.getPullRootView().findViewById(R.id.textSetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

//        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
//        int mScreenHeight = localDisplayMetrics.heightPixels;
//        int mScreenWidth = localDisplayMetrics.widthPixels;
//        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
//        scrollView.setHeaderLayoutParams(localObject);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_main_my_wallet:
                Intent intent_wallet = new Intent(getContext(), MineWalletActivity.class);
                getContext().startActivity(intent_wallet);
                break;
            case R.id.mine_main_my_profile:
                Intent intent_profile = new Intent(getContext(), MineProfileActivity.class);
                getContext().startActivity(intent_profile);
                break;
            case R.id.mine_main_feedback:
                Intent intent_feedback = new Intent(getContext(), MineCommentDetailActivity.class);
                getContext().startActivity(intent_feedback);
                break;
            case R.id.tv_login:
                Intent intent_login = new Intent(getContext(), LoginActivity2.class);
                getContext().startActivity(intent_login);
                break;
            case R.id.tv_register:
                Intent intent_register = new Intent(getContext(), RegisterActivity.class);
                getContext().startActivity(intent_register);
                break;
        }
    }

    @Override
    public void onDestroy() {
        /*mWaterWaveView.stopWave();
        mWaterWaveView=null;*/
        mWaterWaveView.stopWave();
        mWaterWaveView = null;
        super.onDestroy();

    }
}