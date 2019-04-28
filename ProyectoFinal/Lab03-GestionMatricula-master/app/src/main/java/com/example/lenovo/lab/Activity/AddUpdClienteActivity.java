package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Cliente;
import com.example.lenovo.lab.R;

public class AddUpdClienteActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText nomFld;
    private EditText cedFld;
    private EditText emailFld;
    private EditText telFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_cliente);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdAlumnoBtn);

        //cleaning stuff
        nomFld = findViewById(R.id.nombreAddUpdAlum);
        cedFld = findViewById(R.id.cedulaAddUpdAlum);
        emailFld = findViewById(R.id.emailAddUpdAlum);
        telFld=findViewById(R.id.telefonoAddUpdAlum);
        nomFld.setText("");
        cedFld.setText("");
        emailFld.setText("");
        telFld.setText("");

        //receiving data from admAlumnoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Cliente aux = (Cliente) getIntent().getSerializableExtra("alumno");
                cedFld.setText(aux.getCedula());
                cedFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                emailFld.setText(aux.getEmail());
                telFld.setText(Integer.toString(aux.getTelefono()));

                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCliente();
                    }
                });
            } else {         // is adding new Categoria object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCliente();
                    }
                });
            }
        }
    }

    public void addCliente() {
        if (validateForm()) {
            //do something
            Cliente prof = new Cliente(cedFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()),
                    emailFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmAlumnoActivity.class);
            //sending Cliente data
            intent.putExtra("addalumno", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCliente() {
        if (validateForm()) {
            Cliente prof = new Cliente(cedFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()),
                    emailFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmAlumnoActivity.class);
            //sending Cliente data
            intent.putExtra("editalumno", prof);
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
        if (TextUtils.isEmpty(this.cedFld.getText())) {
            cedFld.setError("Cedula requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.emailFld.getText())) {
            emailFld.setError("Email requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.telFld.getText())) {
            telFld.setError("Telefono requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
