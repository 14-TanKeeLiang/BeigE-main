package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PlaySongActivity extends AppCompatActivity {

    private String title;
    private String artiste;
    private String fileLink;
    private double songLength;
    private int drawable;
    private int currentIndex = -1;

    private MediaPlayer player = new MediaPlayer();
    private ImageView btnPlayPause;
    private ImageView btnBack;
    private SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnBack = findViewById(R.id.backbtn);

        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek", "we rx : " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void displaySongBasedOnIndex(int selectedIndex){
        Song song = songCollection.getCurrentSong(selectedIndex);

        title = song.getTitle();

        title = song.getTitle();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        drawable = song.getDrawable();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artiste);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        iCoverArt.setImageResource(drawable);
    }

    public void playSong(String songUrl){

        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            btnPlayPause.setImageResource(R.drawable.pause_btn);
            setTitle(title);
            graceFullyStopWhenMusicEnds();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void playOrPauseMusic(View view) {
        if(player.isPlaying()){
            player.pause();
            btnPlayPause.setImageResource(R.drawable.play_btn);
        }
        else {
            player.start();
            btnPlayPause.setImageResource(R.drawable.pause_btn);
        }
    }

    private void graceFullyStopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnPlayPause.setImageResource(R.drawable.play_btn);
            }
        });
    }

    public void playNext(View view) {
        currentIndex = songCollection.getNextSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.release();
    }
}