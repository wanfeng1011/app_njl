package com.app.njl.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;
import com.app.njl.R;
import com.app.njl.utils.SharedPreferences;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jiaxx on 2016/4/1 0001.
 */
public class CalendarActivity extends Activity implements DatePickerController{
    private DayPickerView dayPickerView;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calendar);
        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        dayPickerView.setController(this);
        intent = getIntent();
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
        SharedPreferences.getInstance().putString("live_in", selectedDays.getFirst().getMonth()
                + "月" + selectedDays.getFirst().getDay() + "日");
        SharedPreferences.getInstance().putString("live_out", selectedDays.getLast().getMonth()
                + "月" + selectedDays.getLast().getDay() + "日");
        int daysCount = daysBetween(selectedDays.getFirst().getYear() + "-" + selectedDays.getFirst().getMonth()
                + "-" + selectedDays.getFirst().getDay() + " 00:00:00", selectedDays.getLast().getYear() + "-" + selectedDays.getLast().getMonth()
                + "-" + selectedDays.getLast().getDay() + " 00:00:00");
        KLog.i("daysCount:" + daysCount);
        SharedPreferences.getInstance().putInt("total_day", daysCount);
        setResult(1, intent);
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

}
