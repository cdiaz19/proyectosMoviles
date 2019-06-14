package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.LogicaNeg.Order;
import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.example.lenovo.lab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddUpdOrderActivity extends AppCompatActivity {

  String apiUrl = "http://10.0.2.2:8080/WebProyectoFinal/";
  String tempUrl = "";
  String json;
  String json2;
  private boolean editable = true;

  List<Client> clientsList;
  List<VideoGame> videoGamesList;


  private Spinner sp_videgames;
  private Spinner sp_clients;
  private EditText orDateFdl;
  private EditText cantFdl;
  private EditText totalFdl;
  private EditText id;
  private FloatingActionButton fAB;

  private ModelData model;
  private Calendar calendar;
  private SimpleDateFormat simpleDateFormat;

  private int cantVideoGames = 0;
  private int cantClients = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_upd_order);
    videoGamesList = new ArrayList<>();
    clientsList = new ArrayList<>();
    editable = true;

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

    tempUrl = apiUrl + "listarClientes";
    MyAsyncTasks myAsyncTasks2 = new MyAsyncTasks();
    try {
      json2 = myAsyncTasks2.execute().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    final Gson gson2 = new Gson();
    final Type tipoListaClientes = new TypeToken<List<Client>>(){}.getType();
    final List<Client> clientes = gson2.fromJson(json2, tipoListaClientes);

    id= findViewById(R.id.idOrder);
    cantFdl = findViewById(R.id.cantAddUpdOr);
    totalFdl = findViewById(R.id.totalAddUpdOr);

    orDateFdl = findViewById(R.id.fechaAddUpdOr);
    calendar = Calendar.getInstance();

    simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String date = simpleDateFormat.format(calendar.getTime());
    orDateFdl.setText(date);
    orDateFdl.setEnabled(false);
    id.setText("");
    id.setEnabled(false);
    cantFdl.setText("");
    totalFdl.setText("");
    totalFdl.setEnabled(false);

    sp_videgames = (Spinner) findViewById(R.id.sp_vg);
    sp_clients = (Spinner) findViewById(R.id.sp_cleint);
    fAB = (FloatingActionButton) findViewById(R.id.addUpdOrderBtn);

    model = new ModelData(null,videojuegos, clientes, null);
    videoGamesList=model.getVideoGamesList();
    clientsList=model.getClientList();
    loadVideoGames();
    loadFilteredVideoGame();
    loadCients();

    //receiving data from admAlumnoActivity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {

      editable = extras.getBoolean("editable");
      if (editable) {   // is editing some row
        Order aux = (Order) getIntent().getSerializableExtra("order");
        orDateFdl.setText(aux.getFecha());
        orDateFdl.setEnabled(false);
        cantFdl.setText(aux.getCantidad());
        totalFdl.setText(aux.getTotal());

        //edit action
        fAB.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            editOrder();
          }
        });
      } else {
        //add new action
        fAB.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            addOrder();
          }
        });
      }
    }

//    loadCiclo();

  }

  public void loadVideoGames() {
    // im not sure about this
    //Category cat =  new Category("FOO", "FOOOTEEEER");

    //VideoGame vg = new VideoGame("GTAV", "GRATVV", "Rockstar", 23, 45000, cat);



    System.out.println(videoGamesList);
    sp_videgames.setPrompt("Seleccione el Video Juego");

//    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<VideoGame>(this, R.layout.support_simple_spinner_dropdown_item, model.getVideoGamesList());
    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<VideoGame>(this, R.layout.support_simple_spinner_dropdown_item, videoGamesList);
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
//        index = model.getVideoGamesList().indexOf(c);
        index = model.getVideoGamesList().indexOf(c);
      }
    }

//    cantVideoGames = model.getVideoGamesList().size();

      cantVideoGames = model.getVideoGamesList().size();

//    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, model.getVideoGamesList());
    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, videoGamesList);
    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    sp_videgames.setAdapter(adapter);
  }

  public void loadCients() {
    // im not sure about this

    sp_videgames.setPrompt("Seleccione el Cliente");

//    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<VideoGame>(this, R.layout.support_simple_spinner_dropdown_item, model.getVideoGamesList());
    ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this, R.layout.support_simple_spinner_dropdown_item, clientsList);
    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    sp_clients.setAdapter(adapter);

    sp_clients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object item = adapterView.getItemAtPosition(i);
        if (item != null) {
          loadFilteredClient();
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {
      }
    });

  }

  private void loadFilteredClient() {

    int index = 0;

    for (Client c : model.getClientList()) {
      if (c.getNombre().equals(sp_clients.getSelectedItem().toString())) {
//        index = model.getVideoGamesList().indexOf(c);
        index = model.getClientList().indexOf(c);
      }
    }

//    cantVideoGames = model.getVideoGamesList().size();

    cantClients = model.getClientList().size();

//    ArrayAdapter<VideoGame> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, model.getClientList());
    ArrayAdapter<Client> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, clientsList);
    adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
    sp_videgames.setAdapter(adapter);

  }

  private void addOrder() {
    if (validateForm()) {
      String spinerclients=sp_clients.getSelectedItem().toString();
      String[] stringclient = spinerclients.split(",");
      String cedulaTemp = stringclient[6];
      String[] formatCed = cedulaTemp.split("=");
      String cedula=formatCed[1];
      String[] cedulafinal1 = cedula.split(" ");
      String cedulafinal=cedulafinal1[0];
      String spinerjuegos=sp_videgames.getSelectedItem().toString();
      String[] stringgames = spinerjuegos.split(",");
      String gameTemp=stringgames[0];
      String[] formatgame=gameTemp.split("=");
      String videojuego_id=formatgame[1].replaceAll("[^\\dA-Za-z]", "");


      tempUrl = apiUrl + "insertarPedido?fecha="+orDateFdl.getText().toString()+
                        "&cantidad="+ cantFdl.getText().toString() +
                        "&cedula="+ cedulafinal +
                        "&videojuego_id="+videojuego_id;

      tempUrl = tempUrl.replaceAll(" ", "%20");


      MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
      myAsyncTasks.execute();

      Order order = new Order(Integer.parseInt(id.getText().toString()),orDateFdl.getText().toString(), Integer.parseInt(cantFdl.getText().toString()),
                              0, null, null);

      System.out.println(order);

      Intent intent = new Intent(getBaseContext(), AdmOrderActivity.class);
      intent.putExtra("AddOrder", order);
      startActivity(intent);
      finish(); //prevent go back
    }

  }

  private void editOrder() {
    if (validateForm()) {
      String spinerclients=sp_clients.getSelectedItem().toString();
      String[] stringclient = spinerclients.split(",");
      String cedulaTemp = stringclient[6];
      String[] formatCed = cedulaTemp.split("=");
      String cedula=formatCed[1];
      String[] cedulafinal1 = cedula.split(" ");
      String cedulafinal=cedulafinal1[0];
      String spinerjuegos=sp_videgames.getSelectedItem().toString();
      String[] stringgames = spinerjuegos.split(",");
      String gameTemp=stringgames[0];
      String[] formatgame=gameTemp.split("=");
      String videojuego_id=formatgame[1].replaceAll("[^\\dA-Za-z]", "");

      tempUrl = apiUrl + "insertarPedido?fecha="+ orDateFdl.getText().toString()+
              "&cantidad="+ cantFdl.getText().toString() +
              "&cedula="+ cedulafinal +
              "&videojuego_id="+videojuego_id;
      tempUrl = tempUrl.replaceAll(" ", "%20");


      MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
      myAsyncTasks.execute();

      Order order = new Order(Integer.parseInt(id.getText().toString()),orDateFdl.getText().toString(), Integer.parseInt(cantFdl.getText().toString()),
              0, null, null);

      Intent intent = new Intent(getBaseContext(), AdmOrderActivity.class);
      intent.putExtra("AddOrder", order);
      startActivity(intent);
      finish(); //prevent go back
    }

  }

  public boolean validateForm() {
    int error = 0;
    if (TextUtils.isEmpty(this.cantFdl.getText())) {
      cantFdl.setError("Field required!");
      error++;
    }
    if (TextUtils.isEmpty(this.orDateFdl.getText())) {
      orDateFdl.setError("Field required!");
      error++;
    }
    if (sp_clients.getSelectedItem().toString().equals("")) {
      error++;
    }
    if (sp_videgames.getSelectedItem().toString().equals("")) {
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
