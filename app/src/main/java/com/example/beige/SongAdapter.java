package com.example.beige;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<LikedPlaylistView> {

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }

    List<Song> songs;
    Context context;

    @NonNull
    @Override
    public LikedPlaylistView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.activity_liked_playlist, parent, false);

        LikedPlaylistView likedHolder = new LikedPlaylistView(songView);

        return likedHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LikedPlaylistView holder, int position) {
        Song song = songs.get(position);

        TextView title = holder.songTitle;
        title.setText(song.getTitle());

        TextView artist = holder.songArtist;
        artist.setText(song.getArtiste());

        int imageId = song.getDrawable();
        holder.songCoverImage.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
