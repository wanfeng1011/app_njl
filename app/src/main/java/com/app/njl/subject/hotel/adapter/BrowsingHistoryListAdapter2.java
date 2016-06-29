package com.app.njl.subject.hotel.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.njl.R;
import com.app.njl.ui.loopviewpager.AutoLoopViewPager;
import com.app.njl.ui.viewpagerindicator.CirclePageIndicator;

/**
 * Created by jiaxx on 2016/3/23 0023.
 */
public class BrowsingHistoryListAdapter2 extends BaseAdapter {
    private Context mContext;
    private int page;
    //private List<String> mImageUrls;
    private int lastPosition = -1;
    private GalleryPagerAdapter galleryAdapter;
    private int[] imageViewIds;

    public BrowsingHistoryListAdapter2(Context context) {
//            mImageUrls = ImageUrl.imageList();
        mContext = context;
        imageViewIds = new int[] { R.mipmap.tp_19_1, R.mipmap.tp_19_2, R.mipmap.tp_19_3, R.mipmap.tp_19_4};
        //mImageUrls = new ArrayList<String>();
        /*List<String> lists = new ArrayList<String>();
        int num = 4;
        String url = "http://192.168.1.116:8080/HttpServer/image";
        for (int i = 0; i < num; i++) {
            lists.add(url + "?name=19/tp_19_" + i);
            Log.i("img", "img:" + url + "?name=19/tp_" + i);
        }*/
        //mImageUrls.addAll(lists);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return imageViewIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.viewpager_layout, parent, false);
            holder = new ViewHolder();
            holder.pager = (AutoLoopViewPager) convertView.findViewById(R.id.pager);
            holder.indicator = (CirclePageIndicator) convertView.findViewById(R.id.indicator);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        galleryAdapter = new GalleryPagerAdapter();
        holder.pager.setAdapter(galleryAdapter);
        holder.indicator.setViewPager(holder.pager);
        holder.indicator.setPadding(5, 5, 10, 5);




        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.viewpager_layout, null,false);

        LinearLayout mainLinnerLayout=(LinearLayout)convertView.findViewById(R.id.mainLinear);

        for (int i = 0; i <5; i++) {
            View additionView = inflater.inflate(R.layout.inner_layout_file, null,false);
            LinearLayout innerLinnerLayout=(LinearLayout)additionView.findViewById(R.id.inner_layout);

            // If the width varies for each innerLinnerLayout, then remove the if block & always calculate padding value
            // padding is an integer initialized to -1 in the constructor
            int padding = -1;
            if (padding == -1) {
                int width = mContext.getResources().getDisplayMetrics().widthPixels;
                innerLinnerLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                padding = width - additionView.getMeasuredWidth();
            }
            // I've set padding to right only, but you could center it by giving left and right padding of value=(padding/2)
            innerLinnerLayout.setPadding(0, 0, padding, 0);
            mainLinnerLayout.addView(innerLinnerLayout);
        }
        return convertView;
    }

    public class ViewHolder {

        AutoLoopViewPager pager;
        CirclePageIndicator indicator;
    }

    //轮播图适配器
    public class GalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = new ImageView(mContext);
            item.setImageResource(imageViewIds[position]);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            item.setLayoutParams(params);
            item.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(item);

            final int pos = position;
            /*item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ImageGalleryActivity.class);
                    intent.putStringArrayListExtra("images", (ArrayList<String>) imageList);
                    intent.putExtra("position", pos);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                }
            });*/

            return item;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        /*@Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            getParent().requestDisallowInterceptTouchEvent(true);//这句话的作用 告诉父view，我的单击事件我自行处理，不要阻碍我。
            return super.dispatchTouchEvent(ev);
        }*/
    }
}