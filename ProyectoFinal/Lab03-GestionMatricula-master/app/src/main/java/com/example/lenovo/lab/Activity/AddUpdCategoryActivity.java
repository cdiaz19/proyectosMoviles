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
import com.example.lenovo.lab.R;

public class AddUpdCategoryActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_categoria);

        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCarreraBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCar);
        nomFld = findViewById(R.id.nombreAddUpdCar);
        codFld.setText("");
        nomFld.setText("");

        //receiving data from admCarreraActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Categoria aux = (Categoria) getIntent().getSerializableExtra("carrera");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCategoria();
                    }
                });
            } else {         // is adding new Categoria object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCategoria();
                    }
                });
            }
        }
    }

    public void addCategoria() {
        if (validateForm()) {
            //do something
            Categoria car = new Categoria(codFld.getText().toString(), nomFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCategoriaActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCategoria() {
        if (validateForm()) {
            Categoria car = new Categoria(codFld.getText().toString(), nomFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCategoriaActivity.class);
            //sending carrera data
            intent.putExtra("editCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
