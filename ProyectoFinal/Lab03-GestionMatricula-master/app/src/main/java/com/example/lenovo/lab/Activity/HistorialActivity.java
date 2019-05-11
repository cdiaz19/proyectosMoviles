package com.example.lenovo.lab.Activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lenovo.lab.LogicaNeg.Pedido;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HistorialActivity extends AppCompatActivity {

    private List<Grupo> grupos;
    private ModelData model;
    private Spinner annioSpinner;
    private Spinner cicloSpinner;
    private TextView notasFld;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        annioSpinner = findViewById(R.id.annioHistorialFld);
        cicloSpinner = findViewById(R.id.cicloHistorialFld);
        notasFld = findViewById(R.id.notasViewFld);

        model = new ModelData();

        grupos = model.getGrupoList();

        initSpinners();  //should use id to search

        fab = findViewById(R.id.searchHistorialBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchNotas();
            }
        });
    }

    private void searchNotas() {
        Pedido filter = new Pedido(Integer.parseInt(annioSpinner.getSelectedItem().toString()), cicloSpinner.getSelectedItem().toString());
        model = new ModelData();
        grupos = model.getGrupoList(); //retrieve data again
        Iterator<Grupo> it = grupos.iterator();
        while (it.hasNext()) {
            if (!it.next().getPedido().equals(filter)) {
                it.remove();
            }
        }
        notasFld.setText("");
        // suppose that my id was the third
        for (Grupo x : grupos) {
            notasFld.setText(notasFld.getText() + x.getCurso() + "->Nota:" + x.getNotas().get(2) + "\n");
        }
    }

    private void initSpinners() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        for (Grupo aux : grupos) {
            pedidos.add(aux.getPedido());
        }
        ArrayList<String> anniosList = new ArrayList<>(), ciclosList = new ArrayList<>();
        ciclosList.add("Primer");
        ciclosList.add("Segundo");

        for (Pedido aux : pedidos) {
            anniosList.add(aux.getCantidad() + "");
        }
        Set<String> set = new HashSet<String>(anniosList);
        anniosList = new ArrayList<String>();
        anniosList.addAll(set);

        ArrayAdapter<String> dataAdapterAnnio = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, anniosList);
        dataAdapterAnnio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        annioSpinner.setAdapter(dataAdapterAnnio);

        ArrayAdapter<String> dataAdapterNumero = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ciclosList);
        dataAdapterNumero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cicloSpinner.setAdapter(dataAdapterNumero);
    }

}
