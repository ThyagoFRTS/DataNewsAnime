package com.example.datanewsanime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.datanewsanime.handlexml.ConnectionAPIs;
import com.example.datanewsanime.handlexml.HandleXML;
import com.example.datanewsanime.handlexml.NewsData;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private Button btn_logout;
    private TextView textView;
    private List<NewsData> dataFeed = new ArrayList<>();
    String url_news = "https://www.animenewsnetwork.com/encyclopedia/reports.xml?id=155&nlist=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        btn_logout = findViewById(R.id.showData);
        textView = findViewById(R.id.textView);

        btn_logout.setOnClickListener(v -> {
            TaskConnection task = new TaskConnection();
            task.execute();
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
            dataFeed = HandleXML.xmlExtract(s);
            showDataFeed();
        }

        private void showDataFeed() {
            if (dataFeed != null){
                for (NewsData item : dataFeed){
                    textView.append(item.getName()+"\n");
                }
            }
        }
    }
}