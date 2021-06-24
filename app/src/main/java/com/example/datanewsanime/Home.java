package com.example.datanewsanime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;
import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.MalNews;
import com.example.datanewsanime.models.MalNextSeason;
import com.example.datanewsanime.models.NewsData;
import com.example.datanewsanime.recyclerview.adapter.NewsDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private List<MalNextSeason> dataFeed = new ArrayList<>();
    private NewsDataAdapter adapter;
    private MalInfoAnime iam;
    private MalNews mn;
    private TextView tv;
    private CardView cv;
    private RecyclerView recyclerView;

    String url_news = "https://www.animenewsnetwork.com/encyclopedia/reports.xml?id=155&nlist=10";
    String url_info_anime = "https://cdn.animenewsnetwork.com/encyclopedia/api.xml?title=10906";

    String searchAnimeTitleId = "https://api.jikan.moe/v3/anime/";
    String searchMangaTitleId = "https://api.jikan.moe/v3/manga/";
    String searchAnimeTitle = "https://api.jikan.moe/v3/search/anime?q=";
    String searchMangaTitle = "https://api.jikan.moe/v3/search/manga?q=";
    String animesSeasonLaterUrl = "https://api.jikan.moe/v3/season/later";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //cv = findViewById(R.id.card_preview_next_season);


        RecyclerView feedNewsRecyclerView = findViewById(R.id.activity_home_recyclerview);
        adapter = new NewsDataAdapter(this);
        feedNewsRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewsDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MalNextSeason malNextSeason) {
                Intent intent = new Intent(Home.this, NewsContent.class);
                intent.putExtra("content_malNextSeason",malNextSeason);
                startActivity(intent);

            }
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