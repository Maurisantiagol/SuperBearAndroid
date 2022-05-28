package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups);
        System.out.println(buscarGrupos().get(0));

    }

    public void volver(View view) {
        Intent volver = new Intent(this, SignIn.class);
        startActivity(volver);
    }

    public void seguir(View view) {
        Intent seguir = new Intent(this, Lists.class);
        startActivity(seguir);
    }

    public static ArrayList<grupo> buscarGrupos() {

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



        } catch (Exception e) {
            error = e.toString();
        }
        return grupos;
    }
}
