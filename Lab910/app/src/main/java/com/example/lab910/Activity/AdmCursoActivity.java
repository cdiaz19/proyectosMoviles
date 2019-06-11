package com.example.lab910.Activity;

import android.database.Cursor;
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
import com.example.lab910.LogicaDeNegocio.Curso;
import com.example.lab910.LogicaDeNegocio.Estudiante;
import com.example.lab910.R;

import java.util.ArrayList;

public class AdmCursoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

  private Button btnCrear;
  private Button btnVer;
  private Button btnEliminar;
  private Button btnActualizar;
  private EditText editDescripcion;
  private EditText editCreditos;
  private EditText txtDescripcion;
  private EditText txtCreditos;

  private Spinner spinEstudaintes;
  private ArrayAdapter spinnerAdapterEstudainte;

  private Spinner spinCursos;
  private ArrayAdapter spinnerAdapterCursos;

  //Lista de Estudaintes
  private ArrayList<Estudiante> listaEstudiantes;
  private Estudiante e;

  //Lista de Cursos y Curso actual
  private ArrayList<Curso> listaCursos;
  private Curso c;

  //Controlador de bases de datos
  private DBHelper db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adm_curso);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //Inicializamos los elementos de la interfaz
    editDescripcion = (EditText) findViewById(R.id.editDescripcion);
    editCreditos = (EditText) findViewById(R.id.editCredito);

    txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
    txtCreditos = (EditText)findViewById(R.id.txtCredito);

    btnCrear = (Button)findViewById(R.id.btnCrearCurso);
    btnVer = (Button)findViewById(R.id.btnVerCurso);
    btnEliminar = (Button)findViewById(R.id.btnEliminarCurso);
    btnActualizar = (Button)findViewById(R.id.btnActualizarCurso);

    btnCrear.setOnClickListener(this);
    btnVer.setOnClickListener(this);
    btnEliminar.setOnClickListener(this);
    btnActualizar.setOnClickListener(this);

    //Iniciamos el controlador de la base de datos
    db = new DBHelper(this);

    //Iniciamos el spinner y la lista de spinEstudaintes
    spinEstudaintes =(Spinner) findViewById(R.id.spinEstudiantes);
    listaEstudiantes = db.getEstudiantes();

    //Iniciamos el spinner y la lista de spinCursos
    spinCursos = (Spinner) findViewById(R.id.spinCursos);
    listaCursos = db.getCursos();

    //Creamos el adapter y lo asociamos al spinner Estudiante
    spinnerAdapterEstudainte =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaEstudiantes);
    spinEstudaintes.setAdapter(spinnerAdapterEstudainte);
    spinEstudaintes.setOnItemSelectedListener(this);

    //Creamos el adapter y lo asociamos al spinner Curso
    spinnerAdapterCursos =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursos);
    spinCursos.setAdapter(spinnerAdapterCursos);
    spinCursos.setOnItemSelectedListener(this);
  }

  @Override
  public void onClick(View v) {
    //Acciones de cada boton
    switch(v.getId()){
      case R.id.btnCrearCurso:
        //Insertamos un nuevo elemento en base de datos

        String[] parts = ((String) spinEstudaintes.getSelectedItem().toString()).split(" ");
        Estudiante estudiante = db.buscarNombreEstudiante(parts[2]);

        db.insertarCurso(editDescripcion.getText().toString(), Integer.parseInt(editCreditos.getText().toString()), estudiante.getId());
//
//        //Actualizamos la lista de cursos
//        listaCursos = db.getCursos();
//
//        // Actualizamos el adapter y lo asociamos de nuevo al spinner
//        spinnerAdapterCursos = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursos);
//        spinCursos.setAdapter(spinnerAdapterCursos);
//
//        //Limpiamos el formulario
//        editDescripcion.setText("");
//        editCreditos.setText("");
        break;

      case R.id.btnVerCurso:
        //Si hay algun Estudiante seleccionado mostramos sus valores en la parte inferior
//        if(c != null) {
//          txtNombre.setText(c.getNombre());
//          txtApellido.setText(c.getApellidos());
//          txtEdad.setText(String.valueOf(c.getEdad()));
//        }
        break;

      case R.id.btnActualizarCurso:
//        //Si hay algun Estudiante seleccionado mostramos sus valores en la parte inferior
//        db.actualizarEstudiante(c.getId(), txtNombre.getText().toString(), txtApellido.getText().toString(), Integer.parseInt(txtEdad.getText().toString()));
//
//        //Actualizamos la lista de comentarios
//        lista = db.getEstudiantes();
//
//        //Actualizamos el adapter y lo asociamos de nuevo al spinner
//        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
//        spinEstudaintes.setAdapter(spinnerAdapter);
//
//        //Limpiamos el formulario
//        txtNombre.setText("");
//        txtApellido.setText("");
//        txtEdad.setText("");

        break;

      case R.id.btnEliminarCurso:
        //Si hay algun Estudiante seleccionado lo borramos de la base de datos y actualizamos el spinner
//        if(c!=null) {
//          db.borrarEstudiante(c.getId());
//          lista = db.getEstudiantes();
//          spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);
//          spinEstudaintes.setAdapter(spinnerAdapter);
//
//          //Limpiamos los datos del panel inferior
//          txtNombre.setText("");
//          txtApellido.setText("");
//          txtEdad.setText("");
//          //Eliminamos el spinEstudaintes actual puesto que ya no existe en base de datos
//          c=null;
//        }
//        break;
    }

  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
