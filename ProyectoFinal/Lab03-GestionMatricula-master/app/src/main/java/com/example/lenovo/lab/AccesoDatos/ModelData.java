package com.example.lenovo.lab.AccesoDatos;

import com.example.lenovo.lab.LogicaNeg.Category;
import com.example.lenovo.lab.LogicaNeg.Client;
import com.example.lenovo.lab.LogicaNeg.Order;
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
  private List<Order> orderList;

  public ModelData(List<Category> categories,List<VideoGame> games, List<Client> clients, List<Order> orders) {
    categoriesList = new ArrayList<>();
    videoGamesList = new ArrayList<>();
    clientList = new ArrayList<>();
    orderList = new ArrayList<>();
    prepareCategoriesData1(categories);
    prepareVideoGamesData1(games);
    prepareClientsData1(clients);
    prepareOrdersData1(orders);
    prepareClientData();

  }

  public ModelData() {
    categoriesList = new ArrayList<>();
    videoGamesList = new ArrayList<>();
    clientList = new ArrayList<>();
//    prepareClientData();

  }

  public List<User> getUsuariosList() {
    usersList = new ArrayList<>();
    usersList.add(new User("1111", "@admin", "admin", "administrator"));
    usersList.add(new User("2222", "@client", "client", "client"));

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

  public void prepareClientsData1(List<Client> clients) {
    if (clients != null) {
      for (int i = 0; i < clients.size(); i++) {
        User user = new User(clients.get(i).getUser().getCedula(), clients.get(i).getUser().getEmail(),
          clients.get(i).getUser().getPassword(),
          clients.get(i).getUser().getRole());

        Client client = new Client(clients.get(i).getNombre(),
                            clients.get(i).getTelefono(), user);
        clientList.add(client);
      }
    }
  }

  public void prepareOrdersData1(List<Order> orders) {
    if (orders != null) {
      for (int i = 0; i < orders.size(); i++) {
        User user = new User(orders.get(i).getClient().getUser().getCedula(), null,
                              null,
                              null);

        Client client = new Client(null,
                                    0, user);

        VideoGame videoGame = new VideoGame(orders.get(i).getVideoGame().getCodigoJuego(),null,
                                            null, 0, 0, null);

        Order order = new Order(orders.get(i).getId(),orders.get(i).getFecha(), orders.get(i).getCantidad(), orders.get(i).getTotal(), videoGame, client);

        orderList.add(order);
      }
    }
  }


  public void prepareClientData() {
    User userAdmin = new User ("1234", "@admin", "admin", "administrator");
    Client admin = new Client("Jose", 321, userAdmin);
    clientList.add(admin);
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

  public List<Order> getOrderList() {
    return orderList;
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

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }
}
