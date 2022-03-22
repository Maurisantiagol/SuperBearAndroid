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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {


    private static final String TAG = "SignUp";
    private Spinner spinner1, spinner2;
    private Button btnlink;
    private EditText etn,etc, etco1, etco2;

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


}
