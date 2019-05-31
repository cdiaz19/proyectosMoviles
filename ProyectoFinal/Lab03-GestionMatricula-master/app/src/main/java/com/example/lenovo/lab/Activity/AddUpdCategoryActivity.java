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

import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUpdCategoryActivity extends AppCompatActivity {



    String apiUrl = "http://10.0.2.2:8080/WebProyectoFinal/";
    String tempUrl = "";
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_category);
        editable = true;

        fBtn = findViewById(R.id.addUpdCategoryBtn);
        codFld = findViewById(R.id.codeInAddUpdCar);
        nomFld = findViewById(R.id.nameInAddUpdCar);
        codFld.setText("");
        nomFld.setText("");

        //receiving data from admCategoryActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Category aux = (Category) getIntent().getSerializableExtra("category");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());

                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCategory();
                    }
                });
            } else {
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCategory();
                    }
                });
            }
        }
    }

    public void addCategory() {
        if (validateForm()) {
            tempUrl = apiUrl + "insertarCategoria?nombre="+nomFld.getText().toString()+"&codigo="+codFld.getText().toString();
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            Category category = new Category(codFld.getText().toString(), nomFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCategoryActivity.class);
            intent.putExtra("addCategory", category);
            startActivity(intent);
            finish();
        }
    }

    public void editCategory() {
        if (validateForm()) {
            tempUrl = apiUrl + "editCategoria?nombre="+nomFld.getText().toString()+"&codigo="+codFld.getText().toString();
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute();
            Category category = new Category(codFld.getText().toString(), nomFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCategoryActivity.class);
            intent.putExtra("editCategory", category);
            startActivity(intent);
            finish();
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Name is required!");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Name is required!");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Form has errors!", Toast.LENGTH_LONG).show();
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
                AlertDialog alertDialog = new AlertDialog.Builder(AddUpdCategoryActivity.this).create();
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
