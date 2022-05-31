package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superbearandroid.Modelo.producto;
import com.example.superbearandroid.control.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Product extends AppCompatActivity {
    private static final int MAX_BYTES = 8000;
    private static  final String FILE_NAME = "NoAbrir.txt";
    private static String error="";
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        list = (ListView) findViewById(R.id.list);


        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buscarProductos(1)

        );
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Log.i("Click", "click en el elemento " + position + " de mi ListView");

            }
        });
    }

    public void volver(View view){
        Intent volver = new Intent(this, Lists.class);
        startActivity(volver);
    }
    public ArrayList<String> buscarProductos(int id) {

        list = (ListView) findViewById(R.id.list);
        ArrayList<producto> productos = new ArrayList<>();

        try {
            Connection connection = bd.getConnection();

            String q = "select nom_pro from dproducto where id_eli=?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setInt(1, 87);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                producto objetoProducto = new producto();
                objetoProducto.setNombreProducto(resultSet.getString(1));


                productos.add(objetoProducto);
            }
            itemList = new ArrayList<>();
            for (int i=0;i<productos.size();i++){
                itemList.add(productos.get(i).getNombreProducto());


            }

            System.out.println(itemList+"--------------------------->");



        } catch (Exception e) {
            error = e.toString();
            System.out.println(error);
        }
        return itemList;
    }
}
