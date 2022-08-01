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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.beige.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    SongCollection songCollection = new SongCollection();

    static ArrayList<Song> playList = new ArrayList<Song>();

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

        //The function below is when user press any of the icons in the top navigation bar it will prompt up the fragment that the icon is assigned to.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){

                    case R.id.settingFragment:
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
    }

    //The method below is to replace the fragment when user taps on one of the icons at the bottom of the navigation bar.
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Do you want to Log out?");
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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

    //Bottom function is to create a playlist when user pressed
    public void createPlaylist(View view){


    }
}