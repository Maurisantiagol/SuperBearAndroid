package com.example.superbearandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.superbearandroid.control.bd;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private static final int MAX_BYTES = 8000;
    private EditText etc, etco1;
    private static  final String FILE_NAME = "NoAbrir.txt";
    private ArrayList<String> itemList;
    private ArrayList<String> ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        etc = (EditText)findViewById(R.id.correo);

    }

    public void enviar(View view){

        String correo = etc.getText().toString();

        if(correo.length() == 0){
            Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_LONG).show();
        }else{
                if(validarCorreo(correo) == true){
                        Iniciar(correo);
                    System.out.println(readItemList()+"---------------------------------------------------------------------------------->>>>");
                    Intent ingresar = new Intent(this, Groups.class);
                        startActivity(ingresar);


                }else{
                    Toast.makeText(this, "Ingresa un correo valido", Toast.LENGTH_LONG).show();
                }

            }
        }


    public void volver(View view){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }

    public boolean validarCorreo (String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public boolean validarContra (String contra){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$");
        Matcher matcher = pattern.matcher(contra);
        return matcher.find();
    }

    private void saveFile(String ID){
        String textoASalvar = ID;
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(textoASalvar.getBytes(StandardCharsets.UTF_8));
            Log.d("tag1", "---------------------------------------------Fichero salvado en: " + getFilesDir() + "/" + FILE_NAME);
            System.out.println();
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


    public ArrayList<String> readItemList() {
        itemList = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buffer = new byte[MAX_BYTES];
            int nread = fis.read(buffer);
            if (nread > 0) {
                String content = new String(buffer, 0, nread);
                itemList.add(content);

            }
            fis.close();
        } catch (FileNotFoundException e) {
            Log.i("pauek", "readItemList: FileNotFoundException");
        } catch (IOException e) {
            Log.e("pauek", "readItemList: IOException");
            Toast.makeText(this, "No se puede leer el archivo", Toast.LENGTH_SHORT).show();
        }
        return itemList;
    }
    public void Iniciar(String email ){
        String records="";
        try {
            Connection connection= bd.getConnection();
            String q = "SELECT id_usu FROM musuario WHERE cor_usu = ?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            String ID = rs.toString();
            System.out.println(ID);
            while(rs.next()){
                records = rs.getString(1);
            }
            if(ID.isEmpty()) {
                Toast.makeText(this, "No existe un registro de ese correo", Toast.LENGTH_LONG).show();
                Intent volver = new Intent(this, SignIn.class);
                startActivity(volver);
            }else{
                saveFile(records);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
