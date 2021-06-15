package com.example.datanewsanime.recyclerview.adapter;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datanewsanime.R;
import com.example.datanewsanime.handlexml.NewsData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter <NewsDataAdapter.ViewHolder> {
    private final Context context;

    public NewsDataAdapter(Context context) {
        this.context = context;
    }

    public void refresh (List<NewsData> listNewsData){
        notifyItemRangeRemoved(0, this.feed.size());
        this.feed.clear();
        this.feed.addAll(listNewsData);
        notifyItemRangeInserted(0, this.feed.size());
    }


    private final List<NewsData> feed = new ArrayList<>();
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context)
                .inflate(R.layout.item_news_data, parent, false);
        return new ViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        NewsData newsData = feed.get(position);
        holder.bind(newsData);
    }

    @Override
    public int getItemCount() {
        return feed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image;
        private NewsData newsData;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_news_data_title);
            image = itemView.findViewById(R.id.item_news_data_image);


        }



        public void bind (NewsData newsData){
            this.newsData = newsData;
            title.setText(newsData.getName());
        }
    }
}
