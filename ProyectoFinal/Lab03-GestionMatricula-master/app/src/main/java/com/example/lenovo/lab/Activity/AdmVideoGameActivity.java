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

import com.example.lenovo.lab.Adapter.VideoGameAdapter;
import com.example.lenovo.lab.AccesoDatos.ModelData;
import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.example.lenovo.lab.R;
import com.example.lenovo.lab.Helper.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class AdmVideoGameActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, VideoGameAdapter.VideoGameAdapterListener {

  private RecyclerView mRecyclerView;
  private VideoGameAdapter mAdapter;
  private List<VideoGame> videoGamesList;
  private CoordinatorLayout coordinatorLayout;
  private SearchView searchView;
  private FloatingActionButton fab;
  private ModelData model;
  private VideoGame critFiltG;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_adm_category);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setTitle("Maintenance VideoGames");

    mRecyclerView = findViewById(R.id.recycler_carrerasFld);
    videoGamesList = new ArrayList<>();
    model = new ModelData();
    videoGamesList = model.getVideoGamesList();
    mAdapter = new VideoGameAdapter(videoGamesList, this);
    coordinatorLayout = findViewById(R.id.coordinator_layout);
    whiteNotificationBar(mRecyclerView);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    mRecyclerView.setAdapter(mAdapter);

    fab = findViewById(R.id.addBtn);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        goToAddUpdVideoGameActivity();
      }
    });

    //delete swiping left and right
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

    critFiltG = new VideoGame();
    // Receive the VideoGame sent by AddUpdVideoGameActivity and then refresh the view with it.
    checkIntentInformation();
    mAdapter.notifyDataSetChanged();
  }

  private void checkIntentInformation() {
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      critFiltG = (VideoGame) getIntent().getSerializableExtra("filtGrupo");
      if (critFiltG != null){ // only the videojuegos the match with the criteria
        for(VideoGame g : model.getVideoGamesList()){
          if(g.getCategory().getCode().equals(critFiltG.getCategory().getCode())){
            videoGamesList.add(g);
          }
        }
      }
      VideoGame aux;
      aux = (VideoGame) getIntent().getSerializableExtra("addVideoGame");
      if (aux == null) {
        aux = (VideoGame) getIntent().getSerializableExtra("editVideoGame");
        if (aux != null) {
          //found an item that can be updated
          boolean founded = false;
          for (VideoGame videogame : videoGamesList) {
            if (videogame.getName().equals(aux.getName())) {
              videogame.setName(aux.getName());
              founded = true;
              break;
            }
          }
          if (founded) {
            Toast.makeText(getApplicationContext(), aux.getName() + " edited", Toast.LENGTH_LONG).show();
          } else {
            Toast.makeText(getApplicationContext(), aux.getName() + " not finding", Toast.LENGTH_LONG).show();
          }
        }
      } else {
        //found a new Category Object
        videoGamesList.add(aux);
        Toast.makeText(getApplicationContext(), aux.getName() + " addded to List!", Toast.LENGTH_LONG).show();
      }
    }
  }

  private void goToAddUpdVideoGameActivity() {
    Intent intent = new Intent(this, AddUpdVideoGameActivity.class);
    intent.putExtra("editable", false);
    intent.putExtra("filtGrupo", critFiltG);
    startActivity(intent);
  }


  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
    if (direction == ItemTouchHelper.START) {
      if (viewHolder instanceof VideoGameAdapter.MyViewHolder) {
        // get the removed item name to display it in snack bar
        String name = videoGamesList.get(viewHolder.getAdapterPosition()).getName();

        // save the index deleted
        final int deletedIndex = viewHolder.getAdapterPosition();
        // remove the item from recyclerView
        mAdapter.removeItem(viewHolder.getAdapterPosition());

        // showing snack bar with Undo option
        Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removed from List!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            mAdapter.restoreItem(deletedIndex);
          }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
      }
    } else {
      VideoGame aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());

      Intent intent = new Intent(this, AddUpdCategoryActivity.class);
      intent.putExtra("editable", true);
      intent.putExtra("filtGrupo", critFiltG);
      intent.putExtra("videoGame", aux);
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
  public void onContactSelected(VideoGame videoGame) {
    Toast.makeText(getApplicationContext(), "Selected: " + videoGame.getCode() + ", " + videoGame.getName(), Toast.LENGTH_LONG).show();
  }
}
