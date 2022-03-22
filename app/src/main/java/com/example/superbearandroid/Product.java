package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
    }

    public void volver(View view){
        Intent volver = new Intent(this, Lists.class);
        startActivity(volver);
    }
}
