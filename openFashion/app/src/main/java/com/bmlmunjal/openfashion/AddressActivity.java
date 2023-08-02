package com.bmlmunjal.openfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddressActivity extends AppCompatActivity {

    FirebaseDatabase database;

    TextView addaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addaddress= findViewById(R.id.add_add);
        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( AddressActivity.this,Add_AddressActivity.class));
            }
        });

        database = FirebaseDatabase.getInstance();

        database.getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        TextView name = findViewById(R.id.name);
                        name.setText(user.getName());

                        TextView mobile = findViewById(R.id.phonenumber);
                        mobile.setText(user.getMobile());

                        TextView address = findViewById(R.id.address);
                        address.setText(user.getAddress());

                        TextView locality = findViewById(R.id.locality);
                        locality.setText(user.getLocality());

                        TextView city = findViewById(R.id.city);
                        city.setText(user.getCity());

                        TextView pincode = findViewById(R.id.Pincode);
                        pincode.setText(user.getPincode());
                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}