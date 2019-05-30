package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

  private String codigo, nombre;
  private List<Category> categories;

  public Category() {
  }

  public Category(String codigo, String nombre) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.categories = new ArrayList<>();
  }

  public String getCodigo() {
    return codigo;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return nombre;
        /*
        return "Category{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
                */
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Category category = (Category) o;

    if (getCodigo() != null ? !getCodigo().equals(category.getCodigo()) : category.getCodigo() != null)
      return false;
    return getNombre() != null ? getNombre().equals(category.getNombre()) : category.getNombre() == null;
  }

  @Override
  public int hashCode() {
    int result = getCodigo() != null ? getCodigo().hashCode() : 0;
    result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
    return result;
  }
}