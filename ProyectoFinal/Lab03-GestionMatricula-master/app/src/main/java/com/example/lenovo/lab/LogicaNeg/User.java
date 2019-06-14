package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class User implements Serializable {
  private String email;
  private String password;
  private String role;
  private String cedula;

  public User() {
    this.email = "";
    this.password = "";
    this.role = "";
    this.cedula ="";
  }

  public User(String cedula, String email, String password, String role) {
    this.cedula = cedula;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public String getCedula() { return cedula; }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setCedula(String cedula) { this.cedula = cedula; }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
      "email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", role='" + role + '\'' +
      ",cedula=" + cedula  + " " +
      '}';
  }
}
