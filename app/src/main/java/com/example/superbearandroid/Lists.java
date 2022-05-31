package com.example.superbearandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superbearandroid.Modelo.lista;
import com.example.superbearandroid.control.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Lists extends AppCompatActivity {
    private static final int MAX_BYTES = 8000;
    private static  final String FILE_NAME = "Idgrupo.txt";
    private static  final String FILE_NAME2 = "Idlista.txt";
    private static String error="";
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList = new ArrayList<>();
    private ArrayList<Integer> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists);
        list = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buscarListas(readItemList())

        );
        list.setAdapter(adapter);

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
            String q = "SELECT id_lst,id_eli from elista WHERE id_grp= ?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                lista objetoLista = new lista();
                objetoLista.setIdLista(resultSet.getInt(1));
                objetoLista.setId_eli(resultSet.getInt(2));


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
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                    Log.i("Click", "click en el elemento " + position + " de mi ListView");
                    System.out.println(idlistas.get(position).getId_eli());
                    String textoASalvar = Integer.toString(idlistas.get(position).getId_eli());
                    FileOutputStream fos = null;
                    try {
                        fos = openFileOutput(FILE_NAME2, Context.MODE_PRIVATE);
                        fos.write(textoASalvar.getBytes(StandardCharsets.UTF_8));
                        Log.d("tag1", "---------------------------------------------Fichero salvado en: " + getFilesDir() + "/" + FILE_NAME2);
                        Intent seguir = new Intent(Lists.this, Product.class);
                        startActivity(seguir);
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
                }
            });
        } catch (Exception e) {
            error = e.toString();
            System.out.println(error);
        }
        return itemList;
    }










}
