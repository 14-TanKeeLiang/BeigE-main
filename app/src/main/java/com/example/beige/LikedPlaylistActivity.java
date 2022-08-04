package com.example.beige;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LikedPlaylistActivity extends AppCompatActivity {

    RecyclerView likeList;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_playlist);

        likeList = findViewById(R.id.recycler_view);
        songAdapter = new SongAdapter(PlaySongActivity.likedList);
        likeList.setAdapter(songAdapter);
        likeList.setLayoutManager(new LinearLayoutManager(this));

    }
}
