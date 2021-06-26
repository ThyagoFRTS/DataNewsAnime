package com.example.datanewsanime.activities;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import com.example.datanewsanime.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Profile extends AppCompatActivity {
    DatabaseReference reff;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userName = findViewById(R.id.profile_username);
        Button btn_logout = findViewById(R.id.profile_button_logout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.profile_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            System.out.println("-----------------------------------------");
            user.getUid();
            reff = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    String name = "";
                    if (snapshot.child("userName").getValue() != null){
                        name = Objects.requireNonNull(snapshot.child("userName").getValue()).toString();
                    }
                    userName.setText(String.format("User Name: %s", name));
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });

        }

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0,0);


                    return true;
                case R.id.menu_profile:
                    System.out.println("home is here");
                    return true;
            }
            return false;
        });

        btn_logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });
    }
}