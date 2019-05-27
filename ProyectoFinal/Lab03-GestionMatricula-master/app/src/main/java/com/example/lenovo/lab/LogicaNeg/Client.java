package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

public class Client implements Serializable {
  private String identify, name, email;
  private int phone;

  public Client() {
  }

  public Client(String identify, String name, int phone, String email) {
    this.identify = identify;
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  public String getIdentify() {
    return identify;
  }

  public void setIdentify(String identify) {
    this.identify = identify;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Client{" +
      "identify='" + identify + '\'' +
      ", name='" + name + '\'' +
      ", phone=" + phone +
      ", email='" + email + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Client)) return false;

    Client client = (Client) o;

    if (getPhone() != client.getPhone()) return false;
    if (getIdentify() != null ? !getIdentify().equals(client.getIdentify()) : client.getIdentify() != null)
      return false;
    if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null)
      return false;
    return getEmail() != null ? getEmail().equals(client.getEmail()) : client.getEmail() == null;
  }

  @Override
  public int hashCode() {
    int result = getIdentify() != null ? getIdentify().hashCode() : 0;
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + getPhone();
    result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
    return result;
  }
}
