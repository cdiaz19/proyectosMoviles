package com.example.lab910.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.lab910.LogicaDeNegocio.CursoEstudiante;
import com.example.lab910.LogicaDeNegocio.Estudiante;
import com.example.lab910.R;

import java.util.ArrayList;

public class AdmCursoEstudianteActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

  private Button btnCrear;
  private Button btnVer;
  private Button btnEliminar;
  private Button btnActualizar;

  private EditText txtEstudiante;
  private EditText txtCurso;

  private Spinner spinCursos;
  private ArrayAdapter spinnerAdapterCursos;

  private Spinner spinEstudiantes;
  private ArrayAdapter spinnerAdapterEstudiantes;

  private Spinner spinCursosEstudiantes;
  private ArrayAdapter spinnerAdapterCursosEstudiantes;

  //Lista de CursosEstudiantes y Curso actual
  private ArrayList<Curso> listaCursos;
  private Curso c;

  //Lista de CursosEstudiantes y Curso actual
  private ArrayList<Estudiante> listaEstudiantes;
  private Estudiante e;

  //Lista de CursosEstudiantes y Curso actual
  private ArrayList<CursoEstudiante> listaCursosEstudiantes;
  private CursoEstudiante ce;

  //Controlador de bases de datos
  private DBHelper db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adm_curso_estudiante);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    btnCrear = (Button)findViewById(R.id.btnCrearCursoEstudiante);
    btnVer = (Button)findViewById(R.id.btnVerCursoEstudiante);
    btnEliminar = (Button)findViewById(R.id.btnEliminarCursoEstudiante);
    btnActualizar = (Button)findViewById(R.id.btnActualizarCursoEstudiante);

    txtEstudiante = (EditText) findViewById(R.id.txtEstudianteCurso);
    txtCurso = (EditText) findViewById(R.id.txtCursoCurso);

    btnCrear.setOnClickListener(this);
    btnVer.setOnClickListener(this);
    btnEliminar.setOnClickListener(this);
    btnActualizar.setOnClickListener(this);

    //Iniciamos el controlador de la base de datos
    db = new DBHelper(this);

    //Iniciamos el spinner y la lista de spinCursos
    spinCursos = (Spinner) findViewById(R.id.spinCursos);
    listaCursos = db.getCursos();

    //Creamos el adapter y lo asociamos al spinner Curso
    spinnerAdapterCursos =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursos);
    spinCursos.setAdapter(spinnerAdapterCursos);
    spinCursos.setOnItemSelectedListener(this);

    //Iniciamos el spinner y la lista de spinCursos
    spinEstudiantes = (Spinner) findViewById(R.id.spinEstudiantes);
    listaEstudiantes = db.getEstudiantes();

    //Creamos el adapter y lo asociamos al spinner Curso
    spinnerAdapterEstudiantes =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaEstudiantes);
    spinEstudiantes.setAdapter(spinnerAdapterEstudiantes);
    spinEstudiantes.setOnItemSelectedListener(this);

    //Iniciamos el spinner y la lista de spinCursos
    spinCursosEstudiantes = (Spinner) findViewById(R.id.spinCursosEstudiantes);
    listaCursosEstudiantes = db.getCursoEstudiante();

    //Creamos el adapter y lo asociamos al spinner Curso
    spinnerAdapterCursosEstudiantes =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursosEstudiantes);
    spinCursosEstudiantes.setAdapter(spinnerAdapterCursosEstudiantes);
    spinCursosEstudiantes.setOnItemSelectedListener(this);
  }

  @Override
  public void onClick(View v) {
    //Acciones de cada boton
    switch(v.getId()){
      case R.id.btnCrearCursoEstudiante:
        //Insertamos un nuevo elemento en base de datos
        String[] parts = ((String) spinEstudiantes.getSelectedItem().toString()).split(" ");
        String[] partss = ((String) spinCursos.getSelectedItem().toString()).split(" ");

        db.insertarCursoEstudiante(parts[2], partss[1]);

        //Actualizamos la lista de cursos
        listaCursosEstudiantes = db.getCursoEstudiante();

        // Actualizamos el adapter y lo asociamos de nuevo al spinner
        spinnerAdapterCursosEstudiantes = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursosEstudiantes);
        spinCursosEstudiantes.setAdapter(spinnerAdapterCursosEstudiantes);

        break;
//
      case R.id.btnVerCursoEstudiante:
        System.out.println("HIJO DE PUTAAA!!!");
//        //Si hay algun Curso seleccionado mostramos sus valores en la parte inferior
        if(ce != null) {

          System.out.println("=====");

          System.out.println(ce);

          System.out.println("=====");


          txtEstudiante.setText(ce.getEstudiante());
          txtCurso.setText(ce.getCurso());
        }
        break;

      case R.id.btnActualizarCursoEstudiante:
        //Si hay algun Curso seleccionado mostramos sus valores en la parte inferior
        String[] partsss = ((String) spinEstudiantes.getSelectedItem().toString()).split(" ");
//        String[] partss = ((String) spinCursos.getSelectedItem().toString()).split(" ");
//
//        db.actualizarCurso(c.getId(), txtDescripcion.getText().toString(), Integer.parseInt(txtCredito.getText().toString()));
//
//        //Actualizamos la lista de comentarios
//        listaCursos = db.getCursos();
//
//        //Actualizamos el adapter y lo asociamos de nuevo al spinner
//        spinnerAdapterCursos = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursos);
//        spinCursos.setAdapter(spinnerAdapterCursos);
//
//        //Limpiamos el formulario
        break;

      case R.id.btnEliminarCursoEstudiante:
        //Si hay algun Curso seleccionado lo borramos de la base de datos y actualizamos el spinner
        if(ce!=null) {

          db.borrarCursoEstudiante(ce.getId());

          //Actualizamos la lista de cursos
          listaCursosEstudiantes = db.getCursoEstudiante();

          // Actualizamos el adapter y lo asociamos de nuevo al spinner
          spinnerAdapterCursosEstudiantes = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaCursosEstudiantes);
          spinCursosEstudiantes.setAdapter(spinnerAdapterCursosEstudiantes);

          //Eliminamos el spinCursos actual puesto que ya no existe en base de datos
          ce=null;
        }
        break;
    }
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    if (parent.getId() == R.id.spinCursosEstudiantes) {
      //Si hay elementos en la base de datos, establecemos el curso actual a partir del
      //indice del elemento seleccionado en el spinner

      if(listaCursosEstudiantes.size() > 0) {
        ce = listaCursosEstudiantes.get(position);
      }
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
