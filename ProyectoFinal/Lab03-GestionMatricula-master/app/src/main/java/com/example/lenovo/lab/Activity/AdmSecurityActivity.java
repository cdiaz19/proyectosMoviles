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

import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;
import com.example.lenovo.lab.Adapter.SecurityAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdmSecurityActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, SecurityAdapter.SeguridadAdapterListener {

  private RecyclerView mRecyclerView;
  private SecurityAdapter mAdapter;
  private List<User> userList;
  private CoordinatorLayout coordinatorLayout;
  private SearchView searchView;
  private FloatingActionButton fab;
  private ModelData model;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adm_segurity);
    Toolbar toolbar = findViewById(R.id.toolbarP);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.my_seguridad));

    mRecyclerView = findViewById(R.id.recycler_seguridadFld);
    userList = new ArrayList<>();
    model = new ModelData();
    userList = model.getUsuariosList();
    mAdapter = new SecurityAdapter(userList, this);
    coordinatorLayout = findViewById(R.id.coordinator_layoutP);
    whiteNotificationBar(mRecyclerView);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    mRecyclerView.setAdapter(mAdapter);

    fab = findViewById(R.id.addBtnP);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goToAddUpdSecurity();
      }
    });

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

    // Receive the User sent by AddUpdUserActivity
    checkIntentInformation();
    mAdapter.notifyDataSetChanged();
  }

  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
    if (direction == ItemTouchHelper.START) {
      if (viewHolder instanceof SecurityAdapter.MyViewHolder) {
        // get the removed item name to display it in snack bar
        String name = userList.get(viewHolder.getAdapterPosition()).getEmail();

        // save the index deleted
        final int deletedIndex = viewHolder.getAdapterPosition();
        // remove the item from recyclerView
        mAdapter.removeItem(viewHolder.getAdapterPosition());

        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removed!", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            mAdapter.restoreItem(deletedIndex);
          }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
      }
    } else {
      //If is editing a row object
      User aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
      //send data to Edit Activity
      Intent intent = new Intent(this, AddUpdSegurityActivity.class);
      intent.putExtra("editable", true);
      intent.putExtra("user", aux);
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
    getMenuInflater().inflate(R.menu.menu_search, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    searchView = (SearchView) menu.findItem(R.id.action_search)
      .getActionView();
    searchView.setSearchableInfo(searchManager
      .getSearchableInfo(getComponentName()));
    searchView.setMaxWidth(Integer.MAX_VALUE);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        mAdapter.getFilter().filter(query);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String query) {
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
  public void onContactSelected(User user) { //TODO get the select item of recycleView
    Toast.makeText(getApplicationContext(), "Selected: " + user.getEmail() + ", " + user.getRole(), Toast.LENGTH_LONG).show();
  }

  private void checkIntentInformation() {
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      User aux;
      aux = (User) getIntent().getSerializableExtra("addUser");
      if (aux == null) {
        aux = (User) getIntent().getSerializableExtra("editUser");
        if (aux != null) {
          //found an item that can be updated
          boolean founded = false;
          for (User user : userList) {
            if (user.getEmail().equals(aux.getEmail())) {
              user.setRole(aux.getRole());
              user.setPassword(aux.getPassword());
              founded = true;
              break;
            }
          }
          //check if exist
          if (founded) {
            Toast.makeText(getApplicationContext(), aux.getEmail() + " edited!", Toast.LENGTH_LONG).show();
          } else {
            Toast.makeText(getApplicationContext(), aux.getEmail() + " bit finding", Toast.LENGTH_LONG).show();
          }
        }
      } else {
        //found a new Profesor Object
        userList.add(aux);
        Toast.makeText(getApplicationContext(), aux.getEmail() + " added to List!", Toast.LENGTH_LONG).show();
      }
    }
  }

  private void goToAddUpdSecurity() {
    Intent intent = new Intent(this, AddUpdSegurityActivity.class);
    intent.putExtra("editable", false);
    startActivity(intent);
  }
}