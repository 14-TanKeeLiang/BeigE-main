package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SleepTimerActivity extends AppCompatActivity {



    @SuppressLint("StaticFieldLeak")
    public static TextView textViewCountDown;
    @SuppressLint("StaticFieldLeak")
    public static ImageView btnStartPause;
    @SuppressLint("StaticFieldLeak")
    public static ImageView btnReset;
    @SuppressLint("StaticFieldLeak")
    public static EditText editTextInput;
    public ImageView btnSet;

    public static CountDownTimer countDownTimer;

    public static boolean timerRunning;
    public static boolean outOfPage;

    public static long startTimeInMillis;
    public static long timeLeftInMills = startTimeInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_timer);

        textViewCountDown = findViewById(R.id.countdown_timer);
        btnStartPause = findViewById(R.id.btn_start_pause);
        btnReset = findViewById(R.id.btn_reset);
        btnSet = findViewById(R.id.btn_edit_set);
        editTextInput = findViewById(R.id.countdown_timer_edit);

        //This function is to set the minutes of the countdown timer.
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextInput.getText().toString();
                if(input.length() == 0){
                    Toast.makeText(SleepTimerActivity.this, "Field can't be empty.", Toast.LENGTH_SHORT).show();
                }

                long millisInput = Long.parseLong(input) * 60000;
                if(millisInput == 0){
                    Toast.makeText(SleepTimerActivity.this, "Please enter a positive number.", Toast.LENGTH_SHORT).show();
                }

                setTime(millisInput);
            }
        });

        //This function is to start or pause the countdown timer.
        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning){
                    pauseTimer();
                }
                else {
                    startTimer();
                }
            }
        });

        //This function is to reset the countdown timer.
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownBtns();
        updateCountDownText();
    }

    private void setTime(long milliseconds){
        startTimeInMillis = milliseconds;
        resetTimer();
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMills = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                if(outOfPage){
                    timerRunning = true;
                }
                else {
                    timerRunning = false;
                    btnStartPause.setImageResource(R.drawable.sleep_timer_start_btn);
                    btnStartPause.setVisibility(View.INVISIBLE);
                    btnReset.setVisibility(View.VISIBLE);
                }
            }
        }.start();

        timerRunning = true;
        btnStartPause.setImageResource(R.drawable.sleep_timer_pause_btn);
        btnReset.setVisibility(View.INVISIBLE);
        btnSet.setVisibility(View.INVISIBLE);
        editTextInput.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        btnStartPause.setImageResource(R.drawable.sleep_timer_start_btn);
        btnReset.setVisibility(View.VISIBLE);
        btnSet.setVisibility(View.VISIBLE);
        editTextInput.setVisibility(View.VISIBLE);
    }

    private static void resetTimer(){
        timeLeftInMills = startTimeInMillis;
        updateCountDownText();
        btnReset.setVisibility(View.INVISIBLE);
        btnStartPause.setVisibility(View.VISIBLE);
    }

    private static void updateCountDownText(){
        int hours = (int) (timeLeftInMills / 1000) / 3600;
        int minutes = (int) ((timeLeftInMills / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMills / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d",hours,minutes,seconds);
        textViewCountDown.setText(timeLeftFormatted);
    }

    private static void updateCountDownBtns(){
        if(timerRunning && timeLeftInMills <= 1000){
            timerRunning = false;
            btnStartPause.setImageResource(R.drawable.sleep_timer_start_btn);
            btnStartPause.setVisibility(View.INVISIBLE);
            btnReset.setVisibility(View.VISIBLE);
        }
        else if(timerRunning){
            btnStartPause.setImageResource(R.drawable.sleep_timer_pause_btn);
        }
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        outOfPage = true;
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
    }
}