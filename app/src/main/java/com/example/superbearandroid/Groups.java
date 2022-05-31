package com.example.superbearandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.superbearandroid.Modelo.grupo;
import com.example.superbearandroid.control.bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Groups extends AppCompatActivity {
    private static final int MAX_BYTES = 8000;
    private static  final String FILE_NAME = "NoAbrir.txt";
    private static String error="";
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        list = (ListView) findViewById(R.id.list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups);
        Dialog dialogo = new Dialog(Groups.this);
        System.out.println(readItemList()+"-----------------------------------------------------------<<<>><");
if (readItemList()!=0){
    Toast.makeText(this, "Iniciando sesión", Toast.LENGTH_LONG).show();

    adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            buscarGrupos(readItemList())

    );
    list.setAdapter(adapter);

}else{
    Intent volver = new Intent(this, SignIn.class);
    startActivity(volver);
    Toast.makeText(this, "No se encontro el correo", Toast.LENGTH_SHORT).show();

}


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
    public void volver(View view) {
        Intent volver = new Intent(this, SignIn.class);
        startActivity(volver);
    }

    public void seguir(View view) {
        Intent seguir = new Intent(this, Lists.class);
        startActivity(seguir);
    }

    public ArrayList<String> buscarGrupos(int id) {

        list = (ListView) findViewById(R.id.list);
        ArrayList<grupo> grupos = new ArrayList<>();

        try {
            Connection connection = bd.getConnection();

            String q = "SELECT m.id_grp, cod_grp, nom_grp, nom_usu,e.id_priv FROM mgrupo m\n" +
                    "INNER JOIN egrupo e\n" +
                    "ON m.id_grp = e.id_grp\n" +
                    "INNER JOIN musuario mu\n" +
                    "ON e.id_usu = mu.id_usu\n" +
                    "WHERE mu.id_usu = ?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                grupo objetoGrupo = new grupo();
                objetoGrupo.setId_grp(resultSet.getInt(1));
                objetoGrupo.setCod_grp(resultSet.getString(2));
                objetoGrupo.setNom_grp(resultSet.getString(3));
                objetoGrupo.setNom_usu(resultSet.getString(4));
                objetoGrupo.setId_priv(resultSet.getInt(5));

                grupos.add(objetoGrupo);
            }
            itemList = new ArrayList<>();
            for (int i=0;i<grupos.size();i++){
                itemList.add(grupos.get(i).getNom_grp());


            }

            System.out.println(grupos.get(0).getNom_grp()+grupos.get(1).getNom_grp()+grupos.get(2).getNom_grp()+grupos.get(3).getNom_grp()+grupos.get(4).getNom_grp()+"--------------------------->");
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                    Log.i("Click", "click en el elemento " + position + " de mi ListView");
                    System.out.println(grupos.get(position).getId_grp());
                    //de aqui se escribe en otro txt y se manda a listas para que lea el txt y haga lo mismo que aqui
                }
            });


        } catch (Exception e) {
            error = e.toString();
        }
        return itemList;
    }

}
