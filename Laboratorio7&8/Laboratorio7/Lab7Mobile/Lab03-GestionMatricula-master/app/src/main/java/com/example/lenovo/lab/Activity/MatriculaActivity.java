package com.example.lenovo.lab.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.Adapter.MatriculaAdapter;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatriculaActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, MatriculaAdapter.MatriculaAdapterListener {

    private RecyclerView mRecyclerView;
    private MatriculaAdapter mAdapter;
    private List<Grupo> grupoList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private ModelData model;
    private String alumno;
    private Ciclo cicloActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricula);
        Toolbar toolbar = findViewById(R.id.toolbarMa);
        setSupportActionBar(toolbar);

        //toolbar fancy stuff
        getSupportActionBar().setTitle(getString(R.string.my_matricula));

        mRecyclerView = findViewById(R.id.recycler_matriculaFld);
        grupoList = new ArrayList<>();
        model = new ModelData();
        grupoList = model.getGrupoList();
        updAlumnoCed();
        cicloActual = getCicloActual();
        grupoList = filtrar();

        mAdapter = new MatriculaAdapter(grupoList, this);
        coordinatorLayout = findViewById(R.id.coordinator_layoutM);

        // white background notification bar
        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        // go to update or add career
        fab = findViewById(R.id.addBtnM);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdGrupo();
            }
        });

        //delete swiping left and right
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        //should use database info


        // Receive the Carrera sent by AddUpdCarreraActivity
        checkIntentInformation();

        //refresh view
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof MatriculaAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = grupoList.get(viewHolder.getAdapterPosition()).getCurso();

            // save the index deleted
            final int deletedIndex = viewHolder.getAdapterPosition();
            // remove the item from recyclerView
            mAdapter.removeItem(viewHolder.getAdapterPosition(), alumno);

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // undo is selected, restore the deleted item from adapter
                    mAdapter.restoreItem(deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(Grupo grupo) { //TODO get the select item of recycleView
        Toast.makeText(getApplicationContext(), "Selected: " + grupo.getCurso(), Toast.LENGTH_LONG).show();
    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Grupo aux;
            aux = (Grupo) getIntent().getSerializableExtra("addmatricula");
            if (aux != null) {
                aux.agregarAlumno(alumno, 0);
                grupoList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getCurso() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToAddUpdGrupo() {
        finish();
        Intent intent = new Intent(this, AddMatriculaActivity.class);
        intent.putExtra("estudiant", alumno);
        startActivity(intent);
    }

    public void updAlumnoCed() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            alumno = (String) getIntent().getSerializableExtra("estudiante");
        } else {
            alumno = "";
        }
    }

    public Ciclo getCicloActual() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        if (month < 5)
            return new Ciclo(year, "Primer");
        else
            return new Ciclo(year, "Segundo");
    }

    public List<Grupo> filtrar() {
        List<Grupo> aux;
        aux = filtroCiclo();
        return filtroAlumno(aux);
    }

    public List<Grupo> filtroCiclo() {
        List<Grupo> aux = new ArrayList<>();
        for (Grupo i : grupoList)
            if (i.getCiclo().getAño() == cicloActual.getAño() && i.getCiclo().getNumero().compareTo(cicloActual.getNumero()) == 0)
                aux.add(i);
        return aux;
    }

    public List<Grupo> filtroAlumno(List<Grupo> aux) {
        List<Grupo> auc = new ArrayList<>();
        for (Grupo i : aux)
            if (i.existe(alumno))
                auc.add(i);
        return auc;
    }


}
