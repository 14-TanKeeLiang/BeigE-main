package com.example.beige;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //The codes below is to create button & text functions for the buttons in the app work. In this case is the imagine button & edit text.
    ImageButton proceedBtn, backBtn;
    EditText email, password;
    TextView doNotHaveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        proceedBtn = findViewById(R.id.proceedbtn);
        backBtn = findViewById(R.id.backbtn);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        doNotHaveAccount = findViewById(R.id.dont_have_account);

        proceedBtn.setOnClickListener(view -> {
            String mail, pass;

            mail = email.getText().toString();
            pass = password.getText().toString();
            if(SignUpActivity.accountCreated){
                if(mail.equals("2204887J@student.tp.edu.sg") &&
                        pass.equals("123")){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    finish();
                }
                else {
                    Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                    doNotHaveAccount.setVisibility(View.VISIBLE);
                }
            }
            else if(mail.equals("")){
                Toast.makeText(this, "Email field is blank.", Toast.LENGTH_SHORT).show();
                doNotHaveAccount.setVisibility(View.VISIBLE);
            }
            else if(pass.equals("")){
                Toast.makeText(this, "Password field is blank.", Toast.LENGTH_SHORT).show();
                doNotHaveAccount.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                doNotHaveAccount.setVisibility(View.VISIBLE);
            }

        });

        backBtn.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, WelcomePage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.side_to_right);
        finish();
    }

    //This on-Click function is to go to the signup page.
    public void goToSignUpPage(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        finish();
    }
}