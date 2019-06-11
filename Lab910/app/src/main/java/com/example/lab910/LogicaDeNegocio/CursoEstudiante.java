package com.example.lab910.LogicaDeNegocio;

public class CursoEstudiante {
  private int id;
  private String estudiante, curso;

  public CursoEstudiante() {
  }

  public CursoEstudiante(int id, String estudiante, String curso) {
    this.id = id;
    this.estudiante = estudiante;
    this.curso = curso;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getEstudiante() {
    return estudiante;
  }

  public void setEstudiante(String estudiante) {
    this.estudiante = estudiante;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  @Override
  public String toString() {
    return estudiante + ", Curso:'" + curso;
  }
}
