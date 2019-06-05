package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Carrera;
import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.Curso;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;

public class OfertaAcademicaActivity extends AppCompatActivity {

    private Spinner sp_carreras;
    private Spinner sp_ciclos;
    private Spinner sp_cursos;
    private FloatingActionButton fAB;
    private ModelData model;
    private int cantCursos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica);

        sp_carreras = (Spinner) findViewById(R.id.sp_carreras);
        sp_ciclos = (Spinner) findViewById(R.id.sp_ciclos);
        sp_cursos = (Spinner) findViewById(R.id.sp_cursos);
        fAB = (FloatingActionButton) findViewById(R.id.addUpdGrupoBtn);
        fAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGrupos();
            }
        });

        model = new ModelData();

        loadCarreras();
        loadCiclo();
        loadFilteredCurso();
    }

    public void goToGrupos() {
        if (cantCursos < 1) {
            Toast.makeText(getApplicationContext(), "No hay cursos en esta carrera", Toast.LENGTH_SHORT).show();
        } else {                      // TODO change this
            int largo = sp_ciclos.getSelectedItem().toString().length();
            int año = Integer.parseInt(sp_ciclos.getSelectedItem().toString().substring(largo-4, largo));
            String numero = "Primer";
            if(sp_ciclos.getSelectedItem().toString().substring(0,1).equals("S"))
                numero = "Segundo";
            Grupo gru = new Grupo(new Ciclo(año, numero), sp_cursos.getSelectedItem().toString(), "", "", "", null, null);
            Intent intent = new Intent(getBaseContext(), AdmGrupoActivity.class);
            intent.putExtra("filtGrupo", gru);
            startActivity(intent);
        }
    }

    public void loadCarreras() {
        // im not sure about this
        ArrayAdapter<Carrera> adapter = new ArrayAdapter<Carrera>(this, R.layout.support_simple_spinner_dropdown_item, model.getCarreraList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_carreras.setAdapter(adapter);

        sp_carreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if (item != null) {
                    loadFilteredCurso();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadCiclo() {
        // im not sure about this
        ArrayAdapter<Ciclo> adapter = new ArrayAdapter<Ciclo>(this, R.layout.support_simple_spinner_dropdown_item, model.getCicloList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_ciclos.setAdapter(adapter);
    }

    private void loadFilteredCurso() {

        //get the Cursos of that Carrera
        int index = 0;

        for (Carrera c : model.getCarreraList()) {
            if (c.getNombre().equals(sp_carreras.getSelectedItem().toString())) {
                index = model.getCarreraList().indexOf(c);
            }
        }

        cantCursos = model.getCarreraList().get(index).getCursos().size();

        ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(this, R.layout.support_simple_spinner_dropdown_item, model.getCarreraList().get(index).getCursos());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_cursos.setAdapter(adapter);
    }

}
