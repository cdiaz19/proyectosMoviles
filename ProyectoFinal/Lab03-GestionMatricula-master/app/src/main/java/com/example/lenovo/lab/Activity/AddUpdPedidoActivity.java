package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Pedido;
import com.example.lenovo.lab.R;

public class AddUpdPedidoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText añoFld;
    private EditText numFld;
    private EditText iniFld;
    private EditText finFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_pedido);
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
                Pedido aux = (Pedido) getIntent().getSerializableExtra("ciclo");
                añoFld.setText(Integer.toString(aux.getCantidad()));
                numFld.setText(aux.getNombre());
                iniFld.setText(aux.getPrecio());
                finFld.setText(aux.getRentor());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editPedido();
                    }
                });
            } else {         // is adding new Categoria object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addPedido();
                    }
                });
            }
        }
    }

    public void addPedido() {
        if (validateForm()) {
            //do something
            Pedido prof = new Pedido(Integer.parseInt(añoFld.getText().toString()), numFld.getText().toString(),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmPedidoActivity.class);
            //sending Pedido data
            intent.putExtra("addciclo", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editPedido() {
        if (validateForm()) {
            Pedido prof = new Pedido(Integer.parseInt(añoFld.getText().toString()), numFld.getText().toString(),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmPedidoActivity.class);
            //sending Pedido data
            intent.putExtra("editciclo", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.añoFld.getText())) {
            añoFld.setError("Es requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.numFld.getText())) {
            numFld.setError("Es requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.iniFld.getText())) {
            iniFld.setError("Es requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.finFld.getText())) {
            finFld.setError("Es requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
