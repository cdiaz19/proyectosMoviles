package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

/**
 * Created by User on 24/03/2018.
 */

public class Cliente implements Serializable {
    private String cedula;
    private String nombre;
    private int telefono;
    private String email;


    public Cliente(String cedula, String nombre, int telefono, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;

        Cliente cliente = (Cliente) o;

        if (getTelefono() != cliente.getTelefono()) return false;
        if (getCedula() != null ? !getCedula().equals(cliente.getCedula()) : cliente.getCedula() != null)
            return false;
        if (getNombre() != null ? !getNombre().equals(cliente.getNombre()) : cliente.getNombre() != null)
            return false;
        return getEmail() != null ? getEmail().equals(cliente.getEmail()) : cliente.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getCedula() != null ? getCedula().hashCode() : 0;
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        result = 31 * result + getTelefono();
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
