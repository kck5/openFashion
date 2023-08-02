package com.bmlmunjal.openfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Add_AddressActivity extends AppCompatActivity implements View.OnClickListener {

    Button savebtn;
    EditText name,mobile,pincode,state,address,locality,city;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    FirebaseDatabase database;
//    private FirebaseDatabase databaseUsers = FirebaseDatabase.getInstance();
//    private DatabaseReference root = databaseUsers.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        savebtn = (Button) findViewById(R.id.save_address);
        name = (EditText) findViewById(R.id.enter_name);
        mobile = (EditText) findViewById(R.id.enter_mobile_number);
        pincode = (EditText) findViewById(R.id.enter_pincode);
        state = (EditText) findViewById(R.id.enter_state);
        address = (EditText) findViewById(R.id.enter_address);
        locality = (EditText) findViewById(R.id.enter_locality);
        city = (EditText) findViewById(R.id.enter_city);
//        FirebaseDatabase databaseUsers = FirebaseDatabase.getInstance();
//        DatabaseReference root = databaseUsers.getReference().child("Users");


        savebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.save_address):

                String username = name.getText().toString();
                String usermobile = mobile.getText().toString();
                String userpincode = pincode.getText().toString();
                String userstate = state.getText().toString();
                String useraddress = address.getText().toString();
                String userlocality = locality.getText().toString();
                String usercity = city.getText().toString();
                rootNode= FirebaseDatabase.getInstance();
                reference = rootNode.getReference("User");

                reference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue(username);
                reference.child(FirebaseAuth.getInstance().getUid()).child("mobile").setValue(usermobile);
                reference.child(FirebaseAuth.getInstance().getUid()).child("pincode").setValue(userpincode);
                reference.child(FirebaseAuth.getInstance().getUid()).child("state").setValue(userstate);
                reference.child(FirebaseAuth.getInstance().getUid()).child("address").setValue(useraddress);
                reference.child(FirebaseAuth.getInstance().getUid()).child("locality").setValue(userlocality);
                reference.child(FirebaseAuth.getInstance().getUid()).child("city").setValue(usercity);
                finish();
                break;
        }
    }

    private void InsertData() {
        String username = name.getText().toString();
        String usermobile = mobile.getText().toString();
        String userpincode = pincode.getText().toString();
        String userstate = state.getText().toString();
        String useraddress = address.getText().toString();
        String userlocality = locality.getText().toString();
        String usercity = city.getText().toString();



        User user = new User(username,usermobile,userpincode,userstate,useraddress,userlocality,usercity);
        HashMap<String,Object> obj = new HashMap<>();
        obj.put("name",username);
        obj.put("mobile",usermobile);
        obj.put("Pincode",userpincode);
        obj.put("state",userstate);
        obj.put("address",useraddress);
        obj.put("City",usercity);


        database.getReference().child("User").child(FirebaseAuth.getInstance().getUid()).updateChildren(obj);
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                        {
//                            Toast.makeText(Add_AddressActivity.this,"Address is successfully saved",Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(Add_AddressActivity.this,"Error not saved",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }
}