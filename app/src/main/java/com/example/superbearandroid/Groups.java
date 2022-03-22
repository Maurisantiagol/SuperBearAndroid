package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Groups extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups);
    }

    public void volver(View view){
        Intent volver = new Intent(this, SignIn.class);
        startActivity(volver);
    }
    public void seguir(View view){
        Intent seguir = new Intent(this, Lists.class);
        startActivity(seguir);
    }
}
