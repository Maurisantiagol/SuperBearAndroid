package com.example.superbearandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.superbearandroid.control.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Task().execute();

    }

    public void Signin(View view){
        Intent Signin = new Intent(this, SignIn.class);
        startActivity(Signin);
    }

    public void Signup(View view){
        Intent Signup = new Intent(this, SignUp.class);
        startActivity(Signup);
    }
    class Task extends AsyncTask<Void, Void, Void> {
        String error="",records="";
        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Connection connection= bd.getConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("Select * from musuario");

                while(resultSet.next()){
                    records+= resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3)+"\n";
                }
            } catch (Exception e) {
                error =e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println(records);
            if (error != "")
                System.out.println(error);
            super.onPostExecute (aVoid);
        }
    }

}