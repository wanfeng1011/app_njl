package com.app.njl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.njl.R;
import com.app.njl.base.BaseActivity;
import com.app.njl.utils.SharedPreferences;
import com.app.njl.widget.calendar.DatePickerController;
import com.app.njl.widget.calendar.DayPickerView;
import com.app.njl.widget.calendar.SimpleMonthAdapter;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jiaxx on 2016/4/1 0001.
 */
public class CalendarActivity extends BaseActivity implements DatePickerController {
    private DayPickerView dayPickerView;
    private ImageView iv_back;
    Intent intent;
    private SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays;

    @Override
    protected void initContentView() {
        setContentView(R.layout.layout_calendar);
        iv_back = (ImageView) findViewById(R.id.calendar_iv_back);
        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        iv_back.setOnClickListener(this);
        dayPickerView.setController(this);
        intent = getIntent();
        selectedDays = (SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay>)intent.getSerializableExtra("selectedDays");
        if(selectedDays != null)
            Log.i("selectDays", "CarlandarActivity selectedDays:" + selectedDays.toString());
        dayPickerView.setSelectedDays(selectedDays);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initControl() {

    }

    @Override
    public int getMaxYear() {
        return 2017;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        KLog.e("Day Selected", day + " / " + month + " / " + year);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

        KLog.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
        //设置住店、离店日期到SharePreference
        SharedPreferences.getInstance().putInt("live_in_year", selectedDays.getFirst().getYear());
        SharedPreferences.getInstance().putInt("live_in_month", selectedDays.getFirst().getMonth());
        SharedPreferences.getInstance().putInt("live_in_day", selectedDays.getFirst().getDay());
        SharedPreferences.getInstance().putInt("live_out_year", selectedDays.getLast().getYear());
        SharedPreferences.getInstance().putInt("live_out_month", selectedDays.getLast().getMonth());
        SharedPreferences.getInstance().putInt("live_out_day", selectedDays.getLast().getDay());
        int daysCount = daysBetween(selectedDays.getFirst().getYear() + "-" + selectedDays.getFirst().getMonth()
                + "-" + selectedDays.getFirst().getDay() + " 00:00:00", selectedDays.getLast().getYear() + "-" + selectedDays.getLast().getMonth()
                + "-" + selectedDays.getLast().getDay() + " 00:00:00");
        KLog.i("daysCount:" + daysCount);
        SharedPreferences.getInstance().putInt("total_day", daysCount-1);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedDays", selectedDays);
        setResult(1, resultIntent);
        finish();
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate,String bdate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long between_days = 0;
        try {
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return (int)between_days + 1;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.calendar_iv_back) {
            finish();
        }
    }
}
