package com.example.lab910.LogicaDeNegocio;

public class Curso {
  private int id, creditos;
  private String descripcion;
  private int estudiante;

  public Curso() { }

  public Curso(int id, int creditos, String descripcion, int estudiante) {
    this.id = id;
    this.creditos = creditos;
    this.descripcion = descripcion;
    this.estudiante = estudiante;
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

  public int getEstudiante() {
    return estudiante;
  }

  public void setEstudiante(int estudiante) {
    this.estudiante = estudiante;
  }

  @Override
  public String toString() {
    return "Curso{" +
      "id=" + id +
      ", creditos=" + creditos +
      ", descripcion='" + descripcion + '\'' +
      ", estudiante=" + estudiante +
      '}';
  }
}
