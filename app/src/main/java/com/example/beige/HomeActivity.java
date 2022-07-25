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