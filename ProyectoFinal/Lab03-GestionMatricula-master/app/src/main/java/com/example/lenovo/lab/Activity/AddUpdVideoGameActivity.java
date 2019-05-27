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
import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.example.lenovo.lab.R;

public class AddUpdVideoGameActivity extends AppCompatActivity {

  private FloatingActionButton fBtn;
  private boolean editable;
  private EditText codeCatFld;
  private EditText nameCatFld;
  private EditText catVGFld;
  private EditText nameFld;
  private EditText priceFld;
  private EditText companyFld;
  private VideoGame critFiltG;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_upd_videogame);
    editable = true;
    fBtn = findViewById(R.id.addUpdVideoGameBtn);

    //cleaning stuff
    codeCatFld = findViewById(R.id.codeCatAddUpdVG);
    nameCatFld = findViewById(R.id.nameCatAddUpdVG);
    catVGFld = findViewById(R.id.catAddUpdVG);
    nameFld = findViewById(R.id.nameAddUpdVG);
    priceFld = findViewById(R.id.priceAddUpdVG);
    companyFld = findViewById(R.id.companyAddUpdVG);

    //receiving data from admGrupoActivity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      critFiltG = (VideoGame) getIntent().getSerializableExtra("filtGrupo");
      editable = extras.getBoolean("editable");
      if (editable) {   // is editing some row
        VideoGame aux = (VideoGame) getIntent().getSerializableExtra("videoGame");
        codeCatFld.setText(aux.getCategory().toString());
        codeCatFld.setEnabled(false);
        nameFld.setText(aux.getName());
        nameFld.setEnabled(false);
        priceFld.setText(aux.getPrice());
        priceFld.setEnabled(false);
        companyFld.setText(aux.getCompany());

        //edit action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            editVideoGame();
          }
        });
      } else {

        //add new action
        fBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            addVideoGame();
          }
        });
      }
    }
  }

  public void addVideoGame() {
    if (validateForm()) {

      String codeCat = codeCatFld.getText().toString();
      String nameCat = nameCatFld.getText().toString();

      VideoGame videoGame = new VideoGame(catVGFld.getText().toString(), nameFld.getText().toString(), companyFld.getText().toString(),
        Integer.parseInt(priceFld.getText().toString()), new Category(codeCat, nameCat));

      Toast.makeText(getApplicationContext(), videoGame.getName(), Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getBaseContext(), AdmVideoGameActivity.class);

      intent.putExtra("addVideoGame", videoGame);
      intent.putExtra("filtGrupo", critFiltG);
      startActivity(intent);
      finish();
    }
  }

  public void editVideoGame() {
    if (validateForm()) {

      String codeCat = codeCatFld.getText().toString();
      String nameCat = nameCatFld.getText().toString();

      VideoGame videoGame = new VideoGame(catVGFld.getText().toString(), nameFld.getText().toString(), companyFld.getText().toString(),
        Integer.parseInt(priceFld.getText().toString()), new Category(codeCat, nameCat));

      Toast.makeText(getApplicationContext(), videoGame.getName(), Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getBaseContext(), AdmVideoGameActivity.class);

      intent.putExtra("editVideoGame", videoGame);
      intent.putExtra("filtGrupo", critFiltG);
      startActivity(intent);
      finish();
    }
  }

  public boolean validateForm() {
    int error = 0;
    if (TextUtils.isEmpty(this.nameFld.getText())) {
      nameFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.priceFld.getText())) {
      priceFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.codeCatFld.getText())) {
      codeCatFld.setError("Field is Required!");
      error++;
    }
    if (TextUtils.isEmpty(this.priceFld.getText())) {
      companyFld.setError("Field is Required!");
      error++;
    }
    if (error > 0) {
      Toast.makeText(getApplicationContext(), "Form has some errors!", Toast.LENGTH_LONG).show();
      return false;
    }
    return true;
  }
}