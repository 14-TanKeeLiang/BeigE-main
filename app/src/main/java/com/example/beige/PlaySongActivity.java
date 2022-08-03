package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class PlaySongActivity extends AppCompatActivity {

    private String title;
    private String artiste;
    private String fileLink;
    private double songLength;
    private int drawable;
    public static int currentIndex = -1;

    //This section is for liked indicator 0 = not liked 1 = liked.
    private static int joji = 0;
    private static int twice = 0;
    private static int babyKeem = 0;
    private static int calvinHarris = 0;
    private static int yeat = 0;
    private static int lilUziVert = 0;
    private static int brunoMars = 0;
    private static int theKidLoaroi = 0;
    private static int yoasobi = 0;
    private static int travisScott = 0;

    static ArrayList<Song> likedList = new ArrayList<Song>();

    private MediaPlayer player = new MediaPlayer();
    private ImageView btnPlayPause;
    private SongCollection songCollection = new SongCollection();
    private ImageView likeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        likeBtn = findViewById(R.id.liked_song_btn);
        btnPlayPause = findViewById(R.id.btnPlayPause);

        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        Log.d("temasek", "we rx : " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        likedSongs();
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
        likedSongs();
    }

    public void playPrevious(View view) {
        currentIndex = songCollection.getPrevSong(currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(fileLink);
        likedSongs();
    }

    //This on-Click function is to change the song like and unlike btn.
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
        else if(currentIndex == 0 && joji == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            joji = 0;
        }
        else if(currentIndex == 1 && twice == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            twice = 0;
        }
        else if(currentIndex == 2 && babyKeem == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            babyKeem = 0;
        }
        else if(currentIndex == 3 && calvinHarris == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            calvinHarris = 0;
        }
        else if(currentIndex == 4 && yeat == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            yeat = 0;
        }
        else if(currentIndex == 5 && lilUziVert == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            lilUziVert = 0;
        }
        else if(currentIndex == 6 && brunoMars == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            brunoMars = 0;
        }
        else if(currentIndex == 7 && theKidLoaroi == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            theKidLoaroi = 0;
        }
        else if(currentIndex == 8 && yoasobi == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            yoasobi = 0;
        }
        else if(currentIndex == 9 && travisScott == 1){
            likeBtn.setImageResource(R.drawable.heart1_btn);
            travisScott = 0;
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
        super.onBackPressed();
        player.release();
    }
}