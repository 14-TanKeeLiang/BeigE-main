package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    //The codes below is to create button functions for the buttons in the app work. In this case is the image button.
    //ImageButton playlistBtn;

    //This code is for finding the imageview id.
    //ImageView backGroundPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*playlistBtn = findViewById(R.id.playlistbtn);
        backGroundPopup = findViewById(R.id.backgroundpopup);

        //This code below will help make transition of activity much smoother.
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(androidx.appcompat.R.id.action_bar_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        playlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PlaylistActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, backGroundPopup, Objects.requireNonNull(ViewCompat.getTransitionName(backGroundPopup)));
                startActivity(intent, options.toBundle());
            }
        });
        */
    }

    SongCollection songCollection = new SongCollection();

    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View myView){

        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("temaesk","The id of the pressed ImageButton is : " + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }
}