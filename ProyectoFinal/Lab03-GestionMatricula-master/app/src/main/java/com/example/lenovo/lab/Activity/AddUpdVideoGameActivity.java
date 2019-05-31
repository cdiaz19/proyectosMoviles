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
import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.example.lenovo.lab.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddUpdVideoGameActivity extends AppCompatActivity {

  private FloatingActionButton fBtn;
  private boolean editable;
  private EditText codeCatFld;
  private EditText nameCatFld;
  private EditText cantVGFld;
  private EditText codeVGFld;
  private EditText nameFld;
  private EditText priceFld;
  private EditText companyFld;
  private VideoGame critFiltG;

  String apiUrl = "http://10.0.2.2:8080/WEB-INF/";
  String tempUrl = "";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_upd_videogame);
    editable = true;
    fBtn = findViewById(R.id.addUpdVideoGameBtn);
    //cleaning stuff
    codeVGFld = findViewById(R.id.codeVGAddUpdVG);
    codeCatFld = findViewById(R.id.codeCatAddUpdVG);
    nameCatFld = findViewById(R.id.nameCatAddUpdVG);
    cantVGFld = findViewById(R.id.cantAddUpdVG);
    nameFld = findViewById(R.id.nameAddUpdVG);
    priceFld = findViewById(R.id.priceAddUpdVG);
    companyFld = findViewById(R.id.companyAddUpdVG);

    //receiving data from admGrupoActivity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      critFiltG = (VideoGame) getIntent().getSerializableExtra("filtGrupo");
      editable = extras.getBoolean("editable");
      if (editable) {   // is editing some row
        VideoGame aux = (VideoGame) getIntent().getSerializableExtra("videoGame");



        codeCatFld.setText(aux.getCategoria().getCodigo());
        codeCatFld.setEnabled(false);
        nameCatFld.setText(aux.getCategoria().getNombre());
        nameCatFld.setEnabled(false);
        codeVGFld.setText(aux.getCodigoJuego());
        codeVGFld.setEnabled(false);
        nameFld.setText(aux.getNombre());
        cantVGFld.setText(String.valueOf(aux.getCantidad()));
        priceFld.setText(String.valueOf(aux.getCantidad()));
        companyFld.setText(aux.getEmpresa());

        //edit action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            editVideoGame();
          }
        });
      } else {

        //add new action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            addVideoGame();
          }
        });
      }
    }
  }

  public void addVideoGame() {
    if (validateForm()) {
      tempUrl = apiUrl + "insertarVid?nombre="+ nameFld.getText().toString()
                        +"&codigoJuego="+ codeVGFld.getText().toString()
                        +"&empresa="+ companyFld.getText().toString()
                        +"&cantidad="+ cantVGFld.getText().toString()
                        +"&precio="+ priceFld.getText().toString()
                        +"&categoria_id="+ codeCatFld.getText().toString()
                        +"&nombre_categoria="+ nameCatFld.getText().toString();

      MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
      myAsyncTasks.execute();

      String codeCat = codeCatFld.getText().toString();
      String nameCat = nameCatFld.getText().toString();

      VideoGame videoGame =
        new VideoGame(codeVGFld.getText().toString(), nameFld.getText().toString(), companyFld.getText().toString(),
                      Integer.parseInt(cantVGFld.getText().toString()),
                      Integer.parseInt(priceFld.getText().toString()), new Category(codeCat, nameCat));

      Toast.makeText(getApplicationContext(), videoGame.getNombre(), Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getBaseContext(), AdmVideoGameActivity.class);

      intent.putExtra("addVideoGame", videoGame);
      intent.putExtra("filtGrupo", critFiltG);
      startActivity(intent);
      finish();
    }
  }

  public void editVideoGame() {
    if (validateForm()) {

      tempUrl = apiUrl + "editVid?nombre="+ nameFld.getText().toString()
              +"&codigoJuego="+ codeVGFld.getText().toString()
              +"&empresa="+ companyFld.getText().toString()
              +"&cantidad="+ cantVGFld.getText().toString()
              +"&precio="+ priceFld.getText().toString()
              +"&categoria_id="+ codeCatFld.getText().toString()
              +"&nombre_categoria="+ nameCatFld.getText().toString();

      MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
      myAsyncTasks.execute();

      String codeCat = codeCatFld.getText().toString();
      String nameCat = nameCatFld.getText().toString();

      VideoGame videoGame =
              new VideoGame(codeVGFld.getText().toString(), nameFld.getText().toString(), companyFld.getText().toString(),
                      Integer.parseInt(cantVGFld.getText().toString()),
                      Integer.parseInt(priceFld.getText().toString()), new Category(codeCat, nameCat));

      Toast.makeText(getApplicationContext(), videoGame.getNombre(), Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getBaseContext(), AdmVideoGameActivity.class);

      intent.putExtra("editVideoGame", videoGame);
      intent.putExtra("filtGrupo", critFiltG);
      startActivity(intent);
      finish();
    }
  }

  public boolean validateForm() {
    int error = 0;
    if (TextUtils.isEmpty(this.nameFld.getText())) {
      nameFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.priceFld.getText())) {
      priceFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.codeCatFld.getText())) {
      codeCatFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.priceFld.getText())) {
      companyFld.setError("Field is Required!");
      error++;
    }
    if (error > 0) {
      Toast.makeText(getApplicationContext(), "Form has some errors!", Toast.LENGTH_LONG).show();
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
        AlertDialog alertDialog = new AlertDialog.Builder(AddUpdVideoGameActivity.this).create();
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