package com.example.lab910.LogicaDeNegocio;

public class CursoEstudiante {
  private String estudiante, curso;

  public CursoEstudiante() {
  }

  public CursoEstudiante(String estudiante, String curso) {
    this.estudiante = estudiante;
    this.curso = curso;
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
    return "CursoEstudiante{" +
      "estudiante='" + estudiante + '\'' +
      ", curso='" + curso + '\'' +
      '}';
  }
}
