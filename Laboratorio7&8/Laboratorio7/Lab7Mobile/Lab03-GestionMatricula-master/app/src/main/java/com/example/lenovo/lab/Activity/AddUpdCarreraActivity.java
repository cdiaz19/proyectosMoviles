package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Carrera;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUpdCarreraActivity extends AppCompatActivity {
    String apiUrl = "http://192.168.43.141:8080/Lab7-8Web/";
    //String apiUrl = "http://10.0.2.2:8080/Lab7-8Web/";
    String tempUrl = "";
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;
    private EditText tituloFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_carrera);

        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCarreraBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCar);
        nomFld = findViewById(R.id.nombreAddUpdCar);
        tituloFld = findViewById(R.id.tituloAddUpdCar);
        codFld.setText("");
        nomFld.setText("");
        tituloFld.setText("");

        //receiving data from admCarreraActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Carrera aux = (Carrera) getIntent().getSerializableExtra("carrera");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                tituloFld.setText(aux.getTitulo());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCarrera();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCarrera();
                    }
                });
            }
        }
    }

    public void addCarrera() {
        if (validateForm()) {

            tempUrl = apiUrl + "insertarCarrera?codigo="+codFld.getText().toString()+"&nombre="+nomFld.getText().toString()+"&titulo="+tituloFld.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();


            //do something
            Carrera car = new Carrera(codFld.getText().toString(), nomFld.getText().toString(), tituloFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCarreraActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCarrera() {
        if (validateForm()) {
            tempUrl = apiUrl + "modificarCarrera?codigo="+codFld.getText().toString()+"&nombre="+nomFld.getText().toString()+"&titulo="+tituloFld.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            Carrera car = new Carrera(codFld.getText().toString(), nomFld.getText().toString(), tituloFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCarreraActivity.class);
            //sending carrera data
            intent.putExtra("editCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.tituloFld.getText())) {
            tituloFld.setError("Titulo requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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

                // final Gson gson = new Gson();
                // final Type tipoListaCategories = new TypeToken<List<Category>>(){}.getType();
                // categoriesList = gson.fromJson(s, tipoListaCategories);
                //System.out.println(categoriesList);


                //AlertDialog alertDialog = new AlertDialog.Builder(AdmCategoryActivity.this).create();
                //alertDialog.setTitle("Mensaje");
                //alertDialog.setMessage(s);
                //alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                //      new DialogInterface.OnClickListener() {
                //        public void onClick(DialogInterface dialog, int which) {
                //          dialog.dismiss();
                //    }
                //});
                //alertDialog.show();

            }
            catch (Exception ex){

            }
        }


    }

}
