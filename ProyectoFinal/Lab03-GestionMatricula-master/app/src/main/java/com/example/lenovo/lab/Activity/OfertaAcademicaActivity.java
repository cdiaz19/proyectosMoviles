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

import com.example.lenovo.lab.LogicaNeg.Categoria;
import com.example.lenovo.lab.LogicaNeg.Pedido;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.LogicaNeg.VideoJuego;
import com.example.lenovo.lab.R;

public class OfertaAcademicaActivity extends AppCompatActivity {

    private Spinner sp_carreras;
    private Spinner sp_ciclos;
    private Spinner sp_cursos;
    private FloatingActionButton fAB;
    private ModelData model;
    private int cantCursos = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica);

        sp_carreras = (Spinner) findViewById(R.id.sp_carreras);
        fAB = (FloatingActionButton) findViewById(R.id.addUpdGrupoBtn);
        fAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGrupos();
            }
        });

        model = new ModelData();

        loadCarreras();
    }

    public void goToGrupos() {
        int cantCursos =  model.getCategoriaList().size();

        if (cantCursos < 1) {
            Toast.makeText(getApplicationContext(), "No hay cursos en esta carrera", Toast.LENGTH_SHORT).show();
        } else {                      // TODO change this
            String largo = sp_carreras.getSelectedItem().toString();
            VideoJuego gru = new VideoJuego(sp_cursos.getSelectedItem().toString(), "", 0, "", 0, null, new Categoria("aa", largo));
            Intent intent = new Intent(getBaseContext(), AdmVideoJuegoActivity.class);
            intent.putExtra("filtGrupo", gru);
            startActivity(intent);
        }
    }

    public void loadCarreras() {
        // im not sure about this
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this, R.layout.support_simple_spinner_dropdown_item, model.getCategoriaList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_carreras.setAdapter(adapter);

        sp_carreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if (item != null) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
