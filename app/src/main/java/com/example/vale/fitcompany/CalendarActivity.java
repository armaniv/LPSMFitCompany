package com.example.vale.fitcompany;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.widget.CalendarView;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Chiusura;
import com.example.vale.fitcompany.Oggetti.News;
import com.example.vale.fitcompany.Oggetti.Orario;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    DBOperations db;
    MaterialCalendarView calendar;
    private HashSet<CalendarDay> dates;
    List<Chiusura> chiusure;
    List<Orario> orarionormale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        db = DBOperations.getInstance(getApplicationContext());
        db.open();
        try {
            chiusure = db.GetChiusura();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orarionormale=db.GetOrario();
        db.close();
        calendar= ( MaterialCalendarView) findViewById(R.id.calendario);
        calendar.setDateTextAppearance(R.style.AppTheme);
        calendar.invalidateDecorators();
      //  calendar.setDateTextAppearance(R.style.AppTheme);
        Calendar c = Calendar.getInstance();
        dates=new HashSet<CalendarDay>();
        setHashCalendar();
        dates.add(new CalendarDay().from(c));
        calendar.addDecorator(new CircleDecorator(this,R.drawable.decor,dates));

    }
    private void setHashCalendar()
    {
        List<Date> date=new ArrayList<Date>();
        for(int i=0; i< chiusure.size();i++)
        {
            date.addAll(getDates(chiusure.get(i).getInizio(),chiusure.get(i).getFine()));

        }
        for(int i=0; i< date.size();i++)
        {
            dates.add(new CalendarDay().from(date.get(i)));

        }

    }
    private  List<Date> getDates(Date date1, Date date2)
    {
        ArrayList<Date> dates = new ArrayList<Date>();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
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
