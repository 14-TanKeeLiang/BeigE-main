package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    //The codes below is to create button & text functions for the buttons in the app work. In this case is the imagine button & edit text.
    ImageButton proceedBtn, backBtn;
    EditText username, email, password, cfmPassword;

    static boolean accountCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        proceedBtn = findViewById(R.id.proceedbtn);
        backBtn = findViewById(R.id.backbtn);
        username = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        cfmPassword = findViewById(R.id.editTextTextCfmPassword);


        proceedBtn.setOnClickListener(view -> {
            String name,mail,pass,cfmPass;

            name = username.getText().toString();
            mail = email.getText().toString();
            pass = password.getText().toString();
            cfmPass = cfmPassword.getText().toString();

            if(name.equals("")){
                Toast.makeText(this, "Username field is blank.", Toast.LENGTH_SHORT).show();
            }

            if(mail.equals("")){
                Toast.makeText(this, "Email field is blank.", Toast.LENGTH_SHORT).show();
            }

            if(pass.equals("")){
                Toast.makeText(this, "Password field is blank.", Toast.LENGTH_SHORT).show();
            }

            if(cfmPass.equals("")){
                Toast.makeText(this, "Confirm Password field is blank.", Toast.LENGTH_SHORT).show();
            }

            if(name.equals("Tan Kee Liang") &&
                    mail.equals("2204887J@student.tp.edu.sg") &&
                    pass.equals("123") &&
                    cfmPass.equals("123")){
                accountCreated = true;
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();
            }
            else {
                Toast.makeText(this, "Try again.", Toast.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpActivity.this, WelcomePage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
        finish();
    }

    public void goToLoginPage(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }
}