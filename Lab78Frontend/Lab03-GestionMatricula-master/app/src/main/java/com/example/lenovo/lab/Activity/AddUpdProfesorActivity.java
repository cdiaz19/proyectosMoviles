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

import com.example.lenovo.lab.LogicaNeg.Profesor;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUpdProfesorActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText nomFld;
    private EditText cedFld;
    private EditText emailFld;
    private EditText telFld;
    String apiUrl = "http://192.168.0.13:8080/Lab7-8Web/";
    //String apiUrl = "http://10.0.2.2:8080/Lab7-8Web/";
    String tempUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_profesor);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdProfesorBtn);

        //cleaning stuff
        nomFld = findViewById(R.id.nombreAddUpdProf);
        cedFld = findViewById(R.id.cedulaAddUpdProf);
        emailFld = findViewById(R.id.emailAddUpdProf);
        telFld=findViewById(R.id.telefonoAddUpdProf);
        nomFld.setText("");
        cedFld.setText("");
        emailFld.setText("");
        telFld.setText("");

        //receiving data from admProfesorActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Profesor aux = (Profesor) getIntent().getSerializableExtra("profesor");
                cedFld.setText(aux.getCedula());
                cedFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                emailFld.setText(aux.getEmail());
                telFld.setText(Integer.toString(aux.getTelefono()));
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editProfesor();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addProfesor();
                    }
                });
            }
        }
    }

    public void addProfesor() {
        if (validateForm()) {

            tempUrl = apiUrl + "insertarProfesor?cedula="+cedFld.getText().toString()+"&nombre="+nomFld.getText().toString()+"&email="+emailFld.getText().toString()
                    +"&telefono="+telFld.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            //do something
            Profesor prof = new Profesor(cedFld.getText().toString(), nomFld.getText().toString(),
                    emailFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmProfesorActivity.class);
            //sending Profesor data
            intent.putExtra("addProfesor", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editProfesor() {
        if (validateForm()) {

            tempUrl = apiUrl + "modificarProfesor?cedula="+cedFld.getText().toString()+"&nombre="+nomFld.getText().toString()+"&email="+emailFld.getText().toString()
                    +"&telefono="+telFld.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            Profesor prof = new Profesor(cedFld.getText().toString(), nomFld.getText().toString(),
                    emailFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmProfesorActivity.class);
            //sending Profesor data
            intent.putExtra("editProfesor", prof);
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
        if (TextUtils.isEmpty(this.cedFld.getText())) {
            cedFld.setError("Cedula requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.emailFld.getText())) {
            emailFld.setError("Email requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.telFld.getText())) {
            telFld.setError("Telefono requerido");
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
