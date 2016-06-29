package com.app.njl.subject.hotel.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.app.njl.R;
import com.app.njl.base.BaseFragment;
import com.app.njl.subject.hotel.ui.ShopPlayShowSceneryActivity;
import com.app.njl.subject.hotel.ui.ShopPlayShowSelfServiceActivity;
import com.app.njl.subject.hotel.ui.ShopPlayShowTourGuideActivity;

import butterknife.Bind;

/**
 * Created by tiansj on 15/9/4.
 */
public class ShopDetailPlayFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.scenery_ticket_ll)
    LinearLayout scenery_ticket_ll;
    @Bind(R.id.tour_guide_ll)
    LinearLayout tour_guide_ll;
    @Bind(R.id.self_service_ll)
    LinearLayout self_service_ll;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_shopdetail_play;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void setListener() {
        scenery_ticket_ll.setOnClickListener(this);
        tour_guide_ll.setOnClickListener(this);
        self_service_ll.setOnClickListener(this);
    }

    @Override
    public void initLocalData() {

    }

    @Override
    public void initRemoteData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scenery_ticket_ll:
                Intent intent_ticket = new Intent(getContext(), ShopPlayShowSceneryActivity.class);
                getContext().startActivity(intent_ticket);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.tour_guide_ll:
                Intent intent_tour_guide = new Intent(getContext(), ShopPlayShowTourGuideActivity.class);
                getContext().startActivity(intent_tour_guide);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case R.id.self_service_ll:
                Intent intent_self_service = new Intent(getContext(), ShopPlayShowSelfServiceActivity.class);
                getContext().startActivity(intent_self_service);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                break;
        }
    }
}
