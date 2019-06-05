package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class Order implements Serializable {
  private String fecha;
  private int cantidad, total;
  private VideoGame videoGame;
  private Client client;

  public Order() {
  }

  public Order(String fecha, int cantidad, int total, VideoGame videoGame, Client client) {
    this.fecha = fecha;
    this.cantidad = cantidad;
    this.total = total;
    this.videoGame = videoGame;
    this.client = client;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public VideoGame getVideoGame() {
    return videoGame;
  }

  public void setVideoGame(VideoGame videoGame) {
    this.videoGame = videoGame;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  @Override
  public String toString() {
    return "Order{" +
      "fecha='" + fecha + '\'' +
      ", cantidad=" + cantidad +
      ", total=" + total +
      ", videoGame=" + videoGame +
      ", client=" + client +
      '}';
  }
}
