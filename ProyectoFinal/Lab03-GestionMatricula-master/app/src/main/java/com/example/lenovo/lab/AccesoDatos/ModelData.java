package com.example.lenovo.lab.AccesoDatos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lenovo.lab.Activity.AdmCategoryActivity;
import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.LogicaNeg.VideoGame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/03/2018.
 */

public class ModelData {

  private List<Category> categoriesList;
  private List<User> usersList;
  private List<VideoGame> videoGamesList;
  private List<Client> clientList;




  public ModelData(List<Category> cate,List<VideoGame> games) {
    categoriesList = new ArrayList<>();
    videoGamesList = new ArrayList<>();
    clientList = new ArrayList<>();
    prepareCategoriesData1(cate);
    prepareVideoGamesData1(games);
    prepareClientData();

  }

  public ModelData() {
    categoriesList = new ArrayList<>();
    videoGamesList = new ArrayList<>();
    clientList = new ArrayList<>();
    prepareClientData();

  }

  public List<User> getUsuariosList() {
    usersList = new ArrayList<>();
    usersList.add(new User("@admin", "admin", "administrator", "111"));
    usersList.add(new User("@client", "client", "client", "222"));

    return usersList;
  }

  public void prepareCategoriesData1(List<Category> cate) {
    if(cate!=null) {
      for (int i = 0; i < cate.size(); i++) {
        Category category = new Category(cate.get(i).getCodigo(), cate.get(i).getNombre());
        categoriesList.add(category);
      }
    }
  }


  public void prepareVideoGamesData1(List<VideoGame> games) {
    if (games != null) {
      for (int i = 0; i < games.size(); i++) {
        Category category1 = new Category(games.get(i).getCategoria().getCodigo(), games.get(i).getCategoria().getNombre());

        VideoGame videoGame = new VideoGame(games.get(i).getCodigoJuego(), games.get(i).getNombre(), games.get(i).getEmpresa(),
                                            games.get(i).getCantidad(), games.get(i).getPrecio(), category1);
        videoGamesList.add(videoGame);
      }
    }
  }


  public void prepareClientData() {
    Client client = new Client("123", "Jose", 321, "@Jose");
    clientList.add(client);

    client = new Client("234", "Miguel", 3241, "@Miguel");
    clientList.add(client);
  }

  public List<Category> getCategoriesList() {
    return categoriesList;
  }

  public List<VideoGame> getVideoGamesList() {
    return videoGamesList;
  }

  public List<Client> getClientList() {
    return clientList;
  }

  public void setCategoriesList(List<Category> categoriesList) {
    this.categoriesList = categoriesList;
  }

  public void setVideoGamesList(List<VideoGame> videoGamesList) {
    this.videoGamesList = videoGamesList;
  }

  public void setClientList(List<Client> clientList) {
    this.clientList = clientList;
  }
}
