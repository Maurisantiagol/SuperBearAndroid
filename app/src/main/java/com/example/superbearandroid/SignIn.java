package com.example.superbearandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private EditText etc, etco1;
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
                Toast.makeText(this, "Iniciando sesión", Toast.LENGTH_LONG).show();
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
        Pattern pattern = Pattern.compile("/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,64}$/");
        Matcher matcher = pattern.matcher(contra);
        return matcher.find();
    }
}
