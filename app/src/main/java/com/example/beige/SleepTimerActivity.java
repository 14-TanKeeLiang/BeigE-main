package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class SleepTimerActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLS = 8000;

    @SuppressLint("StaticFieldLeak")
    private static TextView textViewCountDown;
    @SuppressLint("StaticFieldLeak")
    private static ImageView btnStartPause;
    @SuppressLint("StaticFieldLeak")
    private static ImageView btnReset;

    private static CountDownTimer countDownTimer;

    private static boolean timerRunning;
    private static boolean outOfPage;

    private static long timeLeftInMills = START_TIME_IN_MILLS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_timer);

        textViewCountDown = findViewById(R.id.countdown_timer);
        btnStartPause = findViewById(R.id.btn_start_pause);
        btnReset = findViewById(R.id.btn_reset);

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

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownBtns();
        updateCountDownText();
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
    }

    private void pauseTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        btnStartPause.setImageResource(R.drawable.sleep_timer_start_btn);
        btnReset.setVisibility(View.VISIBLE);
    }

    private static void resetTimer(){
        timeLeftInMills = START_TIME_IN_MILLS;
        updateCountDownText();
        btnReset.setVisibility(View.INVISIBLE);
        btnStartPause.setVisibility(View.VISIBLE);
    }

    private static void updateCountDownText(){
        int minutes = (int) (timeLeftInMills / 1000) / 60;
        int seconds = (int) (timeLeftInMills / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
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
        Log.d("timer", "backBtn: " + timerRunning);
    }

    @Override
    public void onBackPressed() {
        outOfPage = true;
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
    }
}