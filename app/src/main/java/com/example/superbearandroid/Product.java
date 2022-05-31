package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superbearandroid.Modelo.producto;
import com.example.superbearandroid.control.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Product extends AppCompatActivity {
    private static final int MAX_BYTES = 8000;
    private static  final String FILE_NAME = "Idlista.txt";
    private static String error="";
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList = new ArrayList<>();
    private ArrayList<Integer> idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        list = (ListView) findViewById(R.id.list);


        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buscarProductos(readItemList())

        );
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Log.i("Click", "click en el elemento " + position + " de mi ListView");

            }
        });
    }
    public int readItemList() {
        String content="";
        int contenInt = 0;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buffer = new byte[MAX_BYTES];
            int nread = fis.read(buffer);
            if (nread > 0) {
                content = new String(buffer, 0, nread);

            }
            fis.close();
        } catch (FileNotFoundException e) {
            Log.i("pauek", "readItemList: FileNotFoundException");
        } catch (IOException e) {
            Log.e("pauek", "readItemList: IOException");
            Toast.makeText(this, "No se puede leer el archivo", Toast.LENGTH_SHORT).show();
        }finally {
            if(content!=""){
                contenInt = Integer.parseInt(content);
            }
        }
        return contenInt;
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
            ps.setInt(1, id);
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
