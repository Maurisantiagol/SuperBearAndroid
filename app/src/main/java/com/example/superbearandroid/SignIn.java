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

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private EditText etc, etco1;
    private static  final String FILE_NAME = "NoAbrir.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        etc = (EditText)findViewById(R.id.correo);
        etco1 = (EditText)findViewById(R.id.contra);

    }

    public void enviar(View view){

        String correo = etc.getText().toString();
        String contra = etco1.getText().toString();

        if(correo.length() == 0){
            Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_LONG).show();
        }else{
            if(contra.length() == 0){
                Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
            }else{
                if(validarCorreo(correo) == true){
                    if(validarContra(contra) == true){
                        Toast.makeText(this, "Iniciando sesión", Toast.LENGTH_LONG).show();
                        Iniciar(correo, contra);

                        Intent ingresar = new Intent(this, Groups.class);
                        startActivity(ingresar);
                    }else{
                        Toast.makeText(this, "Ingresa una contraseña valida", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Ingresa un correo valido", Toast.LENGTH_LONG).show();
                }

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

    public void Iniciar(String email, String contra ){
        try {
            Connection connection= bd.getConnection();
            String q = "SELECT id_usu FROM musuario WHERE cor_usu = ?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            String ID = rs.toString();
            System.out.println(ID);
            if(ID.isEmpty()) {
                Toast.makeText(this, "No existe un registro de ese correo", Toast.LENGTH_LONG).show();
                Intent volver = new Intent(this, SignIn.class);
                startActivity(volver);
            }else{
                saveFile(ID);
                Toast.makeText(this, "Iniciando sesión", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
