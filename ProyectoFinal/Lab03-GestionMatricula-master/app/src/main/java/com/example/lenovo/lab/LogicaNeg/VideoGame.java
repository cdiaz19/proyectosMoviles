package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class VideoGame implements Serializable{

  private String codigoJuego, nombre, empresa;
  private int precio, cantidad;
  private Category categoria;

  public VideoGame() {
  }

  public VideoGame(String codigoJuego, String nombre, String empresa, int cantidad, int precio, Category categoria) {
    this.codigoJuego = codigoJuego;
    this.nombre = nombre;
    this.empresa = empresa;
    this.precio = precio;
    this.cantidad = cantidad;
    this.categoria = categoria;
  }

  public String getCodigoJuego() {
    return codigoJuego;
  }

  public void setCodigoJuego(String codigoJuego) {
    this.codigoJuego = codigoJuego;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmpresa() {
    return empresa;
  }

  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public int getPrecio() {
    return precio;
  }

  public void setPrecio(int precio) {
    this.precio = precio;
  }

  public Category getCategoria() {
    return categoria;
  }

  public void setCategoria(Category categoria) {
    this.categoria = categoria;
  }

  @Override
  public String toString() {
    return "VideoGame{" +
      "codigoJuego='" + codigoJuego + '\'' +
      ", nombre='" + nombre + '\'' +
      ", empresa='" + empresa + '\'' +
      ", cantidad=" + cantidad +
      ", precio=" + precio +
      ", categoria=" + categoria +
      '}';
  }
}
