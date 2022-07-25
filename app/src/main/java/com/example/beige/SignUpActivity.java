package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class SignUpActivity extends AppCompatActivity {

    //The codes below is to create button functions for the buttons in the app work. In this case is the imagine button.
    ImageButton proceedBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        proceedBtn = findViewById(R.id.proceedbtn);
        backBtn = findViewById(R.id.backbtn);

        proceedBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        backBtn.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
    }
}