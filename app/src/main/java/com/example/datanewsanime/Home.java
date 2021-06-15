package com.example.datanewsanime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;
import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.NewsData;
import com.example.datanewsanime.recyclerview.adapter.NewsDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private Button btn_logout;
    private List<NewsData> dataFeed = new ArrayList<>();
    private NewsDataAdapter adapter;
    private MalInfoAnime iam;
    private TextView tv;
    String url_news = "https://www.animenewsnetwork.com/encyclopedia/reports.xml?id=155&nlist=10";
    String url_info_anime = "https://cdn.animenewsnetwork.com/encyclopedia/api.xml?title=10906";

    String searchAnimeTitleId = "https://api.jikan.moe/v3/anime/";
    String searchMangaTitleId = "https://api.jikan.moe/v3/manga/";
    String searchAnimeTitle = "https://api.jikan.moe/v3/search/anime?q=";
    String searchMangaTitle = "https://api.jikan.moe/v3/search/manga?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_logout = findViewById(R.id.showData);
        tv = findViewById(R.id.textView);

        RecyclerView feedNewsRecyclerView = findViewById(R.id.activity_home_recyclerview);
        adapter = new NewsDataAdapter(this);
        feedNewsRecyclerView.setAdapter(adapter);


        btn_logout.setOnClickListener(v -> {
            TaskConnection task = new TaskConnection();
            //task.execute(url_news);
            task.execute(searchAnimeTitle+"naruro shippuden");
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class TaskConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return ConnectionAPIs.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            iam = HandleJSON.getMalInfoAnime(s);
            tv.setText(iam.getTitle());
            /*
            dataFeed = HandleXML.xmlExtract(s);
            adapter.refresh(dataFeed);*/
        }

    }
}