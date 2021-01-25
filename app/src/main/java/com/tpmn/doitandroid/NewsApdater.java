package com.tpmn.doitandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsApdater extends RecyclerView.Adapter<ArticleViewHolder> {

    List<NewsItemModel> list = new ArrayList<>();

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDataAndRefresh(List<NewsItemModel> news) {
       this.list = news;
       notifyDataSetChanged();
    }
}

class ArticleViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView titleTextView;
    TextView descTextView;
    Context context;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.imageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        descTextView = itemView.findViewById(R.id.descTextView);
    }

    public void bind(NewsItemModel article) {
        Glide.with(context).load(article.getUrlToImage()).into(imageView);
        titleTextView.setText(article.getTitle());
        descTextView.setText(article.getDescription());
    }
}
