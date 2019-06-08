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

import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.List;

public class AddUpdSegurityActivity extends AppCompatActivity {
  private FloatingActionButton fBtn;
  private boolean editable = true;
  private EditText emailFld;
  private EditText passFld;
  private EditText identifyFld;
  private Spinner spinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_upd_segurity);
    editable = true;

    // button check
    fBtn = findViewById(R.id.addUpdSeguridadBtn);

    //cleaning stuff
    emailFld = findViewById(R.id.emailAddUpdSegurity);
    passFld = findViewById(R.id.passwordAddUpdSegurity);
    identifyFld = findViewById(R.id.identifyAddUpdSegurity);
    emailFld.setText("");
    passFld.setText("");

    //spinner
    initSpinner();

    Bundle extras = getIntent().getExtras();
    if (extras != null) {

      editable = extras.getBoolean("editable");
      if (editable) {
        User aux = (User) getIntent().getSerializableExtra("user");
        identifyFld.setEnabled(false);
        emailFld.setEnabled(false);
        emailFld.setText(aux.getEmail());
        passFld.setText(aux.getPassword());
        //edit action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            editUser();
          }
        });
      } else {
        //add new action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            addUser();
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

  public void addUser() {
    if (validateForm()) {
      User user = new User(identifyFld.getText().toString(), emailFld.getText().toString(), passFld.getText().toString(),
        spinner.getSelectedItem().toString());

      Intent intent = new Intent(getBaseContext(), AdmSecurityActivity.class);
      intent.putExtra("addUser", user);
      startActivity(intent);
      finish();
    }
  }

  public void editUser() {
    if (validateForm()) {
      User user = new User(identifyFld.getText().toString(), emailFld.getText().toString(), passFld.getText().toString(),
        spinner.getSelectedItem().toString());
      Intent intent = new Intent(getBaseContext(), AdmSecurityActivity.class);

      intent.putExtra("editUser", user);
      startActivity(intent);
      finish();
    }
  }

  public boolean validateForm() {
    int error = 0;
    if (TextUtils.isEmpty(this.emailFld.getText())) {
      emailFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.passFld.getText())) {
      passFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.identifyFld.getText())) {
      identifyFld.setError("Field is Required!");
      error++;
    }
    if (spinner.getSelectedItem().toString().equals("")) {
      error++;
    }
    if (error > 0) {
      Toast.makeText(getApplicationContext(), "Form has some errors!!", Toast.LENGTH_LONG).show();
      return false;
    }
    return true;
  }
}