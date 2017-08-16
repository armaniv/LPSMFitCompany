package com.example.vale.fitcompany;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Trainer;

import java.util.List;

public class TrainersActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button btnContact, btnNext;
    private ImageView imageTrainer;
    private TextView trainerName,trainerDesc;
    private View v ;
    Intent intent;
    DBOperations db;
    private List<Trainer> trainers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()


        setContentView(R.layout.activity_trainers);
        db = DBOperations.getInstance(getApplicationContext());
        db.open();
        trainers = db.GetTrainers();


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnContact = (Button) findViewById(R.id.btn_contact);
        btnNext = (Button) findViewById(R.id.btn_next);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();



        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // contact trainer
                int current= getItem(0);
                intent = new Intent(v.getContext(), ContactActivity.class);
                intent.putExtra("trainer",trainers.get(current));
                startActivity(intent);

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(1);
                if (current < trainers.size()) {
                    // move to next screen
                   // setTrainer(current);
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[trainers.size()];

        int colorsActive = getResources().getColor(R.color.dot_light_screen1);
        int colorsInactive = getResources().getColor(R.color.dot_dark_screen1);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    private void setTrainer(int i)
    {

        imageTrainer = (ImageView) v.findViewById(R.id.trainerPic);
        trainerName = (TextView) v.findViewById(R.id.trainerName);
        trainerDesc = (TextView) v.findViewById(R.id.trainerDesc);
        imageTrainer.setImageResource(R.drawable.trainerplaceholder);
        trainerName.setText(trainers.get(i).getNome() + " " + trainers.get(i).getCognome());
        trainerDesc.setText(trainers.get(i).getSpecialita());


    }

    private void launchHomeScreen() {
        startActivity(new Intent(TrainersActivity.this, MainActivity.class));
        finish();
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {


            //setTrainer(position);
            addBottomDots(position);

            if (position == trainers.size()-1) {
                // last page. make button text to GOT IT
                btnNext.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = layoutInflater.inflate(R.layout.trainers1, container, false);

            setTrainer(position);


            container.addView(v);

            return v;
        }

        @Override
        public int getCount() {
            return trainers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
