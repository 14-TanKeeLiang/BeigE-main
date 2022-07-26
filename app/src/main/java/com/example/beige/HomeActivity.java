package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.beige.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //I have to
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //this code below is to set home screen to be seen everytime the user enters/login/signup the app.
        replaceFragment(new homeFragment());

        //the function below is when user press any of the icons in the navigation bar it will prompt up the fragment that the icon is assigned to.
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.homeFragment:
                    replaceFragment(new homeFragment());
                    break;
                case R.id.playlistFragment:
                    replaceFragment(new playlistFragment());
                    break;
                case R.id.searchFragment:
                    replaceFragment(new searchFragment());
                    break;
                case R.id.profileFragment:
                    replaceFragment(new profileFragment());
                    break;

            }

            return true;
        });
    }

    //The method below is to replace the fragment when user taps on one of the icons at the bottom of the navigation bar.
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
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