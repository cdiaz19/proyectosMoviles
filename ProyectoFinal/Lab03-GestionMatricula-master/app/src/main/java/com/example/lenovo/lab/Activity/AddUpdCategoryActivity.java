package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.R;

public class AddUpdCategoryActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_category);
        editable = true;

        fBtn = findViewById(R.id.addUpdCategoryBtn);
        codFld = findViewById(R.id.codeInAddUpdCar);
        nomFld = findViewById(R.id.nameInAddUpdCar);
        codFld.setText("");
        nomFld.setText("");

        //receiving data from admCategoryActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Category aux = (Category) getIntent().getSerializableExtra("category");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());

                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCategory();
                    }
                });
            } else {
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCategory();
                    }
                });
            }
        }
    }

    public void addCategory() {
        if (validateForm()) {

            Category category = new Category(codFld.getText().toString(), nomFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCategoryActivity.class);
            intent.putExtra("addCategory", category);
            startActivity(intent);
            finish();
        }
    }

    public void editCategory() {
        if (validateForm()) {

            Category category = new Category(codFld.getText().toString(), nomFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCategoryActivity.class);
            intent.putExtra("editCategory", category);
            startActivity(intent);
            finish();
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Name is required!");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Name is required!");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Form has errors!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
