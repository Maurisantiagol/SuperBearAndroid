package com.example.superbearandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.superbearandroid.control.bd;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    private static  final String FILE_NAME = "NoAbrir.txt";

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

    public void Lists(View view){
        Intent Lists = new Intent(this, Lists.class);
        startActivity(Lists);
    }
    public void Product(View view){
        Intent Product = new Intent(this, Product.class);
        startActivity(Product);
    }


    class Task extends AsyncTask<Void, Void, Void> {
        String error="",records="";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Connection connection= bd.getConnection();

                String q = "SELECT id_usu FROM musuario WHERE cor_usu = ?";
                PreparedStatement ps = connection.prepareStatement(q);
                ps.setString(1, "maurisantiagol@gmail.com");
                ResultSet resultSet = ps.executeQuery();
                String ID = resultSet.toString();
                System.out.println(ID);

                while(resultSet.next()){
                    records+= resultSet.getString(1) ;
                }

                String textoASalvar = ID;
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    fos.write(textoASalvar.getBytes(StandardCharsets.UTF_8));
                    Log.d("tag1", "---------------------------------------------Fichero salvado en: " + getFilesDir() + "/" + FILE_NAME);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(fos != null){
                        try {
                            fos.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
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