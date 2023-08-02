package com.bmlmunjal.openfashion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SizeChartActivity extends AppCompatActivity {
    SwitchCompat switchCompat,switchCompat1;
    ImageView imageView,imageView1,backse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_chart);

        switchCompat = findViewById(R.id.switch_men);
        imageView = findViewById(R.id.men_sc_img);
        switchCompat1 = findViewById(R.id.switch_women);
        imageView1 = findViewById(R.id.women_sc_img);
        backse = findViewById(R.id.back_sc);


        imageView.setImageDrawable(getResources().getDrawable(R.drawable.in));
        imageView1.setImageDrawable(getResources().getDrawable(R.drawable.in_g));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat.isChecked()){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.cm));
                }
                else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.in));
                }
            }
        });

        switchCompat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat1.isChecked()){
                    imageView1.setImageDrawable(getResources().getDrawable(R.drawable.cm_g));
                }
                else {
                    imageView1.setImageDrawable(getResources().getDrawable(R.drawable.in_g));
                }
            }
        });


    }
}