package com.example.lenovo.lab.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import com.example.lenovo.lab.LogicaNeg.Categoria;
import com.example.lenovo.lab.Adapter.CategoriasAdapter;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class AdmCategoriaActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, CategoriasAdapter.CarreraAdapterListener {

    private RecyclerView mRecyclerView;
    private CategoriasAdapter mAdapter;
    private List<Categoria> categoriaList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private ModelData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adm_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar fancy stuff
        getSupportActionBar().setTitle("Categorias");

        mRecyclerView = findViewById(R.id.recycler_carrerasFld);
        categoriaList = new ArrayList<>();
        model = new ModelData();
        categoriaList = model.getCategoriaList();
        mAdapter = new CategoriasAdapter(categoriaList, this);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        // white background notification bar
        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        // go to update or add career
        fab = findViewById(R.id.addBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdCarrera();
            }
        });

        //delete swiping left and right
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        //should use database info


        // Receive the Categoria sent by AddUpdCategoryActivity
        checkIntentInformation();

        //refresh view
        mAdapter.notifyDataSetChanged();
    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Categoria aux;
            aux = (Categoria) getIntent().getSerializableExtra("addCarrera");
            if (aux == null) {
                aux = (Categoria) getIntent().getSerializableExtra("editCarrera");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Categoria categoria : categoriaList) {
                        if (categoria.getCodigo().equals(aux.getCodigo())) {
                            categoria.setNombre(aux.getNombre());
                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " editado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " no encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //found a new Categoria Object
                categoriaList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNombre() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToAddUpdCarrera() {
        Intent intent = new Intent(this, AddUpdCategoryActivity.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof CategoriasAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = categoriaList.get(viewHolder.getAdapterPosition()).getNombre();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());

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
        } else {
            //If is editing a row object
            Categoria aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdCategoryActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("categoria", aux);
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
        // Inflate the menu; this adds categoriaList to the action bar if it is present.
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
    public void onBackPressed() { //TODO it's not working yet
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, NavDrawerActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
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
    public void onContactSelected(Categoria categoria) { //TODO get the select item of recycleView
        Toast.makeText(getApplicationContext(), "Seleccciono la Categoria: " + categoria.getCodigo() + ", " + categoria.getNombre(), Toast.LENGTH_LONG).show();
    }
}
