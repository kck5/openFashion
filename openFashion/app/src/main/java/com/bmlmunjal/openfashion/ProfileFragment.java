package com.bmlmunjal.openfashion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final int REQUEST_CALL = 1;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    Button logoutButton;
    ImageView arrowsizechart,arrowreturn,arrowcall,address;
    ImageView imageViewArrowReturn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        View myView=inflater.inflate(R.layout.fragment_profile, container, false);
        logoutButton=(Button) myView.findViewById(R.id.logoutButtonProfileFragment);
        imageViewArrowReturn = (ImageView) myView.findViewById(R.id.arrow_returnn);

        logoutButton.setOnClickListener(this);

        arrowsizechart= myView.findViewById(R.id.arrow_sizechart);
        arrowsizechart.setOnClickListener(this);

        arrowreturn= (ImageView) myView.findViewById(R.id.arrow_returnn);
        arrowreturn.setOnClickListener(this);

        arrowcall= (ImageView) myView.findViewById(R.id.help_centre_arrow);
        arrowcall.setOnClickListener(this);

        address = (ImageView) myView.findViewById(R.id.address_arrow);
        address.setOnClickListener(this);

        myView.findViewById(R.id.joinour_team_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Please join our Team by installing Open Fashion.....    shorturl.at/lpL14");
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent,"Share via");
                startActivity(sendIntent);
            }
        });

        TextView name = myView.findViewById(R.id.Name);
        database.getReference().child("User").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        name.setText(user.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        return myView;
    }
    private void closeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== REQUEST_CALL){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(getActivity(), "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.logoutButtonProfileFragment):
                mAuth.signOut();
                startActivity(new Intent(getActivity(),MainActivity.class));
                break;
            case(R.id.arrow_sizechart):
                startActivity(new Intent(getActivity(),SizeChartActivity.class));
                break;
            case(R.id.arrow_returnn):
                startActivity(new Intent(getActivity(),returnActivity.class));
                break;
            case(R.id.help_centre_arrow):
                makePhoneCall();
                break;
            case(R.id.address_arrow):
                startActivity(new Intent(getActivity(),AddressActivity.class));
                break;
        }
    }
    public void makePhoneCall(){
        String number= "+918688014820";
        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            String dial= "tel:"+ number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
}