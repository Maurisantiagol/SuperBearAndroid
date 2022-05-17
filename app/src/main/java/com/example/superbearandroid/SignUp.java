package com.example.superbearandroid;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.StringRequest;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {


    private static final String TAG = "SignUp";
    private Spinner spinner1, spinner2;
    private Button btnlink;
    private EditText etn,etc, etco1, etco2;
    private CheckBox aviso;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        etn = (EditText) findViewById(R.id.etn);
        etc = (EditText)findViewById(R.id.etc);
        etco1 = (EditText)findViewById(R.id.etco1);
        etco2 = (EditText)findViewById(R.id.etco2);
        aviso = (CheckBox)findViewById(R.id.checkBox4);
        mDisplayDate = (EditText)findViewById(R.id.mDisplayDate);

        btnlink = findViewById(R.id.button_link);
        String url = "https://super-bear.azurewebsites.net/avisodeprivacidad";
        btnlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse(url);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        String [] opciones ={"Álvaro Obregón","Azcapotzalco","Benito Juárez", "Coyoacán", "Cuajimalpa de Morelos", "Cuauhtémoc", "Gustavo A. Madero", "Iztacalco", "Iztapalapa", "Magdalena Contreras", "Miguel Hidalgo", "Milpa Alta", "Tláhuac", "Tlalpan", "Venustiano Carranza", "Xochimilco"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_alcaldia, opciones);
        spinner1.setAdapter(adapter);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        String [] opciones2 ={"Masculino", "Femenino", "Otro"};
        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_alcaldia, opciones2);
        spinner2.setAdapter(adapter2);

        TextView mDisplayDate = (TextView) findViewById(R.id.mDisplayDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        SignUp.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);
                if(year > 2004){
                    String date = 17 + "/" + 11 + "/" + 2004;
                    mDisplayDate.setText(date);
                }else{
                    String date = day + "/" + month + "/" + year;
                    mDisplayDate.setText(date);
                }

            }
        };
    }

    public void enviar(View view){

        String nombre = etn.getText().toString();
        String correo = etc.getText().toString();
        String contra1 = etco1.getText().toString();
        String contra2 = etco2.getText().toString();
        CheckBox aviso1 = aviso;
        String fecha =  mDisplayDate.getText().toString();
        String spin1 = spinner1.getSelectedItem().toString();
        String spin2 = spinner2.getSelectedItem().toString();
        System.out.println(fecha + "-+---+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(spin1);
        System.out.println(spin2);

        Integer alcaldia = 0;
        Integer genero = 0;


        switch (spin2){
            case "Masculino":{
                genero = 1;
                break;
            }
            case "Femenino":{
                genero = 2;
                break;
            }
            case "Otro":{
                genero = 3;
                break;
            }
        }

        switch (spin1){
            case "Álvaro Obregón":{
                alcaldia = 1;
                break;
            }
            case "Azcapotzalco":{
                alcaldia = 2;
                break;
            }
            case "Benito Juárez":{
                alcaldia = 3;
                break;
            }
            case "Coyoacán":{
                alcaldia = 4;
                break;
            }
            case "Cuajimalpa de Morelos":{
                alcaldia = 5;
                break;
            }
            case "Cuauhtémoc":{
                alcaldia = 6;
                break;
            }
            case "Gustavo A. Madero":{
                alcaldia = 7;
                break;
            }
            case "Iztacalco":{
                alcaldia = 8;
                break;
            }
            case "Iztapalapa":{
                alcaldia = 9;
                break;
            }
            case "Magdalena Contreras":{
                alcaldia = 10;
                break;
            }
            case "Miguel Hidalgo":{
                alcaldia = 11;
                break;
            }
            case "Milpa Alta":{
                alcaldia = 12;
                break;
            }
            case "Tláhuac":{
                alcaldia = 13;
                break;
            }
            case "Tlalpan":{
                alcaldia = 14;
                break;
            }
            case "Venustiano Carranza":{
                alcaldia = 15;
                break;
            }
            case "Xochimilco":{
                alcaldia = 16;
                break;
            }
            default: {
                alcaldia = 1;
                break;
            }
        }

        System.out.println(genero);
        System.out.println(alcaldia);

        if(nombre.length() == 0){
            Toast.makeText(this, "Debes ingresar un nombre", Toast.LENGTH_LONG).show();
        }else{
            if(correo.length() == 0){
                Toast.makeText(this, "Debes ingresar un correo", Toast.LENGTH_LONG).show();
            }else{
                if(contra1.length() == 0){
                    Toast.makeText(this, "Debes ingresar un contraseña", Toast.LENGTH_LONG).show();
                }else{
                    if(contra2.length() == 0){
                        Toast.makeText(this, "Por favor confirma la contraseña", Toast.LENGTH_LONG).show();
                    }else{
                        if(contra1.equals(contra2)){
                            Toast.makeText(this, "las contaseñas coinciden", Toast.LENGTH_LONG).show();
                            if (fecha.equals("Fecha de nacimiento")){
                                Toast.makeText(this, "Ingrese una fecha de nacimiento", Toast.LENGTH_LONG).show();
                            }else {
                                if(aviso1.isChecked()){
                                    Toast.makeText(this, "acepta terminos y condiciones", Toast.LENGTH_LONG).show();
                                    if(validarNombre(nombre) == true) {
                                        if(validarCorreo(correo) == true) {
                                            if(validarContra(contra1) == true){
                                                Toast.makeText(this, "++", Toast.LENGTH_LONG).show();
                                                //registro

                                            }else{
                                                Toast.makeText(this, "Ingresa una contraseña valida", Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Toast.makeText(this, "Ingresa un correo valido", Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(this, "Ingresa un nombre valido", Toast.LENGTH_LONG).show();
                                    }

                                }else{
                                    Toast.makeText(this, "Debe leer y aceptar el aviso de privacidad", Toast.LENGTH_LONG).show();
                                }
                            }

                        }else{
                            Toast.makeText(this, "las contaseñas no coinciden", Toast.LENGTH_LONG).show();
                        }
                    }
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

    public boolean validarNombre (String email){
        Pattern pattern = Pattern.compile("[a-zA-Z]{1,32}");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private void crearUsuario(String Nombre, String Correo, String Contra, String fecha, int alcaldia, int genero){
        /*

         try {
                Connection connection= bd.getConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("SELECT * FROM musuario WHERE (cor_usu = +Correo+ )");

                        if(resultSet == null){
                        try {
                        Connection connection= bd.getConnection();
                        Statement statement=connection.createStatement();
                        ResultSet resultSet=statement.executeQuery("INSERT");


                    } catch (Exception e) {
                        error =e.toString();
                    }

                }

            } catch (Exception e) {
                error =e.toString();
            }

*/

    }
}
