package com.bmlmunjal.openfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;
    private Button LogInButton,SignUpButton;

    private FirebaseAuth mAuth;
    TextView[] dots;
    ViewPagerAdaptor viewPagerAdaptor;

    String onBoardingText="SignIn";
    public static final String EXTRA_NAME= "com.bmlmunjal.openfashion.extra.onboarding";

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final int NUM_PAGES=3;
    final long PERIOD_MS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton=findViewById(R.id.buttonLogIn);
        SignUpButton=findViewById(R.id.buttonSignUp);

        mAuth= FirebaseAuth.getInstance();

        mSliderViewPager= findViewById(R.id.viewPageImageSlider);
        mDotLayout=(LinearLayout) findViewById(R.id.theShiftingDots);
        viewPagerAdaptor= new ViewPagerAdaptor(this);
        mSliderViewPager.setAdapter(viewPagerAdaptor);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        setUpIndicator(0);

        mSliderViewPager.addOnPageChangeListener(viewListener);

        LogInButton.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(this,HomeActivity.class));
        }
    }

    public void setUpIndicator(int position){
        mDotLayout.removeAllViews();
        dots= new TextView[3];
        Log.d("Test", "onPageSelected: It entered setUpIndicator");
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.black,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.d("Test", "onPageSelected: It entered changeListener");
            setUpIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.buttonSignUp):
                onBoardingText="signUp";
                Intent intent = new Intent(this, Onboarding.class);
                intent.putExtra(EXTRA_NAME, onBoardingText);
                startActivity(intent);
                Log.d("xyz", "onClick: signUp");
                break;
            case (R.id.buttonLogIn):
                onBoardingText="xyz";
                Intent intent1 = new Intent(this, Onboarding.class);
                intent1.putExtra(EXTRA_NAME, onBoardingText);
                startActivity(intent1);
                break;
        }
    }
}