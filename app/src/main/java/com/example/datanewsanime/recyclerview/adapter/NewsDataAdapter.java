package com.example.datanewsanime.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datanewsanime.R;
import com.example.datanewsanime.models.MalNextSeason;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.ViewHolder> {
    private final Context context;
    private final List<MalNextSeason> feed = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener (OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public NewsDataAdapter(Context context) {
        this.context = context;
    }

    public void refresh(List<MalNextSeason> listNewsData) {
        notifyItemRangeRemoved(0, this.feed.size());
        this.feed.clear();
        this.feed.addAll(listNewsData);
        notifyItemRangeInserted(0, this.feed.size());
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_data, parent, false);
        return new ViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        MalNextSeason newsData = feed.get(position);
        holder.bind(newsData);
    }

    @Override
    public int getItemCount() {
        return feed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView image;
        private final TextView preview;
        private final TextView type;
        private MalNextSeason malNextSeason;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_news_data_title);
            image = itemView.findViewById(R.id.item_news_data_image);
            preview = itemView.findViewById(R.id.item_news_data_preview);
            type = itemView.findViewById(R.id.item_news_data_type);
            itemView.setOnClickListener(v ->{
                if (malNextSeason !=null){
                    onItemClickListener.onItemClick(malNextSeason);
                }
            });
        }

        public void bind(MalNextSeason malNextSeason) {
            this.malNextSeason = malNextSeason;
            title.setText(malNextSeason.getTitle());
            type.setText(malNextSeason.getType());
            preview.setText(malNextSeason.getSynopsis());

            if (!malNextSeason.getImageUrl().equals("")){
                Picasso.get().load(malNextSeason.getImageUrl()).into(image);
            }
        }

    }

    public interface OnItemClickListener{
        void onItemClick(MalNextSeason malNextSeason);

    }

}
