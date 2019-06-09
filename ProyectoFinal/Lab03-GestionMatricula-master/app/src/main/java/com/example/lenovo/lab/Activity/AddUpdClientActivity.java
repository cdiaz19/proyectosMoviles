package com.example.lenovo.lab.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddUpdClientActivity extends AppCompatActivity {

  String apiUrl = "http://10.0.2.2:8080/WebProyectoFinal/";
  String tempUrl = "";

  private FloatingActionButton fBtn;
  private boolean editable = true;
  private EditText nomFld;
  private EditText cedFld;
  private EditText emailFld;
  private EditText telFld;
  private EditText passFld;
  private Spinner spinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_upd_client);
    editable = true;

    // button check
    fBtn = findViewById(R.id.addUpdAlumnoBtn);

    //cleaning stuff
    nomFld = findViewById(R.id.nombreAddUpdAlum);
    cedFld = findViewById(R.id.cedulaAddUpdAlum);
    emailFld = findViewById(R.id.emailAddUpdAlum);
    telFld=findViewById(R.id.telefonoAddUpdAlum);
    passFld=findViewById(R.id.passAddUpdAlum);
    nomFld.setText("");
    cedFld.setText("");
    emailFld.setText("");
    telFld.setText("");

    // Init Spinner
    initSpinner();

    //receiving data from admAlumnoActivity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {

      editable = extras.getBoolean("editable");
      if (editable) {   // is editing some row
        Client aux = (Client) getIntent().getSerializableExtra("client");
        cedFld.setText(aux.getUser().getCedula());
        cedFld.setEnabled(false);
        nomFld.setText(aux.getNombre());
        emailFld.setText(aux.getUser().getEmail());
        telFld.setText(Integer.toString(aux.getTelefono()));
        //edit action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            editCliente();
          }
        });
      } else {
        //add new action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            addClient();
          }
        });
      }
    }
  }

  public void initSpinner() {
    spinner = (Spinner) findViewById(R.id.roles_spinner);
    List<String> list = new ArrayList<String>();
    list.add("administrador");
    list.add("cliente");
    list.add("ninguno");
    spinner.setPrompt(getString(R.string.privileges_prompt));
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
      android.R.layout.simple_spinner_item, list);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(dataAdapter);
  }

  public void addClient() {
    if (validateForm()) {
      tempUrl = apiUrl + "insertarCliente?nombre="+nomFld.getText().toString()+
                          "&cedula="+ cedFld.getText().toString() +
                          "&correo="+ emailFld.getText().toString() +
                          "&telefono="+ telFld.getText().toString() +
                          "&contrasena="+ passFld.getText().toString() +
                          "&rol="+ spinner.getSelectedItem().toString();

      MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
      myAsyncTasks.execute();

      User user = new User(cedFld.getText().toString(), emailFld.getText().toString(), passFld.getText().toString(),  spinner.getSelectedItem().toString());

      Client client = new Client(nomFld.getText().toString(),
                                  Integer.parseInt(telFld.getText().toString()),
                                  user);
      Intent intent = new Intent(getBaseContext(), AdmClientActivity.class);
      intent.putExtra("addClient", client);
      startActivity(intent);
      finish(); //prevent go back
    }
  }

  public void editCliente() {
    if (validateForm()) {
      tempUrl = apiUrl + "editCliente?nombre="+nomFld.getText().toString()+
                                      "&cedula="+ cedFld.getText().toString() +
                                      "&correo="+ emailFld.getText().toString() +
                                      "&telefono="+ telFld.getText().toString() +
                                      "&contrasena="+ passFld.getText().toString() +
                                      "&rol="+ spinner.getSelectedItem().toString();

      MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
      myAsyncTasks.execute();

      User user = new User(cedFld.getText().toString(), emailFld.getText().toString(), passFld.getText().toString(), spinner.getSelectedItem().toString());

      Client client = new Client(nomFld.getText().toString(),
                                Integer.parseInt(telFld.getText().toString()),
                                user);
      Intent intent = new Intent(getBaseContext(), AdmClientActivity.class);
      intent.putExtra("editClient", client);
      startActivity(intent);
      finish(); //prevent go back
    }
  }

  public boolean validateForm() {
    int error = 0;
    if (TextUtils.isEmpty(this.nomFld.getText())) {
      nomFld.setError("Field required!");
      error++;
    }
    if (TextUtils.isEmpty(this.cedFld.getText())) {
      cedFld.setError("Field required!");
      error++;
    }
    if (TextUtils.isEmpty(this.emailFld.getText())) {
      emailFld.setError("Field required!");
      error++;
    }
    if (TextUtils.isEmpty(this.telFld.getText())) {
      telFld.setError("Field required!");
      error++;
    }
    if (TextUtils.isEmpty(this.spinner.getSelectedItem().toString())) {
      telFld.setError("Field required!");
      error++;
    }
    if (error > 0) {
      Toast.makeText(getApplicationContext(), "Some errors in the Form!", Toast.LENGTH_LONG).show();
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
        AlertDialog alertDialog = new AlertDialog.Builder(AddUpdClientActivity.this).create();
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
