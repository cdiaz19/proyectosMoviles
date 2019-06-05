package com.example.mobilecontrol;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.widget.ImageView;

import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    Button botoninsertar, botonleer, botonnactualizar, botoneliminar, botonlistar;
    TextView txtView;

    String apiUrl = "http://10.0.2.2:8080/LabConnection2/";
    String tempUrl = "";
    String title, image, category;
    TextView titleTextView, categoryTextView;
    ProgressDialog progressDialog;
    Button displayData;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        botoninsertar = (Button) findViewById(R.id.button);
        botonleer = (Button) findViewById(R.id.button2);
        botonnactualizar = (Button) findViewById(R.id.button3);
        botoneliminar = (Button) findViewById(R.id.button4);
        botonlistar = (Button) findViewById(R.id.button5);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        botoninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre="pepito";
                String edad= "76";
                tempUrl = apiUrl + "insertarEstudiante?nombre="+nombre+"&edad="+edad;
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();

            }
        });
        botonleer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempUrl = apiUrl + "leerEstudiante";
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();

            }
        });
        botonnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempUrl = apiUrl + "actualizarEstudiante";
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();

            }
        });
        botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempUrl = apiUrl + "eliminarEstudiante";
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();


            }
        });
        botonlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempUrl = apiUrl + "listarEstudiante";
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();

            }
        });

    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // display a progress dialog for good user experiance
            /*progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(tempUrl);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                         //System.out.print(current);

                    }
                    System.out.println(current);
                    // return the data to onPostExecute method
                    Log.w("", current);

                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Mensaje");
                alertDialog.setMessage(s);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
            catch (Exception ex){

            }
        }


    }
}