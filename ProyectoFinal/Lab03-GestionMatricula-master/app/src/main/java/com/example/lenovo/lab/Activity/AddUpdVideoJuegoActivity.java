package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Categoria;
import com.example.lenovo.lab.LogicaNeg.VideoJuego;
import com.example.lenovo.lab.R;

public class AddUpdVideoJuegoActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable;
    private EditText cicFld;
    private EditText nomFld;
    private EditText catVJFld;
    private EditText curFld;
    private EditText numFld;
    private EditText proFld;
    private VideoJuego critFiltG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_videojuego);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdGrupoBtn);

        //cleaning stuff
        cicFld = findViewById(R.id.cicloAddUpdGru);
        nomFld = findViewById(R.id.nomCatAddUpdGru);
        catVJFld = findViewById(R.id.catVJAddUpdGru);
        curFld = findViewById(R.id.cursoAddUpdGru);
        numFld = findViewById(R.id.numeroAddUpdGru);
        proFld = findViewById(R.id.profesorAddUpdGru);

        //receiving data from admGrupoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            critFiltG = (VideoJuego) getIntent().getSerializableExtra("filtGrupo");
            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                VideoJuego aux = (VideoJuego) getIntent().getSerializableExtra("grupo");
                cicFld.setText(aux.getCategoria().toString());
                cicFld.setEnabled(false);
                curFld.setText(aux.getCodigo());
                curFld.setEnabled(false);
                numFld.setText(aux.getNombre());
                numFld.setEnabled(false);
                proFld.setText(aux.getPrecio());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editVideoJuego();
                    }
                });
            } else {         // is adding new Categoria object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addVideoJuego();
                    }
                });
            }
        }
    }

    public void addVideoJuego() {
        if (validateForm()) {
            //do something
            String largoC = cicFld.getText().toString();
            String nombre = nomFld.getText().toString();
            VideoJuego gru = new VideoJuego(catVJFld.getText().toString(), curFld.getText().toString(),
                    Integer.parseInt(numFld.getText().toString()), "", 0, proFld.getText().toString(), new Categoria(largoC, nombre));
            Toast.makeText(getApplicationContext(), gru.getNombre(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), AdmVideoJuegoActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", gru);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editVideoJuego() {
        if (validateForm()) {
            String largoC = cicFld.getText().toString();
            String nombre = nomFld.getText().toString();
            VideoJuego gru = new VideoJuego(catVJFld.getText().toString(), curFld.getText().toString(),
                    Integer.parseInt(numFld.getText().toString()), "", 0, proFld.getText().toString(), new Categoria(largoC, nombre));
            Toast.makeText(getApplicationContext(), gru.getNombre(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), AdmVideoJuegoActivity.class);
            //sending curso data
            intent.putExtra("editGrupo", gru);
            intent.putExtra("filtGrupo", critFiltG);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.curFld.getText())) {
            curFld.setError("Es requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.numFld.getText())) {
            numFld.setError("Es requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.cicFld.getText())) {
            cicFld.setError("Es requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.numFld.getText())) {
            proFld.setError("Es requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}