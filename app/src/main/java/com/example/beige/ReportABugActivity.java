package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ReportABugActivity extends AppCompatActivity {

    private EditText editFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_abug);

        editFeedBack = findViewById(R.id.edit_feedback_txt);
    }
    
    public void submitFeedBack(View view){
        if(editFeedBack.getText().toString().matches("")){
            Toast.makeText(this, "Enter your feedback.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Successfully submitted your feedback!", Toast.LENGTH_SHORT).show();
            editFeedBack.setText("");
        }
    }

    public void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
    }
}