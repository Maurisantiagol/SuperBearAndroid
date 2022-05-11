package com.example.superbearandroid.control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class bd {



    public static Connection getConnection() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Connection");
            Connection connection= DriverManager.getConnection("jdbc:mysql://superbear.mysql.database.azure.com:3306/superbear","alexisluisedgarmauriciomiguel","$Beartual1412AELMMFornite$1412mareenplatano777pillomaumiguelin");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Se conecto a la BD");
            return connection;


        }catch(SQLException sq){
            System.out.println("Error al conectar la BD");
            System.out.println(sq.getMessage());
        }catch(Exception e){
            System.out.println("Error de clase");
            System.out.println(e.getMessage());
        }
        return null;
    }

}