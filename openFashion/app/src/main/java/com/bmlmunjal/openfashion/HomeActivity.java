package com.bmlmunjal.openfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment= new HomeFragment();
    NavigationFragment navigationFragment= new NavigationFragment();
    CartFragment cartFragment= new CartFragment();
    ProfileFragment profileFragment= new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomNavigationViewHomeActivity);

        replaceFragment(homeFragment);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.homeBottomNavigationMenu):
                        replaceFragment(homeFragment);
                        return true;
                    case (R.id.navigationBottomNavigationMenu):
//                        replaceFragment(navigationFragment);
                        //need to add navigation map
                        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case (R.id.cartBottomNavigationMenu):
                        replaceFragment(cartFragment);
                        return true;
                    case (R.id.profileBottomNavigationMenu):
                        replaceFragment(profileFragment);
                        return true;
                }
                return false;
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutHomeActivity,fragment);
        fragmentTransaction.commit();
    }
}