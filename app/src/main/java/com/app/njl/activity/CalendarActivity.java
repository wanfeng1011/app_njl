package com.app.njl.activity;

import android.app.Activity;
import android.os.Bundle;

import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;
import com.app.njl.R;
import com.socks.library.KLog;

/**
 * Created by jiaxx on 2016/4/1 0001.
 */
public class CalendarActivity extends Activity implements DatePickerController{
    private DayPickerView dayPickerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calendar);
        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        dayPickerView.setController(this);
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
    }
}
