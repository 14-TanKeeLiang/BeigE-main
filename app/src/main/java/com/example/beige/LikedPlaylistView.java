package com.example.beige;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LikedPlaylistView extends RecyclerView.ViewHolder {

    public TextView songTitle;
    public TextView songArtist;
    public ImageView songCoverImage;
    public ImageView removeLike;

    public LikedPlaylistView(@NonNull View itemView) {
        super(itemView);

        songTitle = itemView.findViewById(R.id.song_title);
        songArtist = itemView.findViewById(R.id.song_artist);
        songCoverImage = itemView.findViewById(R.id.song_cover_image);
        removeLike = itemView.findViewById(R.id.remove_like);
    }
}