package com.example.lab910.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lab910.Helper.DBHelper;
import com.example.lab910.LogicaDeNegocio.Estudiante;
import com.example.lab910.R;

import java.util.ArrayList;

public class AdmEstudianteActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

  private Button btnCrear;
  private Button btnVer;
  private Button btnEliminar;
  private Button btnActualizar;
  private EditText editNombre;
  private EditText editApellido;
  private EditText editEdad;
  private EditText txtNombre;
  private EditText txtApellido;
  private EditText txtEdad;

  private Spinner spinEstudaintes;
  private ArrayAdapter spinnerAdapter;

  //Lista de Estudaintes y Estudiante actual
  private ArrayList<Estudiante> lista;
  private Estudiante c;

  //Controlador de bases de datos
  private DBHelper db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adm_estudiante);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //Inicializamos los elementos de la interfaz
    editNombre = (EditText) findViewById(R.id.editNombre);
    editApellido = (EditText) findViewById(R.id.editApellido);
    editEdad = (EditText) findViewById(R.id.editEdad);

    txtNombre = (EditText) findViewById(R.id.txtNombre);
    txtApellido = (EditText)findViewById(R.id.txtApellido);
    txtEdad = (EditText) findViewById(R.id.txtEdad);

    btnCrear = (Button)findViewById(R.id.btnCrear);
    btnVer = (Button)findViewById(R.id.btnVer);
    btnEliminar = (Button)findViewById(R.id.btnEliminar);
    btnActualizar = (Button)findViewById(R.id.btnActualizar);

    btnCrear.setOnClickListener(this);
    btnVer.setOnClickListener(this);
    btnEliminar.setOnClickListener(this);
    btnActualizar.setOnClickListener(this);

    //Iniciamos el controlador de la base de datos
    db = new DBHelper(this);

    //Iniciamos el spinner y la lista de spinEstudaintes
    spinEstudaintes =(Spinner) findViewById(R.id.spinEstudiantes);
    lista = db.getEstudiantes();

    //Creamos el adapter y lo asociamos al spinner
    spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
    spinEstudaintes.setAdapter(spinnerAdapter);
    spinEstudaintes.setOnItemSelectedListener(this);
  }

  @Override
  public void onClick(View v) {
    //Acciones de cada boton
    switch(v.getId()){
      case R.id.btnCrear:
        //Insertamos un nuevo elemento en base de datos
        db.insertarEstudiante(editNombre.getText().toString(), editApellido.getText().toString(), Integer.parseInt(editEdad.getText().toString()));

        //Actualizamos la lista de comentarios
        lista = db.getEstudiantes();

        //Actualizamos el adapter y lo asociamos de nuevo al spinner
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        spinEstudaintes.setAdapter(spinnerAdapter);

        //Limpiamos el formulario
        editNombre.setText("");
        editApellido.setText("");
        editEdad.setText("");
        break;

      case R.id.btnVer:
        //Si hay algun Estudiante seleccionado mostramos sus valores en la parte inferior
        if(c != null) {
          txtNombre.setText(c.getNombre());
          txtApellido.setText(c.getApellidos());
          txtEdad.setText(String.valueOf(c.getEdad()));
        }
        break;

      case R.id.btnActualizar:
        //Si hay algun Estudiante seleccionado mostramos sus valores en la parte inferior
        db.actualizarEstudiante(c.getId(), txtNombre.getText().toString(), txtApellido.getText().toString(), Integer.parseInt(txtEdad.getText().toString()));

        //Actualizamos la lista de comentarios
        lista = db.getEstudiantes();

        //Actualizamos el adapter y lo asociamos de nuevo al spinner
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        spinEstudaintes.setAdapter(spinnerAdapter);

        //Limpiamos el formulario
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");

        break;

      case R.id.btnEliminar:
        //Si hay algun Estudiante seleccionado lo borramos de la base de datos y actualizamos el spinner
        if(c != null) {

          db.borrarEstudiante(c.getId());
          lista = db.getEstudiantes();
          spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
          spinEstudaintes.setAdapter(spinnerAdapter);

          //Limpiamos los datos del panel inferior
          txtNombre.setText("");
          txtApellido.setText("");
          txtEdad.setText("");

          //Eliminamos el spinEstudaintes actual puesto que ya no existe en base de datos
          c = null;
        }
        break;
    }
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    if (parent.getId() == R.id.spinEstudiantes) {
      //Si hay elementos en la base de datos, establecemos el estudainte actual a partir del
      //indice del elemento seleccionado en el spinner

      if(lista.size()>0) {
        c = lista.get(position);
      }
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
  }
}
