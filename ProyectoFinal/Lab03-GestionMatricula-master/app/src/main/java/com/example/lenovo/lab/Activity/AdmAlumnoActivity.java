package com.example.lenovo.lab.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.lenovo.lab.LogicaNeg.Cliente;
import com.example.lenovo.lab.Adapter.ClienteAdapter;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class AdmAlumnoActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, ClienteAdapter.AlumnoAdapterListener {

    private RecyclerView mRecyclerView;
    private ClienteAdapter mAdapter;
    private List<Cliente> clienteList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private ModelData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_cliente);
        Toolbar toolbar = findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        //toolbar fancy stuff
        getSupportActionBar().setTitle(getString(R.string.my_alumno));

        mRecyclerView = findViewById(R.id.recycler_alumnoFld);
        clienteList = new ArrayList<>();
        model = new ModelData();
        clienteList = model.getClienteList();
        mAdapter = new ClienteAdapter(clienteList, this);
        coordinatorLayout = findViewById(R.id.coordinator_layoutA);

        // white background notification bar
        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        // go to update or add career
        fab = findViewById(R.id.addBtnA);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdAlumno();
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof ClienteAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = clienteList.get(viewHolder.getAdapterPosition()).getNombre();

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
            Cliente aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdClienteActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("alumno", aux);
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
        // Inflate the menu; this adds clienteList to the action bar if it is present.
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

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(Cliente cliente) { //TODO get the select item of recycleView
        SharedPreferences prefs = this.getSharedPreferences(getString(R.string.preference_user_key), Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.preference_user_key_default);
        String privilegio = prefs.getString(getString(R.string.preference_user_key), defaultValue);
        if (privilegio.equals("matriculador")) {
            Intent i = new Intent(this, MatriculaActivity.class);
            i.putExtra("estudiante", cliente.getCedula());
            startActivity(i);
        } else if (privilegio.equals("administrador")) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String from = (String) getIntent().getSerializableExtra("from");
                if (from.compareTo("historial") == 0) {
                    Intent i = new Intent(this, ShowHistorialActivity.class);
                    i.putExtra("estudianteH", cliente.getCedula());
                    startActivity(i);
                } else {
                    Intent i = new Intent(this, MatriculaActivity.class);
                    i.putExtra("estudiante", cliente.getCedula());
                    startActivity(i);
                }
            }else {
                Intent i = new Intent(this, MatriculaActivity.class);
                i.putExtra("estudiante", cliente.getCedula());
                startActivity(i);
            }

        }
        Toast.makeText(getApplicationContext(), "Selected: " + cliente.getCedula() + ", " + cliente.getNombre(), Toast.LENGTH_LONG).show();
    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Cliente aux;
            aux = (Cliente) getIntent().getSerializableExtra("addalumno");
            if (aux == null) {
                aux = (Cliente) getIntent().getSerializableExtra("editalumno");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Cliente cliente : clienteList) {
                        if (cliente.getCedula().equals(aux.getCedula())) {
                            cliente.setNombre(aux.getNombre());
                            cliente.setEmail(aux.getEmail());
                            cliente.setTelefono(aux.getTelefono());
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
                //found a new Cliente Object
                clienteList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNombre() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToAddUpdAlumno() {
        SharedPreferences prefs = this.getSharedPreferences(getString(R.string.preference_user_key), Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.preference_user_key_default);
        String privilegio = prefs.getString(getString(R.string.preference_user_key), defaultValue);
        if (privilegio.compareTo("administrador") == 0) {
            Intent intent = new Intent(this, AddUpdClienteActivity.class);
            intent.putExtra("editable", false);
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Necesita permisos de administrador", Toast.LENGTH_LONG).show();
    }
}
