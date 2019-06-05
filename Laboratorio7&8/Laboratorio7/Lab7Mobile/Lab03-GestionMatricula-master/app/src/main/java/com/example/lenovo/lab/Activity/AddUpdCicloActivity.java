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

import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUpdCicloActivity extends AppCompatActivity {
    String apiUrl = "http://192.168.43.141:8080/Lab7-8Web/";
    //String apiUrl = "http://10.0.2.2:8080/Lab7-8Web/";
    String tempUrl = "";
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText añoFld;
    private EditText numFld;
    private EditText iniFld;
    private EditText finFld;
    private EditText codFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_ciclo);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCicloBtn);

        //cleaning stuff
        codFld= findViewById(R.id.codigoAddUpdCi);
        añoFld = findViewById(R.id.añoAddUpdCi);
        numFld = findViewById(R.id.numeroAddUpdCi);
        iniFld = findViewById(R.id.finicioAddUpdCi);
        finFld=findViewById(R.id.ffinalAddUpdCi);
        codFld.setText("");
        añoFld.setText("");
        numFld.setText("");
        iniFld.setText("");
        finFld.setText("");

        //receiving data from admCicloActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Ciclo aux = (Ciclo) getIntent().getSerializableExtra("ciclo");
                codFld.setText(aux.getCodigo());
                añoFld.setText(Integer.toString(aux.getAño()));
                codFld.setEnabled(false);
                numFld.setText(aux.getNumero());
                iniFld.setText(aux.getFinicio());
                finFld.setText(aux.getFfinal());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCiclo();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCiclo();
                    }
                });
            }
        }
    }

    public void addCiclo() {
        if (validateForm()) {
            tempUrl = apiUrl + "insertarCiclo?codigo="+codFld.getText().toString()+"&anno="+añoFld.getText().toString()+"&numero="+numFld.getText().toString()
            +"&fechaInicio="+iniFld.getText().toString()+"&fechaFinal="+finFld.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();

            //do something
            Ciclo prof = new Ciclo(codFld.getText().toString(),Integer.parseInt(añoFld.getText().toString()), numFld.getText().toString(),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCicloActivity.class);
            //sending Ciclo data
            intent.putExtra("addciclo", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCiclo() {
        if (validateForm()) {

            tempUrl = apiUrl + "modificarCiclo?codigo="+codFld.getText().toString()+"&anno="+añoFld.getText().toString()+"&numero="+numFld.getText().toString()
                    +"&fechaInicio="+iniFld.getText().toString()+"&fechaFinal="+finFld.getText().toString();
            tempUrl = tempUrl.replaceAll(" ", "%20");
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            Ciclo prof = new Ciclo(codFld.getText().toString(),Integer.parseInt(añoFld.getText().toString()), numFld.getText().toString(),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCicloActivity.class);
            //sending Ciclo data
            intent.putExtra("editciclo", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;

        if (TextUtils.isEmpty(this.codFld.getText())) {
            añoFld.setError("Codigo Requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.añoFld.getText())) {
            añoFld.setError("Año requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.numFld.getText())) {
            numFld.setError("Numero requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.iniFld.getText())) {
            iniFld.setError("Inicio requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.finFld.getText())) {
            finFld.setError("Final requerido");
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
