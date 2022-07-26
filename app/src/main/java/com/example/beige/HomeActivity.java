package com.example.beige;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;



public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Setting up all the navigation functions
        toolbar = findViewById(R.id.topToolBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_navi_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //This code below is to set home screen to be seen everytime the user enters/login/signup the app.
        replaceFragment(new homeFragment());

        //This code below is to have a ToolBar navigation on the header of the app and have a menu to open up the sidebar.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //The function below is when user press any of the icons in the bottom navigation bar it will prompt up the fragment that the icon is assigned to.
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.homeFragment:
                    replaceFragment(new homeFragment());
                    LikedPlaylistActivity.userInPlaylist = false;
                    break;
                case R.id.playlistFragment:
                    replaceFragment(new playlistFragment());
                    LikedPlaylistActivity.userInPlaylist = false;
                    break;
                case R.id.searchFragment:
                    replaceFragment(new searchFragment());
                    LikedPlaylistActivity.userInPlaylist = true;
                    break;
                case R.id.profileFragment:
                    replaceFragment(new profileFragment());
                    LikedPlaylistActivity.userInPlaylist = false;
                    break;

            }
            return true;
        });

        //The function below is when user press any of the icons in the top navigation bar it will prompt up the fragment that the icon is assigned to.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){

                    case R.id.settingFragment:
                        Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;

                    case R.id.logout:
                        onBackPressed();
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });

        songCollection.getSearchList();
    }

    //The method below is to replace the fragment when user taps on one of the icons at the bottom of the navigation bar.
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }

    //This section below is to logout user if they choose to do so.
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Do you want to Log out?");
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                songCollection.getSearchListRemove();
                Intent intent = new Intent(HomeActivity.this, WelcomePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //This section below is to bring user to the play song activity page.
    public void handleSelection(View myView){

        String resourceId = getResources().getResourceEntryName(myView.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        sendDataToActivity(currentArrayIndex);
    }

    public void sendDataToActivity(int index){
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    //This section below is to bring user to the like playlist activity page.
    public void goToLikedPlaylist(View view){
        Intent intent = new Intent(this, LikedPlaylistActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    //This section below is to tell user that this function has not been added.
    public void notFeatured(View view){
        Toast.makeText(this, "This feature have not been added.", Toast.LENGTH_SHORT).show();
    }


}