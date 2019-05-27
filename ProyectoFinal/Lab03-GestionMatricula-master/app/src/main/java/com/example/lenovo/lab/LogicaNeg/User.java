package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class User implements Serializable {
  private String email;
  private String password;
  private String identify;
  private String role;

  public User() {
    this.email = "";
    this.password = "";
    this.role = "";
  }

  public User(String email, String password, String role, String identify) {
    this.email = email;
    this.password = password;
    this.identify = identify;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getIdentify() {
    return identify;
  }

  public void setIdentify(String identify) {
    this.identify = identify;
  }

  @Override
  public String toString() {
    return "User{" +
      "email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", identify='" + identify + '\'' +
      ", role='" + role + '\'' +
      '}';
  }
}
