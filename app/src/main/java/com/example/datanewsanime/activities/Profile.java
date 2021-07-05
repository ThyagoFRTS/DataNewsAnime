package com.example.datanewsanime.activities;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;

import android.content.Intent;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datanewsanime.R;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;


import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class Profile extends AppCompatActivity {
    DatabaseReference reff;
    StorageReference storageRef;
    int Image_Request_Code = 2;
    public Uri imageUri;
    private ImageView imageView;
    private FirebaseUser user;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView userName = findViewById(R.id.profile_username);
        final TextView userEmail = findViewById(R.id.profile_email_user);
        final TextView type = findViewById(R.id.profile_type);
        final TextView date = findViewById(R.id.profile_data);
        final Button btn_logout = findViewById(R.id.profile_button_logout);
        imageView = findViewById(R.id.profile_image_perfil);
        BottomNavigationView bottomNavigationView = findViewById(R.id.profile_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);



        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseStorage mStorage = FirebaseStorage.getInstance();
        storageRef = mStorage.getReference();
        Toast.makeText(getApplicationContext(),storageRef.toString(),Toast.LENGTH_LONG).show();





        if (user != null) {
            System.out.println("-----------------------------------------");
            user.getUid();

            reff = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());

            reff.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    String name = "";
                    if (snapshot.child("userName").getValue() != null) {
                        name = Objects.requireNonNull(snapshot.child("userName").getValue()).toString();
                    }
                    userName.setText(String.format("User Name: %s", name));

                    name = "";
                    if (snapshot.child("email").getValue() != null) {
                        name = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
                    }
                    userEmail.setText(String.format("User Email: %s", name));

                    name = "";
                    if (snapshot.child("date_create_account").getValue() != null) {
                        name = Objects.requireNonNull(snapshot.child("date_create_account").getValue()).toString();
                    }
                    date.setText(String.format("Account created on: %s", name));

                    name = "";
                    if (snapshot.child("type").getValue() != null) {
                        name = Objects.requireNonNull(snapshot.child("type").getValue()).toString();
                    }
                    type.setText(String.format("Class: %s", name));
                    /*
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        StorageReference photo = storageRef.child(user.getUid()+".jpg");
                        final long MEGABYTE = 1024 * 1024;
                        photo.getBytes(MEGABYTE).addOnSuccessListener(bytes -> {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            imageView.setImageBitmap(bitmap);
                        });

                    }*/
                    if (snapshot.child("url_img_profile").getValue() != null) {
                        name = Objects.requireNonNull(snapshot.child("url_img_profile").getValue()).toString();
                        user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user!=null){
                            StorageReference photo = storageRef.child(user.getUid()+".jpg");
                            final long MEGABYTE = 1024 * 1024;
                            photo.getBytes(MEGABYTE).addOnSuccessListener(bytes -> {
                                Bitmap profile_photo = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                imageView.setImageBitmap(profile_photo);

                            });

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });

        }

        imageView.setOnClickListener(v -> selectPhoto());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0, 0);
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


    @SuppressWarnings("deprecation")
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
            String image_path = data.getData().getPath();
            imageView.setImageURI(imageUri);

            //
            System.out.println("---------------PICASSO-------------------------");
            System.out.println(image_path);
            user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null){

                sendPhoto(user.getUid());
            }



        }
    }

    void sendPhoto(String id){
        Bitmap bitmap = ( (BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagem = byteArrayOutputStream.toByteArray();
        StorageReference profileImg = storageRef.child(id +".jpg");
        UploadTask uploadTask = profileImg.putBytes(imagem);
        uploadTask.addOnSuccessListener(taskSnapshot -> Toast.makeText(getApplicationContext(),"Upload Sucessful",Toast.LENGTH_SHORT).show());
        reff.child("url_img_profile").setValue(id +".jpg");


    }


}