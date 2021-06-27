package com.example.datanewsanime.activities;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;


import com.example.datanewsanime.R;
import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;

import com.example.datanewsanime.models.MalNextSeason;
import com.example.datanewsanime.recyclerview.adapter.NewsDataAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;



import java.util.List;

public class Home extends AppCompatActivity {
    private NewsDataAdapter adapter;

    String animesSeasonLaterUrl = "https://api.jikan.moe/v3/season/later";

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        SearchView searchView = findViewById(R.id.home_search_view);

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

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    System.out.println("home is here");
                    return true;
                case R.id.menu_profile:
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(0,0);
                    finish();

                    return true;
            }
            return false;
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(Home.this, SearchResults.class);
                intent.putExtra("query_anime_tilte",query);
                startActivity(intent);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });




    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    private class TaskConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return ConnectionAPIs.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            List<MalNextSeason> dataFeed = HandleJSON.getMalNextSeason(s);
            adapter.refresh(dataFeed);

        }

    }
}