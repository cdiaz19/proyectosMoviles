package com.example.lenovo.lab.Activity;

import android.app.SearchManager;
import android.content.Context;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.Adapter.GrupoAdapter;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class AdmGrupoActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, GrupoAdapter.GrupoAdapterListener {

    private RecyclerView mRecyclerView;
    private GrupoAdapter mAdapter;
    private List<Grupo> grupoList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private ModelData model;
    private Grupo critFiltG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_grupo);

        Toolbar toolbar = findViewById(R.id.toolbarG);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.my_grupo));

        mRecyclerView = findViewById(R.id.recycler_gruposFld);
        grupoList = new ArrayList<>();
        model= new ModelData();
        //grupoList= model.getGrupoList();
        mAdapter = new GrupoAdapter(grupoList, this);
        coordinatorLayout = findViewById(R.id.coordinator_layoutG);

        // white background notification bar
        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        // go to update or add
        fab = findViewById(R.id.addBtnG);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdGrupo();
            }
        });

        //delete swiping left and right
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        critFiltG = new Grupo();
        checkIntentInformation();

        //refresh view
        mAdapter.notifyDataSetChanged();
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
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof GrupoAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String number = grupoList.get(viewHolder.getAdapterPosition()).getNumero();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar.make(coordinatorLayout, number + " removido!", Snackbar.LENGTH_LONG);
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
        } else {
            //If is editing a row object
            Grupo aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdGrupoActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("filtGrupo", critFiltG);
            intent.putExtra("grupo", aux);
            mAdapter.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }
    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cursoList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView   !IMPORTANT
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, NavDrawerActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }

    @Override
    public void onContactSelected(Grupo grupo) {
        Toast.makeText(getApplicationContext(), "Selected: " + grupo.getNumero() + ", " + grupo.getCurso(), Toast.LENGTH_LONG).show();
    }

    public void checkIntentInformation(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) { // if they send something
            critFiltG = (Grupo) getIntent().getSerializableExtra("filtGrupo");
            if (critFiltG != null){ // only the Grupos the match with the criteria
                for(Grupo g : model.getGrupoList()){
                    if(g.getCurso().equals(critFiltG.getCurso()) && g.getCiclo().equals(critFiltG.getCiclo())){
                        grupoList.add(g);
                    }
                }
            }
            Grupo aux;
            aux = (Grupo) getIntent().getSerializableExtra("addGrupo");
            if (aux == null) { // to edit
                aux = (Grupo) getIntent().getSerializableExtra("editGrupo");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Grupo grupo : grupoList) {
                        if (grupo.getNumero().equals(aux.getNumero())) {
                            grupo.setCiclo(aux.getCiclo());
                            grupo.setCurso(aux.getCurso());
                            grupo.setHorario(aux.getHorario());
                            grupo.setProfesor(aux.getProfesor());
                            grupo.setEstudiantes(null);
                            grupo.setNotas(null);
                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getNumero() + " editado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getNumero() + " no encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            } else { // to add
                //found a new Curso Object
                grupoList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNumero() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void goToAddUpdGrupo(){
        Intent intent = new Intent(this, AddUpdGrupoActivity.class);
        intent.putExtra("editable", false);
        intent.putExtra("filtGrupo", critFiltG);
        startActivity(intent);
    }
}
