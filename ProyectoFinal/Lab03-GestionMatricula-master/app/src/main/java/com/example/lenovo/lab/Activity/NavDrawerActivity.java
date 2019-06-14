package com.example.lenovo.lab.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lenovo.lab.R;

public class NavDrawerActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nav_drawer);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    this.userPrivileges();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    drawer.openDrawer(GravityCompat.START);
  }

  private void userPrivileges() {
    //getting logged user
    SharedPreferences prefs = this.getSharedPreferences(getString(R.string.preference_user_key), Context.MODE_PRIVATE);
    String defaultValue = getResources().getString(R.string.preference_user_key_default);
    String privilegio = prefs.getString(getString(R.string.preference_user_key), defaultValue);

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    Menu menu = navigationView.getMenu();
    MenuItem holder;
    //using privileges to lock data
    switch (privilegio) {
      case "administrator":
        holder = menu.findItem(R.id.nav_videoGame);
        holder.setEnabled(true);
        holder = menu.findItem(R.id.nav_categories);
        holder.setEnabled(true);
        holder = menu.findItem(R.id.nav_clients);
        holder.setEnabled(true);
        holder = menu.findItem(R.id.nav_order);
        holder.setEnabled(true);
        holder = menu.findItem(R.id.nav_segurity);
        holder.setEnabled(true);
        break;
      case "client":
        holder = menu.findItem(R.id.nav_order);
        holder.setEnabled(true);
        break;
      default:    //if is none
        break;
    }
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      this.moveTaskToBack(true);
      //super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.nav_drawer, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    SharedPreferences prefs = this.getSharedPreferences(getString(R.string.preference_user_key), Context.MODE_PRIVATE);
    String defaultValue = getResources().getString(R.string.preference_user_key_default);
    String privilegio = prefs.getString(getString(R.string.preference_user_key), defaultValue);

    if (id == R.id.nav_videoGame) {
      Toast.makeText(getApplicationContext(), "Video Games Module", Toast.LENGTH_SHORT).show();
      openAdmVideoGames();
    } else if (id == R.id.nav_order) {
      Toast.makeText(getApplicationContext(), "Orders Module", Toast.LENGTH_SHORT).show();
      openAdmOrder();
    } else if (id == R.id.nav_clients) {
      Toast.makeText(getApplicationContext(), "Users Module", Toast.LENGTH_SHORT).show();
      openAdmClient();
    } else if (id == R.id.nav_categories) {
      Toast.makeText(getApplicationContext(), "Category Module", Toast.LENGTH_SHORT).show();
      openAdmCategory();
    } else if (id == R.id.nav_logout) {
      Toast.makeText(getApplicationContext(), "Log Out", Toast.LENGTH_SHORT).show();
      openLogin();
    } else if (id == R.id.nav_segurity) {
      Toast.makeText(getApplicationContext(), "Security Module", Toast.LENGTH_SHORT).show();
      openAdmSegurity();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void openLogin() {
    finish();
    Intent a = new Intent(this, LoginActivity.class);
    startActivity(a);
  }

  public void openAdmCategory() {
    Intent intent = new Intent(this, AdmCategoryActivity.class);
    startActivity(intent);
  }

  public void openAdmVideoGames() {
    Intent intent = new Intent(this, AdmVideoGameActivity.class);
    startActivity(intent);
  }

  public void openAdmSegurity() {
    Intent intent = new Intent(this, AdmSecurityActivity.class);
    startActivity(intent);
  }

  public void openAdmClient() {
    Intent intent = new Intent(this, AdmClientActivity.class);
    startActivity(intent);
  }

  public void openAdmOrder() {
    Intent intent = new Intent(this, AdmOrderActivity.class);
    startActivity(intent);
  }

  public void abrirAdmHistorial() {
    Intent intent = new Intent(this, AdmVideoGameActivity.class);
    intent.putExtra("from", "historial");
    startActivity(intent);
    finish();
  }
}
