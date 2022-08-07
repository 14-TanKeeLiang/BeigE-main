package com.example.beige;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {

    private String title;
    public String artiste;
    private String fileLink;
    private boolean repeatFlag = false;
    private boolean shuffleFlag = false;
    private double songLength;
    public int drawable;
    public static int currentIndex = -1;

    //This section is for liked indicator 0 = not liked 1 = liked.
    static int joji = 0;
    static int twice = 0;
    static int babyKeem = 0;
    static int calvinHarris = 0;
    static int yeat = 0;
    static int lilUziVert = 0;
    static int brunoMars = 0;
    static int theKidLoaroi = 0;
    static int yoasobi = 0;
    static int travisScott = 0;

    public MediaPlayer player = new MediaPlayer();
    public SongCollection songCollection = new SongCollection();
    public SongCollection originalSongCollection = new SongCollection();
    private ImageView btnPlayPause;
    private ImageView likeBtn;
    private ImageView repeatBtn;
    private ImageView shuffleBtn;
    SeekBar seekBar;
    Handler handler = new Handler();

    //This section is to create list arrays.
    static ArrayList<Song> likedList = new ArrayList<Song>();
    List<Song> shuffleList = Arrays.asList(songCollection.songs);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        likeBtn = findViewById(R.id.liked_song_btn);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        repeatBtn = findViewById(R.id.btnRepeat);
        shuffleBtn = findViewById(R.id.btnShuffle);
        seekBar = findViewById(R.id.seekBar);

        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");

        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        likedSongs();
        sleepTimerIsUp();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(player != null && player.isPlaying()){
                    player.seekTo(seekBar.getProgress());
                }
            }
        });
    }

    //This function is to have a loop timer for the seekbar.
    Runnable p_bar = new Runnable() {
        @Override
        public void run() {
            if(player != null && player.isPlaying()){
                seekBar.setProgress(player.getCurrentPosition());
                handler.postDelayed(this, 1000);
            }
        }
    };

    //This function is to set the UIs in the activity.
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

    //This function is to play the current song.
    public void playSong(String songUrl){
        try {
            player.reset();
            player.setDataSource(songUrl);
            player.prepare();
            player.start();
            seekBar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
            btnPlayPause.setImageResource(R.drawable.pause_btn);
            setTitle(title);
            graceFullyStopWhenMusicEnds();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //This on-Click function is to play or pause the current song.
    public void playOrPauseMusic(View view) {
        if(player.isPlaying()){
            sleepTimerIsUp();
            player.pause();
            handler.removeCallbacks(p_bar);
            btnPlayPause.setImageResource(R.drawable.play_btn);
        }
        else {
            sleepTimerIsUp();
            player.start();
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
            btnPlayPause.setImageResource(R.drawable.pause_btn);
        }
    }

    //This function is to stop the stop the song when it reaches to the end.
    private void graceFullyStopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(repeatFlag){
                    playOrPauseMusic(null);
                }
                else {
                    btnPlayPause.setImageResource(R.drawable.play_btn);
                }
            }
        });
    }

    //This function is to tell the user to reset their sleep timer in order to play their song.
    private void sleepTimerIsUp(){
        if(!SleepTimerActivity.timerRunning && SleepTimerActivity.timeLeftInMills <= 1000){
            player.pause();
            AlertDialog.Builder builder = new AlertDialog.Builder(PlaySongActivity.this);
            builder.setMessage("Your sleepTimer is up, go to reset the timer.");
            builder.setCancelable(false);

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onBackPressed();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    //This on-Click function is to go to the next song.
    public void playNext(View view) {
        sleepTimerIsUp();
        currentIndex = songCollection.getNextSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        likedSongs();
    }

    //This on-Click function is to go to the previous song.
    public void playPrevious(View view) {
        sleepTimerIsUp();
        currentIndex = songCollection.getPrevSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        likedSongs();
    }

    //This on-Click function is to repeat the song.
    public void repeatSong(View view) {
        if(repeatFlag)
        {
            repeatBtn.setImageResource(R.drawable.repeat_off);
        }
        else
        {
            repeatBtn.setImageResource(R.drawable.repeat_on);
        }
        repeatFlag = !repeatFlag;
    }

    public void shuffleSong(View view) {
        if(shuffleFlag)
        {
            shuffleBtn.setImageResource(R.drawable.shuffle_off);
            songCollection = new SongCollection();
        }
        else
        {
            shuffleBtn.setImageResource(R.drawable.shuffle_on);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffleFlag = !shuffleFlag;
    }

    //This on-Click function is to change the song like and unlike btn.
    @SuppressLint("NotifyDataSetChanged")
    public void likeBtn(View view) {

        if(currentIndex == 0 && joji == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            joji = 1;
        }
        else if(currentIndex == 1 && twice == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            twice = 1;
        }
        else if(currentIndex == 2 && babyKeem == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            babyKeem = 1;
        }
        else if(currentIndex == 3 && calvinHarris == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            calvinHarris = 1;
        }
        else if(currentIndex == 4 && yeat == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            yeat = 1;
        }
        else if(currentIndex == 5 && lilUziVert == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            lilUziVert = 1;
        }
        else if(currentIndex == 6 && brunoMars == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            brunoMars = 1;
        }
        else if(currentIndex == 7 && theKidLoaroi == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            theKidLoaroi = 1;
        }
        else if(currentIndex == 8 && yoasobi == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            yoasobi = 1;
        }
        else if(currentIndex == 9 && travisScott == 0){
            likeBtn.setImageResource(R.drawable.heart2_btn);
            Song song = songCollection.getCurrentSong(currentIndex);
            likedList.add(song);
            travisScott = 1;
        }
        else if(currentIndex == 0 && joji == 1 ||
                currentIndex == 1 && twice == 1 ||
                currentIndex == 2 && babyKeem == 1 ||
                currentIndex == 3 && calvinHarris == 1 ||
                currentIndex == 4 && yeat == 1 ||
                currentIndex == 5 && lilUziVert == 1 ||
                currentIndex == 6 && brunoMars == 1 ||
                currentIndex == 7 && theKidLoaroi == 1 ||
                currentIndex == 8 && yoasobi == 1 ||
                currentIndex == 9 && travisScott == 1){
            Toast.makeText(this, "Please proceed to Like Playlist to remove song.", Toast.LENGTH_SHORT).show();
        }
    }

    //This on-Click function is to make instant change if user pressed next or prev buttons and returning to PlaySongActivity page.
    public void likedSongs(){
        if(currentIndex == 0 && joji == 1 ||
                currentIndex == 1 && twice == 1 ||
                currentIndex == 2 && babyKeem == 1 ||
                currentIndex == 3 && calvinHarris == 1 ||
                currentIndex == 4 && yeat == 1 ||
                currentIndex == 5 && lilUziVert == 1 ||
                currentIndex == 6 && brunoMars == 1 ||
                currentIndex == 7 && theKidLoaroi == 1 ||
                currentIndex == 8 && yoasobi == 1 ||
                currentIndex == 9 && travisScott == 1){
            likeBtn.setImageResource(R.drawable.heart2_btn);
        }
        else {
            likeBtn.setImageResource(R.drawable.heart1_btn);
        }
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(p_bar);
        super.onBackPressed();
        player.release();
    }
}