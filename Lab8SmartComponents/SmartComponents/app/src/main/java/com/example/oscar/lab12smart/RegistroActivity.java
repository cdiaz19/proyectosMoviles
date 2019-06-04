package com.example.oscar.lab12smart;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;

public class RegistroActivity extends AppCompatActivity {

    private ImageButton smartCamera;
    private ImageButton enviarAlerta;
    private ImageButton enviarBluetooth;
    private ImageView imageViewCamera;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private final static int cons=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        smartCamera=(ImageButton)findViewById(R.id.smartCamera);


        imageViewCamera=(ImageView)findViewById(R.id.imageViewCamera);

        smartCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarIntent();
            }
        });

        enviarAlerta=(ImageButton)findViewById(R.id.enviarAlerta);
        enviarAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro= new Intent(RegistroActivity.this, MensajeActivity.class);
                 startActivity(intentRegistro);
            }
        });
        enviarBluetooth=(ImageButton)findViewById(R.id.enviarBluetooth);
        enviarBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro= new Intent(RegistroActivity.this, BluetoothActivity.class);
                startActivity(intentRegistro);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewCamera.setImageBitmap(imageBitmap);
        }
    }

    private void llamarIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
