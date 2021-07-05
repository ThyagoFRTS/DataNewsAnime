package com.example.datanewsanime.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datanewsanime.R;
import com.example.datanewsanime.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;

import java.util.Date;


public class UserRegister extends AppCompatActivity {
    private EditText userName;
    private EditText email;
    private EditText pass;
    private EditText confPass;
    private User user;
    private FirebaseAuth userAuth;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        btn_register = findViewById(R.id.singIn);
        userName = findViewById(R.id.userNameRegister);
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passLogin);
        confPass = findViewById(R.id.confirmPass);

        userAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(v -> {
            if (getData()){

                makeAutentication();
            }

        });
    }

    private void makeAutentication() {
        userAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPass())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        FirebaseUser currentUser =  userAuth.getCurrentUser();
                        String id = currentUser != null ? currentUser.getUid() : "";
                        user.setId(id);
                        user.saveData();
                        startActivity(new Intent(getApplicationContext(), Home.class));

                    }else{
                        Toast.makeText(UserRegister.this,"Autentication Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private boolean getData() {
        if(email.getText().toString().equals("") || confPass.getText().toString().equals("")
                || pass.getText().toString().equals("") || userName.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Error with Credentials",Toast.LENGTH_SHORT).show();
            return false;
        } else if (pass.getText().toString().equals(confPass.getText().toString())){
            if (pass.getText().toString().length() < 6){
                Toast.makeText(getApplicationContext(),"Passwords less than 6 characters",Toast.LENGTH_SHORT).show();
                return false;
            }else{
                user = new User(userName.getText().toString(),email.getText().toString(),pass.getText().toString());
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                user.setType("User");
                user.setDate_create_account(formatter.format(date));
                return true;
            }
        }else {
            Toast.makeText(getApplicationContext(),"Passwords not equals",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}