package com.example.superbearandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superbearandroid.Modelo.grupo;
import com.example.superbearandroid.control.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Groups extends AppCompatActivity {
    private static String error="";
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups);
        Dialog dialogo = new Dialog(Groups.this);


        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buscarGrupos()

        );
        list.setAdapter(adapter);

    }

    public void volver(View view) {
        Intent volver = new Intent(this, SignIn.class);
        startActivity(volver);
    }

    public void seguir(View view) {
        Intent seguir = new Intent(this, Lists.class);
        startActivity(seguir);
    }

    public ArrayList<String> buscarGrupos() {

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
            ps.setInt(1, 2);
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



        } catch (Exception e) {
            error = e.toString();
        }
        return itemList;
    }
}
