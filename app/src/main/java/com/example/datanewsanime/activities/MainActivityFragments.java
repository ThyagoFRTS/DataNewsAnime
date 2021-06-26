package com.example.datanewsanime.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.datanewsanime.R;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityFragments extends AppCompatActivity {
    public NavController navController;
    BottomNavigationView bottomNavigationView;



    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bottomNavigation.setupWithNavController(Navigation.findNavController(this, R.id.activity_main_nav_host));
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host);
        bottomNavigationView = findViewById(R.id.activity_main_botton_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            //onNavDestinationSelected(item, Navigation.findNavController(this, R.id.my_nav_host_fragment))
            switch (item.getItemId()){

                case R.id.homeFragment4:
                    System.out.println("--------------------------HOME");
                    System.out.println(navController.getCurrentDestination().getId());
                    //navController.navigate(HomeDirections.actionGlobalLoginFragment() );
                    break;
                case R.id.profileFragment4:
                    System.out.println("--------------------------PROFILE");
                    System.out.println(navController.getCurrentDestination().getId());

                    //navController.navigate(HomeDirections.actionHomeFragmentToProfileFragment());
                    break;
            }
            return true;
        });







    }
}
