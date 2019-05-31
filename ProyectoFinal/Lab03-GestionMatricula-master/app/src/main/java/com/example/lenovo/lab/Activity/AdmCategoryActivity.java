package com.example.lenovo.lab.Activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.Adapter.CategoriesAdapter;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AdmCategoryActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, CategoriesAdapter.CategoryAdapterListener {


    String apiUrl = "http://10.0.2.2:8080/WEB-INF/";
    String tempUrl = "";
    private RecyclerView mRecyclerView;
    private CategoriesAdapter mAdapter;
    List<Category> categoriesList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private ModelData model;
    private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adm_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Maintenance Categories");

        tempUrl = apiUrl + "listarCategorias";
        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        try {
            json=myAsyncTasks.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final Gson gson = new Gson();
        final Type tipoListaCategories = new TypeToken<List<Category>>(){}.getType();
        final List<Category> categorias = gson.fromJson(json, tipoListaCategories);


        mRecyclerView = findViewById(R.id.recycler_carrerasFld);
        categoriesList = new ArrayList<>();
        model = new ModelData(categorias,null);
        categoriesList = model.getCategoriesList();
        mAdapter = new CategoriesAdapter(categoriesList, this);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        // white background notification bar
        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        fab = findViewById(R.id.addBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdCategory();
            }
        });

        // Receive the Category sent by AddUpdCategoryActivity and then refresh view.
        //checkIntentInformation();
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

    private void goToAddUpdCategory() {
        Intent intent = new Intent(this, AddUpdCategoryActivity.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Category aux;
            aux = (Category) getIntent().getSerializableExtra("addCategory");
            if (aux == null) {
                aux = (Category) getIntent().getSerializableExtra("editCategory");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Category category : categoriesList) {
                        if (category.getCodigo().equals(aux.getCodigo())) {
                            category.setNombre(aux.getNombre());
                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " edited!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " not finding!", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //found a new Category Object
                categoriesList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNombre() + " added to List!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof CategoriesAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = categoriesList.get(viewHolder.getAdapterPosition()).getCodigo();
                tempUrl = apiUrl + "deleteCategoria?codigo="+name;
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();




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
            Category aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            System.out.println("auxiliar"+aux);
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdCategoryActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("category", aux);
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
        // Inflate the menu; this adds categoriesList to the action bar if it is present.
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

    @Override
    public void onContactSelected(Category category) { //TODO get the select item of recycleView
        Toast.makeText(getApplicationContext(), "Seleccciono la Category: " + category.getCodigo() + ", " + category.getNombre(), Toast.LENGTH_LONG).show();
    }


    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // display a progress dialog for good user experiance
            /*progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(tempUrl);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        //System.out.print(current);

                    }
                    System.out.println(current);
                    // return the data to onPostExecute method
                    Log.w("", current);



                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {

               // final Gson gson = new Gson();
               // final Type tipoListaCategories = new TypeToken<List<Category>>(){}.getType();
               // categoriesList = gson.fromJson(s, tipoListaCategories);
                //System.out.println(categoriesList);


                //AlertDialog alertDialog = new AlertDialog.Builder(AdmCategoryActivity.this).create();
                //alertDialog.setTitle("Mensaje");
                //alertDialog.setMessage(s);
                //alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                  //      new DialogInterface.OnClickListener() {
                    //        public void onClick(DialogInterface dialog, int which) {
                      //          dialog.dismiss();
                        //    }
                        //});
                //alertDialog.show();

            }
            catch (Exception ex){

            }
        }


    }


    public  void getDataList(List<Category> cat) {



    }



}
