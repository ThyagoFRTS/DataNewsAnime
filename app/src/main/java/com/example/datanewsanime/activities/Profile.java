package com.example.datanewsanime.activities;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datanewsanime.R;
import com.example.datanewsanime.settings.SettingsDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Profile extends AppCompatActivity {
    DatabaseReference reff;
    FirebaseFirestore db;
    int Image_Request_Code = 2;
    public Uri imageUri;
    private ImageView imageView;
    private FirebaseUser user;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userName = findViewById(R.id.profile_username);
        TextView userEmail = findViewById(R.id.profile_email_user);
        TextView type = findViewById(R.id.profile_type);
        TextView date = findViewById(R.id.profile_data);
        Button btn_logout = findViewById(R.id.profile_button_logout);
        imageView = findViewById(R.id.profile_image_perfil);
        BottomNavigationView bottomNavigationView = findViewById(R.id.profile_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();




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

                    name = "";
                    if (snapshot.child("email").getValue() != null){
                        name = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
                    }
                    userEmail.setText(String.format("User Email: %s", name));

                    name = "";
                    if (snapshot.child("date_create_account").getValue() != null){
                        name = Objects.requireNonNull(snapshot.child("date_create_account").getValue()).toString();
                    }
                    date.setText(String.format("Account created on: %s", name));

                    name = "";
                    if (snapshot.child("type").getValue() != null){
                        name = Objects.requireNonNull(snapshot.child("type").getValue()).toString();
                    }
                    type.setText(String.format("Class: %s", name));

                    if ( snapshot.child("url_img_profile").getValue() != "" && snapshot.child("url_img_profile").getValue() != null) {
                        name = Objects.requireNonNull(snapshot.child("url_img_profile").getValue()).toString();
                        try {
                            Picasso.get().load(name).into(imageView);
                        }catch (Exception e){
                            e.printStackTrace();

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });

        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

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

    private void selectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Request_Code && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);
            //imageView.setImageURI(imageUri);
            System.out.println("---------------PICASSO-------------------------");
            System.out.println(imageUri.toString());
            user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null){
                String id = user.getUid();
                reff.child("url_img_profile").setValue(imageUri.toString());
            }
        }
    }











}