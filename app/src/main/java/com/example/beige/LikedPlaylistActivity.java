package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LikedPlaylistActivity extends AppCompatActivity {

    static boolean userInPlaylist = false;

    RecyclerView likeList;
    SongAdapter songAdapter;

    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_playlist);

        userInPlaylist = true;
        TextView songLiked = findViewById(R.id.no_song_liked_txt);
        likeList = findViewById(R.id.recycler_view);
        songAdapter = new SongAdapter(PlaySongActivity.likedList);
        likeList.setAdapter(songAdapter);
        likeList.setLayoutManager(new LinearLayoutManager(this));

        if(PlaySongActivity.joji == 1 ||
                PlaySongActivity.twice == 1 ||
                PlaySongActivity.babyKeem == 1 ||
                PlaySongActivity.calvinHarris == 1 ||
                PlaySongActivity.yeat == 1 ||
                PlaySongActivity.lilUziVert == 1 ||
                PlaySongActivity.brunoMars == 1 ||
                PlaySongActivity.theKidLoaroi == 1 ||
                PlaySongActivity.yoasobi == 1 ||
                PlaySongActivity.travisScott == 1){
            songLiked.setVisibility(View.INVISIBLE);
        }
        else {
            songLiked.setVisibility(View.VISIBLE);
        }
    }

    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView){

        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        sendDataToActivity(currentArrayIndex);
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        userInPlaylist = false;
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
    }
}