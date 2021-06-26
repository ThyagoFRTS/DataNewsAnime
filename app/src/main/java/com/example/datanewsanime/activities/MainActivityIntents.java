package com.example.datanewsanime.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.datanewsanime.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityIntents extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);




        

    }




        /*
    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        finish();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);
            finish();
        }
    }
        */


}