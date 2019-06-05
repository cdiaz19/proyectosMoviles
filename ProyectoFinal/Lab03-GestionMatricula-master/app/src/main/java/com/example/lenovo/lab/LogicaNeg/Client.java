package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class Client implements Serializable {
  private String nombre;
  private int telefono;
  private User user;

  public Client() {
  }

  public Client( String nombre, int telefono, User user) {
    this.nombre = nombre;
    this.telefono = telefono;
    this.user = user;
  }


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setUser(User user) { this.user = user; }

  public int getTelefono() {
    return telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public User getUser() { return user; }

  @Override
  public String toString() {
    return "Client{" +
      ", name='" + nombre + '\'' +
      ", telefono=" + telefono +
      ", user='" + user + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Client)) return false;

    Client client = (Client) o;

    if (getTelefono() != client.getTelefono()) return false;
    return (getNombre() != null ? !getNombre().equals(client.getNombre()) : client.getNombre() != null);
  }

  @Override
  public int hashCode() {
    int result = (getNombre() != null ? getNombre().hashCode() : 0);
    result = 31 * result + getTelefono();
    return result;
  }
}
