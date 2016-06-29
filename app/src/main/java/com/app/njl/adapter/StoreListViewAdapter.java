package com.app.njl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.app.njl.R;
import com.app.njl.utils.Utility;

/**
 * Created by Administrator on 2016/4/8.
 */
public class StoreListViewAdapter extends BaseAdapter {
    private ListView accommodationList;
    private ListView foodList;
    private ListView playList;
    private ListView specialtyList;

    private Context context;

    public StoreListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.store_item_layout,null);

        accommodationList = (ListView) view.findViewById(R.id.lvAccommodationList);
        foodList = (ListView) view.findViewById(R.id.lvFoodList);
        playList = (ListView) view.findViewById(R.id.lvPlay);
        specialtyList = (ListView) view.findViewById(R.id.lvSpecialty);

        accommodationList.setAdapter(new AccommodationListAdapter(context));
        Utility.setListViewHeightBasedOnChildren(accommodationList);

        foodList.setAdapter(new AccommodationListAdapter(context));
        Utility.setListViewHeightBasedOnChildren(foodList);

        playList.setAdapter(new AccommodationListAdapter(context));
        Utility.setListViewHeightBasedOnChildren(playList);

        specialtyList.setAdapter(new AccommodationListAdapter(context));
        Utility.setListViewHeightBasedOnChildren(specialtyList);
        return view;
    }
}