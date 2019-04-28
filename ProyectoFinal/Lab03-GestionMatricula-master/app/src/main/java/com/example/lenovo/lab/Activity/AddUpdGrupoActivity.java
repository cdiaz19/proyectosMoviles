package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.R;

public class AddUpdGrupoActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable;
    private EditText cicFld;
    private EditText curFld;
    private EditText numFld;
    private EditText horFld;
    private EditText proFld;
    private Grupo critFiltG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_grupo);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdGrupoBtn);

        //cleaning stuff
        cicFld = findViewById(R.id.cicloAddUpdGru);
        curFld = findViewById(R.id.cursoAddUpdGru);
        numFld = findViewById(R.id.numeroAddUpdGru);
        horFld = findViewById(R.id.horarioAddUpdGru);
        proFld = findViewById(R.id.profesorAddUpdGru);

        //receiving data from admGrupoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            critFiltG = (Grupo) getIntent().getSerializableExtra("filtGrupo");
            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Grupo aux = (Grupo) getIntent().getSerializableExtra("grupo");
                cicFld.setText(aux.getCiclo().toString());
                cicFld.setEnabled(false);
                curFld.setText(aux.getCurso());
                curFld.setEnabled(false);
                numFld.setText(aux.getNumero());
                numFld.setEnabled(false);
                horFld.setText(aux.getHorario());
                proFld.setText(aux.getProfesor());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editGrupo();
                    }
                });
            } else {         // is adding new Categoria object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addGrupo();
                    }
                });
            }
        }
    }

    public void addGrupo() {
        if (validateForm()) {
            //do something
            int largoC = cicFld.getText().toString().length();
            int año = Integer.parseInt(cicFld.getText().toString().substring(largoC-4, largoC));
            String numero = "Primer";
            if(cicFld.getText().toString().substring(0,1).equals("S") || cicFld.getText().toString().substring(0,1).equals("s"))
                numero = "Segundo";
            Grupo gru = new Grupo(new Ciclo(año, numero), curFld.getText().toString(), numFld.getText().toString(),
                    horFld.getText().toString(), proFld.getText().toString(), null, null);
            Intent intent = new Intent(getBaseContext(), AdmGrupoActivity.class);
            //sending curso data
            intent.putExtra("addGrupo", gru);
            intent.putExtra("filtGrupo", critFiltG);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editGrupo() {
        if (validateForm()) {
            Grupo gru = new Grupo(new Ciclo(2000, cicFld.getText().toString()), curFld.getText().toString(), numFld.getText().toString(),
                    horFld.getText().toString(), proFld.getText().toString(), null, null);
            Intent intent = new Intent(getBaseContext(), AdmGrupoActivity.class);
            //sending curso data
            intent.putExtra("editGrupo", gru);
            intent.putExtra("filtGrupo", critFiltG);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.horFld.getText())) {
            horFld.setError("Horario requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.proFld.getText())) {
            proFld.setError("Profesor requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}