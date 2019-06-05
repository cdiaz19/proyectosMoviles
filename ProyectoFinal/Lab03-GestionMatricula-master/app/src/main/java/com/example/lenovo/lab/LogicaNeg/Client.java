package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class Client implements Serializable {
  private String cedula, nombre, correo;
  private int telefono;

  public Client() {
  }

  public Client(String cedula, String nombre, int telefono, String correo) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.telefono = telefono;
    this.correo = correo;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getTelefono() {
    return telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  @Override
  public String toString() {
    return "Client{" +
      "cedula='" + cedula + '\'' +
      ", name='" + nombre + '\'' +
      ", telefono=" + telefono +
      ", correo='" + correo + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Client)) return false;

    Client client = (Client) o;

    if (getTelefono() != client.getTelefono()) return false;
    if (getCedula() != null ? !getCedula().equals(client.getCedula()) : client.getCedula() != null)
      return false;
    if (getNombre() != null ? !getNombre().equals(client.getNombre()) : client.getNombre() != null)
      return false;
    return getCorreo() != null ? getCorreo().equals(client.getCorreo()) : client.getCorreo() == null;
  }

  @Override
  public int hashCode() {
    int result = getCedula() != null ? getCedula().hashCode() : 0;
    result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
    result = 31 * result + getTelefono();
    result = 31 * result + (getCorreo() != null ? getCorreo().hashCode() : 0);
    return result;
  }
}
