package com.example.datanewsanime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datanewsanime.models.MalNextSeason;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class NewsContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        boolean hasExtra = getIntent().hasExtra("content_malNextSeason");
        if (hasExtra){
            MalNextSeason malNextSeason = (MalNextSeason) getIntent().getSerializableExtra("content_malNextSeason");
            setFieldsNewsContent(malNextSeason);
        }

    }

    private void setFieldsNewsContent(MalNextSeason malNextSeason) {
        TextView news_content_tittle = findViewById(R.id.content_data_title);
        TextView news_content_type = findViewById(R.id.content_data_type);
        news_content_tittle.setText(malNextSeason.getTitle());
        news_content_type.setText(malNextSeason.getType());
        ImageView news_content_image = findViewById(R.id.content_data_image);
        if (malNextSeason.getImageUrl() != ""){
            Picasso.get().load(malNextSeason.getImageUrl()).into(news_content_image);
        }
    }
}