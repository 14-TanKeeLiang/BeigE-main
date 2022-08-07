package com.example.beige;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<LikedPlaylistView>{

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
        View songView = inflater.inflate(R.layout.activity_songs_in_playlist, parent, false);

        LikedPlaylistView likedHolder = new LikedPlaylistView(songView);

        return likedHolder;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull LikedPlaylistView holder, @SuppressLint("RecyclerView") int position) {
        Song song = songs.get(position);

        TextView title = holder.songTitle;
        title.setText(song.getTitle());

        TextView artist = holder.songArtist;
        artist.setText(song.getArtiste());

        ImageView songCoverImage = holder.songCoverImage;
        songCoverImage.setImageResource(song.getDrawable());

        ImageView removeLike = holder.removeLike;
        removeLike.setOnClickListener(view -> {
            if(song.getId().equals("joji")){
                PlaySongActivity.joji = 0;
            }
            if(song.getId().equals("twice")){
                PlaySongActivity.twice = 0;
            }
            if(song.getId().equals("baby_keem")){
                PlaySongActivity.babyKeem = 0;
            }
            if(song.getId().equals("calvin_harris")){
                PlaySongActivity.calvinHarris = 0;
            }
            if(song.getId().equals("yeat")){
                PlaySongActivity.yeat = 0;
            }
            if(song.getId().equals("lil_uzi_vert")){
                PlaySongActivity.lilUziVert = 0;
            }
            if(song.getId().equals("bruno_mars")){
                PlaySongActivity.brunoMars = 0;
            }
            if(song.getId().equals("the_kid_loaroi")){
                PlaySongActivity.theKidLoaroi = 0;
            }
            if(song.getId().equals("yoasobi")){
                PlaySongActivity.yoasobi = 0;
            }
            if(song.getId().equals("travis_scott")){
                PlaySongActivity.travisScott = 0;
            }
            PlaySongActivity.likedList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
