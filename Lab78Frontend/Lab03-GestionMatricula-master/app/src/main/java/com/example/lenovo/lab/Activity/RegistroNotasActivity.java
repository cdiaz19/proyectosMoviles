package com.example.lenovo.lab.Activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;

public class RegistroNotasActivity extends AppCompatActivity {

    private Spinner sp_grupo;
    private Spinner sp_estudiante;
    private EditText nota;
    private ModelData model;
    private FloatingActionButton saveBtn;
    private Grupo g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_notas);

        sp_grupo = (Spinner) findViewById(R.id.sp_grupo);
        sp_estudiante = (Spinner) findViewById(R.id.sp_estudiante);
        nota = (EditText) findViewById(R.id.notaEditT);
        saveBtn = (FloatingActionButton) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarNota();
            }
        });

        model = new ModelData();
        g = model.getGrupoList().get(0);

        loadGrupos();
        loadFilteredEstudiantes();
    }

    public void loadGrupos(){
        ArrayAdapter<Grupo> adapter = new ArrayAdapter<Grupo>(this, R.layout.support_simple_spinner_dropdown_item, model.getGrupoList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_grupo.setAdapter(adapter);

        sp_grupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if (item != null) {
                    g = model.getGrupoList().get(i);
                    loadFilteredEstudiantes();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadFilteredEstudiantes(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, g.getEstudiantes());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_estudiante.setAdapter(adapter);

        sp_estudiante.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                if (item != null) {
                    loadNota();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadNota(){
        int est = sp_estudiante.getSelectedItemPosition();
        nota.setText(g.getNotas().get(est) + "");
    }

    private void guardarNota(){
        int est = sp_estudiante.getSelectedItemPosition();
        if(nota.getText().toString().length() > 1) {
            model.getGrupoList().get(sp_grupo.getSelectedItemPosition()).setNota(Double.parseDouble(nota.getText().toString()), est);
            Toast.makeText(getApplicationContext(), "Nota registrada", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Intente nuevamente", Toast.LENGTH_SHORT).show();
        }
    }
}
