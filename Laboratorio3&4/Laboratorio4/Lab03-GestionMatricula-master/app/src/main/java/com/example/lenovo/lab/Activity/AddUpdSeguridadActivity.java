package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.lab.R;
import com.example.lenovo.lab.LogicaNeg.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AddUpdSeguridadActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText correoFld;
    private EditText passFld;
    private EditText cedulaFld;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_seguridad);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdSeguridadBtn);

        //cleaning stuff
        correoFld = findViewById(R.id.emailAddUpdSeguridad);
        passFld = findViewById(R.id.contrasenaAddUpdSeguridad);
        cedulaFld = findViewById(R.id.cedulaAddUpdSeguridad);
        correoFld.setText("");
        passFld.setText("");

        //spinner
        initSpinner();

        //receiving data from admProfesorActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Usuario aux = (Usuario) getIntent().getSerializableExtra("usuario");
                cedulaFld.setEnabled(false);
                correoFld.setEnabled(false);
                correoFld.setText(aux.getCorreo());
                passFld.setText(aux.getContraseña());
                cedulaFld.setText(aux.getCedula());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editUsuario();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addUsuario();
                    }
                });
            }
        }
    }

    public void initSpinner() {
        spinner = (Spinner) findViewById(R.id.privilegioSpinnerFld);
        List<String> list = new ArrayList<String>();
        list.add("administrador");
        list.add("matriculador");
        list.add("ninguno");
        spinner.setPrompt(getString(R.string.privileges_prompt));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addUsuario() {
        if (validateForm()) {
            //do something
            Usuario user = new Usuario(correoFld.getText().toString(), passFld.getText().toString(),
                    spinner.getSelectedItem().toString(), cedulaFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmSeguridadActivity.class);
            //sending Usuario data
            intent.putExtra("addUsuario", user);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editUsuario() {
        if (validateForm()) {
            Usuario user = new Usuario(correoFld.getText().toString(), passFld.getText().toString(),
                    spinner.getSelectedItem().toString(), cedulaFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmSeguridadActivity.class);
            //sending User data
            intent.putExtra("editUsuario", user);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.correoFld.getText())) {
            correoFld.setError("Correo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.passFld.getText())) {
            passFld.setError("Contraseña requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.cedulaFld.getText())) {
            cedulaFld.setError("Cedula requerida");
            error++;
        }
        if (spinner.getSelectedItem().toString().equals("")) {
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}