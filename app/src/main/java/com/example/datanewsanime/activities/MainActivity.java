package com.example.datanewsanime.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.datanewsanime.R;
import com.example.datanewsanime.fragments.Home;
import com.example.datanewsanime.fragments.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private NavController controller;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = NavHostFragment.findNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_botton_navigation);
        bottomNavigationView.setupWithNavController(controller);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        /*
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()){
                            case R.id.homeFragment:
                                selectedFragment = new Home();
                                break;
                            case R.id.profileFragment:
                                selectedFragment = new Profile();
                                break;
                        }
                        bottomNavigationView.setOnNavigationItemSelectedListener();
                    }


                });*/
/*
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                onNavDestinationSelected(item, Navigation.findNavController(this, R.id.my_nav_host_fragment))
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                return false;
            }
        });

        setOnNavigationItemSelectedListener {item ->


        }*/
    }
}
