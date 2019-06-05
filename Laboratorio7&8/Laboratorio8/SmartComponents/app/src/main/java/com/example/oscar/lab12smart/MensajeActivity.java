package com.example.oscar.lab12smart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MensajeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
    }

    public void EnviarSMS(View v){
        EditText numeroTelefono = (EditText)findViewById(R.id.numeroTelefono);
        EditText mensajeTexto = (EditText)findViewById(R.id.mensajeTexto);
        String numero = numeroTelefono.getText().toString();
        String mensaje = mensajeTexto.getText().toString();


        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero,null,mensaje, null,null);
    }
}
