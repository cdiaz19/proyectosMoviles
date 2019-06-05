package com.example.lenovo.lab.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.example.lenovo.lab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddUpdOrderActivity extends AppCompatActivity {

  String apiUrl = "http://10.0.2.2:8080/WEB-INF/";
  String tempUrl = "";
  String json;

  List<Client> clientsList;
  List<VideoGame> videoGamesList;

  private Spinner sp_videgames;
  private Spinner sp_clients;
  private FloatingActionButton fAB;
  private ModelData model;

  private int cantVideoGames = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_upd_order);

    getSupportActionBar().setTitle("Maintenance Orders");

    tempUrl = apiUrl + "listarVideojuegos";
    MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
    try {
      json = myAsyncTasks.execute().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    final Gson gson = new Gson();
    final Type tipoListaVideojuegos = new TypeToken<List<VideoGame>>(){}.getType();
    final List<VideoGame> videojuegos = gson.fromJson(json, tipoListaVideojuegos);


    sp_videgames = (Spinner) findViewById(R.id.sp_vg);
    sp_clients = (Spinner) findViewById(R.id.sp_cleint);
    fAB = (FloatingActionButton) findViewById(R.id.addBtnG);

    fAB.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        goToGrupos();
      }
    });

    model = new ModelData(null,videojuegos, null, null);

    loadVideoGames();
    loadFilteredVideoGame();
//    loadCiclo();

  }


  public void loadVideoGames() {
    // im not sure about this
    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<VideoGame>(this, R.layout.support_simple_spinner_dropdown_item, model.getVideoGamesList());
    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    sp_videgames.setAdapter(adapter);

    sp_videgames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object item = adapterView.getItemAtPosition(i);
        if (item != null) {
          loadFilteredVideoGame();
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {
      }
    });
  }


  private void loadFilteredVideoGame() {

    int index = 0;

    for (VideoGame c : model.getVideoGamesList()) {
      if (c.getNombre().equals(sp_videgames.getSelectedItem().toString())) {
        index = model.getVideoGamesList().indexOf(c);
      }
    }

    cantVideoGames = model.getVideoGamesList().size();

    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, model.getVideoGamesList());
    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    sp_videgames.setAdapter(adapter);
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
        //AlertDialog alertDialog = new AlertDialog.Builder(AdmVideoGameActivity.this).create();
        //alertDialog.setTitle("Mensaje");
        //alertDialog.setMessage(s);
        //alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
        //      new DialogInterface.OnClickListener() {
        //      public void onClick(DialogInterface dialog, int which) {
        //      dialog.dismiss();
        //  }
        //      });
        //alertDialog.show();

      }
      catch (Exception ex){

      }
    }
  }
}
