package com.example.lenovo.lab.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.List;

public class ShowHistorialActivity extends AppCompatActivity {
    private ListView listView;
    private List<Grupo> grupoList;
    private ModelData model;
    private String alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_historial);
        Toolbar toolbar = findViewById(R.id.toolbarH);
        setSupportActionBar(toolbar);
        updAlumnoCed();
        //toolbar fancy stuff
        getSupportActionBar().setTitle("cedula: "+alumno);

        listView = findViewById(R.id.listaH);
        grupoList = new ArrayList<>();
        model = new ModelData();
        grupoList = model.getGrupoList();

        whiteNotificationBar(listView);

        //filtrar grupoList
        ArrayList<String> lista = new ArrayList<>();
        for (Grupo i : grupoList)
            lista.add(i.getCurso() + " Nota: " + i.getNota(alumno));

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, lista);
        listView.setAdapter(mAdapter);
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
            alumno= (String) getIntent().getSerializableExtra("estudianteH");
        }
        else{alumno="";}
    }
}
