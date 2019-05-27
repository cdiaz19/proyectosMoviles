package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class VideoGame implements Serializable{

  private String code, name, company;
  private int price;
  private Category category;

  public VideoGame() {
  }

  public VideoGame(String code, String name, String company, int price, Category category) {
    this.code = code;
    this.name = name;
    this.company = company;
    this.price = price;
    this.category = category;
  }

  public String getCode() {
    return code;
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

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "VideoGame{" +
      "code='" + code + '\'' +
      ", name='" + name + '\'' +
      ", company='" + company + '\'' +
      ", price=" + price +
      ", category=" + category +
      '}';
  }
}
