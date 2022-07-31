package com.example.beige;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {

    //The codes below is to create button functions for the buttons in the app work. In this case is just the button.
    Button loginBtn;
    Button signUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        loginBtn = findViewById(R.id.loginbtn);
        signUpBtn = findViewById(R.id.signupbtn);

        loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomePage.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            finish();
        });

        signUpBtn.setOnClickListener(view -> {
            Intent intent = new Intent(WelcomePage.this,SignUpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
