package com.example.vale.fitcompany;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class CalendarActivity extends AppCompatActivity {
    MaterialCalendarView calendar;
    private HashSet<CalendarDay> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar= ( MaterialCalendarView) findViewById(R.id.calendario);
        calendar.setDateTextAppearance(R.style.AppTheme);
        calendar.invalidateDecorators();
      //  calendar.setDateTextAppearance(R.style.AppTheme);
        Calendar c = Calendar.getInstance();
        dates=new HashSet<CalendarDay>();


        dates.add(new CalendarDay().from(c));
        calendar.addDecorator(new CircleDecorator(this,R.drawable.decor,dates));

    }
    public class CircleDecorator implements DayViewDecorator {

        private HashSet<CalendarDay> dates;
        private Drawable drawable;

        public CircleDecorator(Context context, int resId, Collection<CalendarDay> dates) {
            drawable = ContextCompat.getDrawable(context, resId);
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.WHITE));
            view.setSelectionDrawable(drawable);
        }
    }
}
