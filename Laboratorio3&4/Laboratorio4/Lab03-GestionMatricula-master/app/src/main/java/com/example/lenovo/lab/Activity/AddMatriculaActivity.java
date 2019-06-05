package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.List;

public class AddMatriculaActivity extends AppCompatActivity {

    private ListView listView;
    private List<Grupo> grupoList;
    private ModelData model;
    private String alumno;
    private Ciclo cicloActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matricula);
        Toolbar toolbar = findViewById(R.id.toolbarAM);
        setSupportActionBar(toolbar);

        //toolbar fancy stuff
        getSupportActionBar().setTitle(getString(R.string.my_matricula));

        listView = findViewById(R.id.lista);
        grupoList = new ArrayList<>();
        model = new ModelData();
        grupoList = model.getGrupoList();
        updAlumnoCed();
        // white background notification bar
        whiteNotificationBar(listView);
        ArrayList<String> lista = new ArrayList<>();
        for (Grupo i : grupoList)
            lista.add(i.getCurso() + " N°" + i.getNumero());

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, lista);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), MatriculaActivity.class);
                Bundle extra=new Bundle();
                extra.putString("estudiante", alumno);
                extra.putSerializable("addmatricula", grupoList.get(i));
                //intent.putExtra("addmatricula", grupoList.get(i));
                intent.putExtras(extra);
                startActivity(intent);
                finish(); //prevent go back
            }
        });

        //refresh view
    }


    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }
    public void updAlumnoCed(){
        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            alumno= (String) getIntent().getSerializableExtra("estudiant");
        }
        else{alumno="";}
    }


}
