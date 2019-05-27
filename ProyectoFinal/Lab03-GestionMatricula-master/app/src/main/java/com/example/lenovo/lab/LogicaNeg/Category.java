package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

  private String code, name;
  private List<Category> categories;

  public Category() {
  }

  public Category(String code, String name) {
    this.code = code;
    this.name = name;
    this.categories = new ArrayList<>();
  }

  public String getCode() {
    return code;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
        /*
        return "Category{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
                */
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Category category = (Category) o;

    if (getCode() != null ? !getCode().equals(category.getCode()) : category.getCode() != null)
      return false;
    return getName() != null ? getName().equals(category.getName()) : category.getName() == null;
  }

  @Override
  public int hashCode() {
    int result = getCode() != null ? getCode().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    return result;
  }
}