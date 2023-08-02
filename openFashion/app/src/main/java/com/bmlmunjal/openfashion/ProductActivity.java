package com.bmlmunjal.openfashion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buyNowButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        buyNowButton= findViewById(R.id.buynow);

        buyNowButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.buynow):
                startActivity(new Intent(ProductActivity.this, PaymentActivity.class));
                finish();
                break;
        }
    }
}