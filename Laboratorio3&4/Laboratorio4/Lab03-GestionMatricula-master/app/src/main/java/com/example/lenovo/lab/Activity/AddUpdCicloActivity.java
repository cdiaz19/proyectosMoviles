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
import com.example.lenovo.lab.R;

public class AddUpdCicloActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText añoFld;
    private EditText numFld;
    private EditText iniFld;
    private EditText finFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_ciclo);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCicloBtn);

        //cleaning stuff
        añoFld = findViewById(R.id.añoAddUpdCi);
        numFld = findViewById(R.id.numeroAddUpdCi);
        iniFld = findViewById(R.id.finicioAddUpdCi);
        finFld=findViewById(R.id.ffinalAddUpdCi);
        añoFld.setText("");
        numFld.setText("");
        iniFld.setText("");
        finFld.setText("");

        //receiving data from admCicloActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Ciclo aux = (Ciclo) getIntent().getSerializableExtra("ciclo");
                añoFld.setText(Integer.toString(aux.getAño()));
                añoFld.setEnabled(false);
                numFld.setText(aux.getNumero());
                iniFld.setText(aux.getFinicio());
                finFld.setText(aux.getFfinal());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCiclo();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCiclo();
                    }
                });
            }
        }
    }

    public void addCiclo() {
        if (validateForm()) {
            //do something
            Ciclo prof = new Ciclo(Integer.parseInt(añoFld.getText().toString()), numFld.getText().toString(),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCicloActivity.class);
            //sending Ciclo data
            intent.putExtra("addciclo", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCiclo() {
        if (validateForm()) {
            Ciclo prof = new Ciclo(Integer.parseInt(añoFld.getText().toString()), numFld.getText().toString(),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCicloActivity.class);
            //sending Ciclo data
            intent.putExtra("editciclo", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.añoFld.getText())) {
            añoFld.setError("Año requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.numFld.getText())) {
            numFld.setError("Numero requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.iniFld.getText())) {
            iniFld.setError("Inicio requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.finFld.getText())) {
            finFld.setError("Final requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
