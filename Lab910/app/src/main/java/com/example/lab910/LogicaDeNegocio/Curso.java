package com.example.lab910.LogicaDeNegocio;

public class Curso {
  private int id, creditos;
  private String descripcion;

  public Curso() { }

  public Curso(int id, int creditos, String descripcion) {
    this.id = id;
    this.creditos = creditos;
    this.descripcion = descripcion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Override
  public String toString() {
    return "Id:" + id + ", " + descripcion + " -  Creditos " + creditos;
  }
}
