package com.example.datanewsanime.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datanewsanime.R;
import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;
import com.example.datanewsanime.models.MalNextSeason;

import com.example.datanewsanime.recyclerview.adapter.NewsDataAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    private List<MalNextSeason> dataFeed = new ArrayList<>();
    private NewsDataAdapter adapter;



    String animesSeasonLaterUrl = "https://api.jikan.moe/v3/season/later";

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView feedNewsRecyclerView = (RecyclerView) view.findViewById(R.id.activity_home_recyclerview);
        adapter = new NewsDataAdapter(getContext());
        feedNewsRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(malNextSeason -> {
            /*
            Intent intent = new Intent(Home.this, NewsContent.class);
            intent.putExtra("content_malNextSeason",malNextSeason);
            startActivity(intent);
            */
        });

        TaskConnection task = new TaskConnection();
        task.execute(animesSeasonLaterUrl);

    }
    /**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }*/

    @SuppressLint("StaticFieldLeak")
    private class TaskConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return ConnectionAPIs.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            dataFeed = HandleJSON.getMalNextSeason(s);
            adapter.refresh(dataFeed);

        }

    }
}