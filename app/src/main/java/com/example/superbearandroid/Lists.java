package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Lists extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists);
    }

    public void volver(View view){
        Intent volver = new Intent(this, Groups.class);
        startActivity(volver);
    }
    public void Seguir(View view){
        Intent seguir = new Intent(this, Product.class);
        startActivity(seguir);
    }

}
