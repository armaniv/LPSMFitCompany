package com.example.vale.fitcompany;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CalendarActivity extends AppCompatActivity {
    MaterialCalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar= ( MaterialCalendarView) findViewById(R.id.calendario);
        calendar.setDateTextAppearance(R.style.AppTheme);
        calendar.invalidateDecorators();
      //  calendar.setDateTextAppearance(R.style.AppTheme);ia
    }
}
