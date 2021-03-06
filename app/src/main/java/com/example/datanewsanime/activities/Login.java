package com.example.datanewsanime.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datanewsanime.R;

import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity {
    private EditText edt_user;
    private EditText edt_pass;
    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //Variables
        Button btn_login = findViewById(R.id.login);
        Button btn_singin = findViewById(R.id.singIn);
        edt_user = findViewById(R.id.userNameLogin);
        edt_pass = findViewById(R.id.passLogin);

        btn_login.setOnClickListener(v -> {
            if (edt_user.getText().toString().equals("") || edt_pass.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            } else {
                loginUser(edt_user.getText().toString(), edt_pass.getText().toString());
            }
        });

        btn_singin.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, UserRegister.class);
            startActivity(intent);
            finish();
        });


    }

    //class funcions

    private void loginUser (String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success");
                        mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(),"Login Ok",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


}