package com.example.datanewsanime.fragments;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.datanewsanime.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView userName = getActivity().findViewById(R.id.profile_username);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        if (user != null) {
            System.out.println("-----------------------------------------");
            user.getUid();
            System.out.println(FirebaseDatabase.getInstance().getReference().child("Users").child("CQlGTTZyYtMAWW5Ifm8a28ipzBC2").child("userName").getRef());
        }


    }

        /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userName = findViewById(R.id.profile_username);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference data = FirebaseDatabase.getInstance().getReference();
        if (user != null) {
            System.out.println("-----------------------------------------");
            user.getUid();
            System.out.println(FirebaseDatabase.getInstance().getReference().child("Users").child("CQlGTTZyYtMAWW5Ifm8a28ipzBC2").child("userName").getRef());
        }
    }*/
}