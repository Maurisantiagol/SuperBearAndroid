package com.example.superbearandroid.control;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class bd {


    public static Connection getConnection() throws ClassNotFoundException {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String url, userName, password;
            url = "jdbc:mysql://superbear.mysql.database.azure.com/superbear";
            userName = "alexisluisedgarmauriciomiguel";
            password = "$Beartual1412AELMMFornite$1412mareenplatano777pillomaumiguelin";
            Connection conexion=DriverManager.getConnection(url, userName, password);
            System.out.println("Se conecto a la BD");
            return conexion;


        } catch (SQLException sq) {
            System.out.println("Error al conectar la BD");
            System.out.println(sq.getMessage());
        } catch (Exception e) {
            System.out.println("Error de clase");
            System.out.println(e.getMessage());
        }
        return null;
    }







}