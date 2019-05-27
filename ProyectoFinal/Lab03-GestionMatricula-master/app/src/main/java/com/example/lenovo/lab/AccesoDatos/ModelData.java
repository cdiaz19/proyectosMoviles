package com.example.lenovo.lab.AccesoDatos;

import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.LogicaNeg.User;
import com.example.lenovo.lab.LogicaNeg.VideoGame;

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

  public ModelData() {
    categoriesList = new ArrayList<>();
    videoGamesList = new ArrayList<>();
    clientList = new ArrayList<>();

    prepareCategoriesData();
    prepareVideoGamesData();
    prepareClientData();

  }

  public List<User> getUsuariosList() {
    usersList = new ArrayList<>();
    usersList.add(new User("@admin", "admin", "administrator", "111"));
    usersList.add(new User("@client", "client", "client", "222"));

    return usersList;
  }

  public void prepareCategoriesData() {
    Category category = new Category("ACC", "Action");
    categoriesList.add(category);

    category = new Category("SHO", "Shooter");
    categoriesList.add(category);

    category = new Category("STR", "Strategic");
    categoriesList.add(category);

    category = new Category("SIN", "Simulation");
    categoriesList.add(category);

    category = new Category("SPO", "Sports");
    categoriesList.add(category);
  }

  public void prepareVideoGamesData() {
    Category category1 = new Category("ACC", "Accion");

    VideoGame videoGame = new VideoGame("GTAV", "Grand Theaft Auto V", "RockStart", 35000, category1);
    videoGamesList.add(videoGame);
    VideoGame videoGame1 = new VideoGame("GTAII", "Grand Theaft Auto II", "RockStart", 10000, category1);
    videoGamesList.add(videoGame1);
    VideoGame videoGame2 = new VideoGame("GTAIV", "Grand Theaft Auto IV", "RockStart", 25000, category1);
    videoGamesList.add(videoGame2);

    Category category2 = new Category("STR", "Estrategia");
    VideoGame videoGame3 = new VideoGame("SPO", "Spoling", "Test",  35000, category2);
    videoGamesList.add(videoGame3);

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
