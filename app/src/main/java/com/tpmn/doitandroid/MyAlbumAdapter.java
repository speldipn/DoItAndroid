package com.tpmn.doitandroid;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tpmn.doitandroid.MyAlbumAdapter.AlbumViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    List<AlbumModel> list = new ArrayList<>();

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() { return list.size(); }

    public void setData(List<AlbumModel> list) {
       this.list = list;
       notifyDataSetChanged();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbImageView;
        TextView nameTextView;
        TextView dateTextView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbImageView = itemView.findViewById(R.id.thumbImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        public void bind(AlbumModel model) {
            thumbImageView.setImageURI(model.thumbUri);
            nameTextView.setText(model.name);
            dateTextView.setText(getDate(model.time));
        }

        private String getDate(Long date) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            return sdf.format(new Date(date));
        }
    }
}

class AlbumModel {
    Uri thumbUri;
    String name;
    Long time;
}
