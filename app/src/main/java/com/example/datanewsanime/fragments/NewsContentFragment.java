package com.example.datanewsanime.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.jetbrains.annotations.NotNull;


public class NewsContentFragment extends Fragment {
    private MalInfoAnime mia;
    String searchAnimeTitle = "https://api.jikan.moe/v3/search/anime?q=";
    String searchAnimeTitleId = "https://api.jikan.moe/v3/anime/";

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_news_content,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        boolean hasExtra = getIntent().hasExtra("content_malNextSeason");
        if (hasExtra){
            MalNextSeason malNextSeason = (MalNextSeason) getIntent().getSerializableExtra("content_malNextSeason");
            setFieldsNewsContent(malNextSeason);
            NewsContent.TaskMalConnection task = new NewsContent.TaskMalConnection();
            task.execute(searchAnimeTitle+malNextSeason.getTitle());
            NewsContent.TaskMalNewsConnection taskNews = new NewsContent.TaskMalNewsConnection();
            taskNews.execute(searchAnimeTitleId+malNextSeason.getMalId()+"/news");
        }*/

    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
    }
    */


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
            TextView news_content_airing = getActivity().findViewById(R.id.content_data_airing);
            TextView news_content_episodes = getActivity().findViewById(R.id.content_data_episodes);
            TextView news_content_start_data = getActivity().findViewById(R.id.content_data_start_data);
            TextView news_content_end_data = getActivity().findViewById(R.id.content_data_end_data);
            TextView news_content_malId = getActivity().findViewById(R.id.content_data_malId);
            RatingBar score = getActivity().findViewById(R.id.score);

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

    @SuppressLint("StaticFieldLeak")
    private class TaskMalNewsConnection extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return ConnectionAPIs.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            MalNews mn = HandleJSON.getMalNews(s);

            setFieldsMalNews(mn);


        }

        private void setFieldsMalNews(MalNews malNews) {
            TextView news_content_news_tittle = getActivity().findViewById(R.id.content_data_news_title);
            TextView news_content_news_preview = getActivity().findViewById(R.id.content_data_news_preview);
            TextView news_content_news_date = getActivity().findViewById(R.id.content_data_news_date);
            ImageView news_content_news_image = getActivity().findViewById(R.id.content_data_news_image);
            if (!malNews.getImageUrl().equals("")){
                Picasso.get().load(malNews.getImageUrl()).into(news_content_news_image);
            }
            news_content_news_tittle.setText(String.format("Last News:\n%s", malNews.getTitle()));
            news_content_news_preview.setText(malNews.getIntro());
            news_content_news_date.setText(String.format("Published at: %s", malNews.getDate()));

        }


    }

    private void setFieldsNewsContent(MalNextSeason malNextSeason) {
        TextView news_content_tittle = getActivity().findViewById(R.id.content_data_title);
        TextView news_content_type = getActivity().findViewById(R.id.content_data_type);
        TextView news_content_synopsis = getActivity().findViewById(R.id.content_data_synopes);
        TextView news_content_source = getActivity().findViewById(R.id.content_data_source);
        TextView news_content_members = getActivity().findViewById(R.id.content_data_members);
        TextView news_content_geners = getActivity().findViewById(R.id.content_data_geners);
        TextView news_content_producers = getActivity().findViewById(R.id.content_data_producers);
        ImageView news_content_image = getActivity().findViewById(R.id.content_data_image);

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