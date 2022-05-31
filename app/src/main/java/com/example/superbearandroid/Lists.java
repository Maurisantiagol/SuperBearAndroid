package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superbearandroid.Modelo.lista;
import com.example.superbearandroid.control.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Lists extends AppCompatActivity {
    private static final int MAX_BYTES = 8000;
    private static  final String FILE_NAME = "NoAbrir.txt";
    private static String error="";
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList;
    private ArrayList<Integer> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists);
        list = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buscarListas(1)

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
        Intent volver = new Intent(this, Groups.class);
        startActivity(volver);
    }
    public void Seguir(View view){
        Intent seguir = new Intent(this, Product.class);
        startActivity(seguir);
    }
    public ArrayList<String> buscarListas(int id) {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        list = (ListView) findViewById(R.id.list);
        ArrayList<lista> idlistas = new ArrayList<>();
        ArrayList<lista> listas = new ArrayList<>();

        try {
            Connection connection = bd.getConnection();
            String q = "SELECT id_lst from elista WHERE id_grp= ?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setInt(1, 53);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                lista objetoLista = new lista();
                objetoLista.setIdLista(resultSet.getInt(1));


                idlistas.add(objetoLista);
            }
            idList = new ArrayList<>();
            for (int i=0;i<idlistas.size();i++){
                idList.add(idlistas.get(i).getIdLista());
            }

            System.out.println(idList+"--------------------------->idList");

            for (int i=0;i<idList.size();i++){
                System.out.println(idList.get(i).toString());
                String q2 = "SELECT nom_lis from mlista WHERE id_lis=?";
                PreparedStatement ps2 = connection.prepareStatement(q2);
                ps2.setInt(1, idList.get(i));
                ResultSet resultSet2 = ps2.executeQuery();
                while (resultSet2.next()) {
                    lista objetoLista = new lista();
                    objetoLista.setNombreLista(resultSet2.getString(1));


                    listas.add(objetoLista);
                }
                itemList = new ArrayList<>();
                for (int j=0;j<listas.size();j++){
                    itemList.add(listas.get(j).getNombreLista());


                }

            }

        } catch (Exception e) {
            error = e.toString();
            System.out.println(error);
        }
        return itemList;
    }










}
