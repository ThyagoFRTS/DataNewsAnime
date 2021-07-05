package com.example.datanewsanime.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.datanewsanime.R;
import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;
import com.example.datanewsanime.models.MalFullData;
import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.MalNews;
import com.example.datanewsanime.models.MalNextSeason;
import com.squareup.picasso.Picasso;

public class SearchResults extends AppCompatActivity {
    MalInfoAnime mia;
    Button btn_back;
    String malId;
    String searchAnimeTitle = "https://api.jikan.moe/v3/search/anime?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        btn_back = findViewById(R.id.search_results_btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResults.this, Home.class );
                startActivity(intent);
                finish();
            }
        });

        boolean hasExtraFromSearchView = getIntent().hasExtra("query_anime_tilte");
        if (hasExtraFromSearchView){

            String title = getIntent().getSerializableExtra("query_anime_tilte").toString();
            SearchResults.TaskMalInfoConnection task = new SearchResults.TaskMalInfoConnection();
            task.execute(searchAnimeTitle+title);




        }

    }






    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    private class TaskMalInfoConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return ConnectionAPIs.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            mia = HandleJSON.getMalInfoAnime(s);
            setFieldsMalInfoAnime();



        }

        @SuppressLint("SetTextI18n")
        private void setFieldsMalInfoAnime() {
            TextView search_reults_airing = findViewById(R.id.search_reults_airing);
            TextView search_reults_episodes = findViewById(R.id.search_reults_episodes);
            TextView search_reults_start_data = findViewById(R.id.search_reults_start_data);
            TextView search_reults_end_data = findViewById(R.id.search_reults_end_data);
            TextView search_reults_malId = findViewById(R.id.search_reults_malId);
            TextView search_reults_title = findViewById(R.id.search_reults_title);
            TextView search_reults_type = findViewById(R.id.search_reults_type);
            TextView search_reults_syn = findViewById(R.id.search_reults_synopes);



            RatingBar score = findViewById(R.id.search_reults_score);
            ImageView search_reults_image = findViewById(R.id.search_reults_image);


            if (!mia.getImageUrl().equals("")){
                Picasso.get().load(mia.getImageUrl()).into(search_reults_image);
            }
            if (mia.getScore().equals("0")){
                score.setVisibility(RatingBar.GONE);
            }else{
                score.setRating(Float.parseFloat(mia.getScore()));

            }
            search_reults_malId.setText(String.format("Mal id: %s", mia.getMalId()));
            search_reults_airing.setText(String.format("Airing: %s", (mia.isAiring())));
            search_reults_episodes.setText(String.format("Episodes: %s", mia.getEpisodes()));
            search_reults_end_data.setText("Start Date: "+mia.getEndDate());
            search_reults_start_data.setText("End Date: "+mia.getStartDate());
            search_reults_title.setText(mia.getTitle());
            search_reults_type.setText("Type: "+mia.getType());
            search_reults_syn.setText("Synopsis: "+mia.getSynopsis());
        }

    }

}