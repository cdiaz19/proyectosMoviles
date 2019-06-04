package com.example.lenovo.lab.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.lenovo.lab.LogicaNeg.Curso;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUpdCursoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;
    private EditText creditosFld;
    private EditText horasFld;
    private EditText codCar;
    private EditText nombCar;
    String apiUrl = "http://192.168.0.13:8080/Lab7-8Web/";
    //String apiUrl = "http://10.0.2.2:8080/Lab7-8Web/";
    String tempUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_curso);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCursoBtn);
        codCar= findViewById(R.id.codigoCarreraAddUpdCur);
        nombCar=findViewById(R.id.nombreCarreraAddUpdCur);
        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCur);
        nomFld = findViewById(R.id.nombreAddUpdCur);
        creditosFld = findViewById(R.id.creditosAddUpdCur);
        horasFld = findViewById(R.id.horasAddUpdCur);
        codCar.setText("");
        nombCar.setText("");
        codFld.setText("");
        nomFld.setText("");
        creditosFld.setText("");
        horasFld.setText("");

        //receiving data from admCursoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Curso aux = (Curso) getIntent().getSerializableExtra("curso");
                codFld.setText(aux.getCodigo());
                codCar.setText(aux.getCarrera().getCodigo());
                nombCar.setText(aux.getCarrera().getNombre());
                codCar.setEnabled(false);
                nombCar.setEnabled(false);
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                creditosFld.setText(Integer.toString(aux.getCreditos()));
                horasFld.setText(Integer.toString(aux.getHoras()));
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCurso();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCurso();
                    }
                });
            }
        }
    }

    public void addCurso() {
        if (validateForm()) {

            String tempUrl1 = apiUrl + "insertarCurso?codigo="+codFld.getText().toString()+"&nombre="+nomFld.getText().toString()+"&creditos="+creditosFld.getText().toString()
                    +"&horas="+horasFld.getText().toString()+"&codCar="+codCar.getText().toString()+"&nomCar="+nombCar.getText().toString();
            tempUrl = tempUrl1.replaceAll(" ", "%20");
            System.out.println("url"+tempUrl);
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            //do something
            Carrera carrera=new Carrera(codCar.getText().toString(),nombCar.getText().toString(),null);
            Curso cur = new Curso(codFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()),carrera);
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            //sending curso data
            intent.putExtra("addCurso", cur);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCurso() {
        if (validateForm()) {

            tempUrl = apiUrl + "modificarCurso?codigo="+codFld.getText().toString()+"&nombre="+nomFld.getText().toString()+"&creditos="+creditosFld.getText().toString()
                    +"&horas="+horasFld.getText().toString()+"&codCar="+codCar.getText().toString()+"&nomCar="+nombCar.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            Carrera carrera=new Carrera(codCar.getText().toString(),nombCar.getText().toString(),null);
            Curso cur = new Curso(codFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()),carrera);
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            //sending curso data
            intent.putExtra("editCurso", cur);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nombCar.getText())) {
            nomFld.setError("Nombre de la carrera requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codCar.getText())) {
            nomFld.setError("Codigo de Carrera definido");
            error++;
        }
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.creditosFld.getText())) {
            creditosFld.setError("Creditos requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.horasFld.getText())) {
            horasFld.setError("Horas requerido");
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
