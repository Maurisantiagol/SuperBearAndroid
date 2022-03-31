package com.example.superbearandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Signin(View view){
        Intent Signin = new Intent(this, SignIn.class);
        startActivity(Signin);
    }

    public void Signup(View view){
        Intent Signup = new Intent(this, SignUp.class);
        startActivity(Signup);
    }
}