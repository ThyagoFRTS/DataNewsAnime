package com.example.datanewsanime.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.datanewsanime.R;
import com.example.datanewsanime.connectionapis.ConnectionAPIs;
import com.example.datanewsanime.handlejson.HandleJSON;
import com.example.datanewsanime.models.MalInfoAnime;
import com.example.datanewsanime.models.MalNews;
import com.example.datanewsanime.models.MalNextSeason;
import com.squareup.picasso.Picasso;



public class NewsContent extends AppCompatActivity {
    private MalInfoAnime mia;
    String searchAnimeTitle = "https://api.jikan.moe/v3/search/anime?q=";
    String searchAnimeTitleId = "https://api.jikan.moe/v3/anime/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        boolean hasExtraFromRecyclerView = getIntent().hasExtra("content_malNextSeason");
        if (hasExtraFromRecyclerView){
            MalNextSeason malNextSeason = (MalNextSeason) getIntent().getSerializableExtra("content_malNextSeason");
            setFieldsNewsContent(malNextSeason);
            NewsContent.TaskMalConnection task = new NewsContent.TaskMalConnection();
            task.execute(searchAnimeTitle+malNextSeason.getTitle());
            NewsContent.TaskMalNewsConnection taskNews = new NewsContent.TaskMalNewsConnection();
            taskNews.execute(searchAnimeTitleId+malNextSeason.getMalId()+"/news");
        }
        boolean hasExtraFromSearchView = getIntent().hasExtra("query_anime_tilte");
        if (hasExtraFromSearchView){

            System.out.println(getIntent().getSerializableExtra("query_anime_tilte").toString());
            String title = getIntent().getSerializableExtra("query_anime_tilte").toString();
            NewsContent.TaskMalConnection task = new NewsContent.TaskMalConnection();
            task.execute(searchAnimeTitle+title);





        }
    }











    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    private class TaskMalConnection extends AsyncTask<String,String,String> {

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
            TextView news_content_airing = findViewById(R.id.content_data_airing);
            TextView news_content_episodes = findViewById(R.id.content_data_episodes);
            TextView news_content_start_data = findViewById(R.id.content_data_start_data);
            TextView news_content_end_data = findViewById(R.id.content_data_end_data);
            TextView news_content_malId = findViewById(R.id.content_data_malId);
            RatingBar score = findViewById(R.id.score);

            if (mia.getScore().equals("0")){
                score.setVisibility(RatingBar.GONE);
            }else{
                score.setRating(Float.parseFloat(mia.getScore()));

            }
            news_content_malId.setText(String.format("Mal id: %s", mia.getMalId()));
            news_content_airing.setText(String.format("Airing: %s", (mia.isAiring())));
            news_content_episodes.setText(String.format("Episodes: %s", mia.getEpisodes()));
            news_content_end_data.setText("Start Date: "+mia.getEndDate());
            news_content_start_data.setText("End Date: "+mia.getStartDate());

        }

    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    private class TaskMalNewsConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return ConnectionAPIs.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            MalNews mn = HandleJSON.getMalNews(s);
            assert mn != null;
            setFieldsMalNews(mn);
        }

        private void setFieldsMalNews(MalNews malNews) {
            TextView news_content_news_tittle = findViewById(R.id.content_data_news_title);
            TextView news_content_news_preview = findViewById(R.id.content_data_news_preview);
            TextView news_content_news_date = findViewById(R.id.content_data_news_date);
            ImageView news_content_news_image = findViewById(R.id.content_data_news_image);
            if (!malNews.getImageUrl().equals("")){
                Picasso.get().load(malNews.getImageUrl()).into(news_content_news_image);
            }
            news_content_news_tittle.setText(String.format("Last News:\n%s", malNews.getTitle()));
            news_content_news_preview.setText(malNews.getIntro());
            news_content_news_date.setText(String.format("Published at: %s", malNews.getDate()));


        }


    }

    private void setFieldsNewsContent(MalNextSeason malNextSeason) {
        TextView news_content_tittle = findViewById(R.id.content_data_title);
        TextView news_content_type = findViewById(R.id.content_data_type);
        TextView news_content_synopsis = findViewById(R.id.content_data_synopes);
        TextView news_content_source = findViewById(R.id.content_data_source);
        TextView news_content_members = findViewById(R.id.content_data_members);
        TextView news_content_geners = findViewById(R.id.content_data_geners);
        TextView news_content_producers = findViewById(R.id.content_data_producers);
        ImageView news_content_image = findViewById(R.id.content_data_image);

        if (!malNextSeason.getProducers().isEmpty()) {
            news_content_producers.setText(String.format("Producers: %s", malNextSeason.getProducers().toString().replace("[", "").replace("]", "")));
        }else{
            news_content_producers.setVisibility(TextView.GONE);
        }
        if (!malNextSeason.getGenres().isEmpty()) {
            news_content_geners.setText(String.format("Geners: %s", malNextSeason.getGenres().toString().replace("[", "").replace("]", "")));
        }else{
            news_content_geners.setVisibility(TextView.GONE);
        }
        if (!malNextSeason.getImageUrl().equals("")){
            Picasso.get().load(malNextSeason.getImageUrl()).into(news_content_image);
        }
        news_content_members.setText(String.format("Member's Number: %s", malNextSeason.getMembers()));
        news_content_source.setText(String.format("Source: %s", malNextSeason.getSource()));
        news_content_synopsis.setText(String.format("Synopsis: %s", malNextSeason.getSynopsis()));
        news_content_tittle.setText(malNextSeason.getTitle());
        news_content_type.setText(malNextSeason.getType());
    }
}