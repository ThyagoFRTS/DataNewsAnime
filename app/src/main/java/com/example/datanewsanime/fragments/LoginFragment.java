package com.example.datanewsanime.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datanewsanime.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class LoginFragment extends Fragment {
    private EditText edt_user;
    private EditText edt_pass;
    //Firebase
    private FirebaseAuth mAuth;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        //Variables
        Button btn_login = view.findViewById(R.id.login);
        Button btn_singin = getView().findViewById(R.id.singIn);
        edt_user = view.findViewById(R.id.userNameLogin);
        edt_pass = view.findViewById(R.id.passLogin);

        btn_login.setOnClickListener(v -> {
            if (edt_user.getText().toString().equals("") || edt_pass.getText().toString().equals("")) {
                Toast.makeText(getActivity().getApplicationContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            } else {
                loginUser(edt_user.getText().toString(), edt_pass.getText().toString());
            }
        });

        btn_singin.setOnClickListener(v -> {
            /**
             Intent intent = new Intent(Login.this, UserRegister.class);
             startActivity(intent);
             finish();
             */
        });
    }
    /**
     @Override
     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_login);
     }*/

    //class funcions



    private void loginUser (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getActivity().getApplicationContext(),"Login Ok",Toast.LENGTH_SHORT).show();
                        /**
                         Intent intent = new Intent(Login.this, MainActivity2.class);
                         startActivity(intent);
                         finish();
                         */
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                        Toast.makeText(getActivity().getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


}