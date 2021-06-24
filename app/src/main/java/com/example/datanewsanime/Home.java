package com.example.datanewsanime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;
import com.example.datanewsanime.models.MalNextSeason;

import com.example.datanewsanime.recyclerview.adapter.NewsDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private List<MalNextSeason> dataFeed = new ArrayList<>();
    private NewsDataAdapter adapter;



    String animesSeasonLaterUrl = "https://api.jikan.moe/v3/season/later";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        RecyclerView feedNewsRecyclerView = findViewById(R.id.activity_home_recyclerview);
        adapter = new NewsDataAdapter(this);
        feedNewsRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(malNextSeason -> {
            Intent intent = new Intent(Home.this, NewsContent.class);
            intent.putExtra("content_malNextSeason",malNextSeason);
            startActivity(intent);

        });

        TaskConnection task = new TaskConnection();
        task.execute(animesSeasonLaterUrl);



        


    }

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