package com.example.beige;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchViewActivity extends RecyclerView.ViewHolder {

    public TextView songTitle;
    public TextView songArtist;
    public ImageView songCoverImage;

    public SearchViewActivity(@NonNull View itemView) {
        super(itemView);

        songTitle = itemView.findViewById(R.id.song_title);
        songArtist = itemView.findViewById(R.id.song_artist);
        songCoverImage = itemView.findViewById(R.id.song_cover_image);
    }
}