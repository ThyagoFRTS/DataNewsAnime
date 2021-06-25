package com.example.datanewsanime.fragments;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datanewsanime.R;
import com.example.datanewsanime.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;


public class UserRegister extends Fragment {
    private EditText userName;
    private EditText email;
    private EditText pass;
    private EditText confPass;
    private User user;
    private FirebaseAuth userAuth;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_register,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_register = getActivity().findViewById(R.id.singIn);
        userName = getActivity().findViewById(R.id.userNameRegister);
        email = getActivity().findViewById(R.id.emailLogin);
        pass = getActivity().findViewById(R.id.passLogin);
        confPass = getActivity().findViewById(R.id.confirmPass);

        userAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(v -> {
            if (getData()){

                //makeAutentication();
            }

        });

    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void makeAutentication() {
        userAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPass())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        FirebaseUser currentUser =  userAuth.getCurrentUser();
                        user.setId(currentUser.getUid());
                        user.saveData();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    }else{
                        Toast.makeText(UserRegister.this,"Autentication Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

    private boolean getData() {
        if(email.getText().toString().equals("") || confPass.getText().toString().equals("")
                || pass.getText().toString().equals("") || userName.getText().toString().equals("")){
            Toast.makeText(getContext(),"Error with Credentials",Toast.LENGTH_SHORT).show();
            return false;
        } else if (pass.getText().toString().equals(confPass.getText().toString())){
            if (pass.getText().toString().length() < 6){
                Toast.makeText(getContext(),"Passwords less than 6 characters",Toast.LENGTH_SHORT).show();
                return false;
            }else{
                user = new User(userName.getText().toString(),email.getText().toString(),pass.getText().toString());
                return true;
            }
        }else {
            Toast.makeText(getContext(),"Passwords not equals",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}