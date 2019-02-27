/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

/**
 *
 * @author cdiaz
 */
public class Usuario {
    private String id;
    private String cedula;
    private String contrasena;

    public Usuario() {
        id = "123";
        cedula = "12345678";
        contrasena = "contrasena";
    }

    public Usuario(String id, String cedula, String contrasena) {
        this.id = id;
        this.cedula = cedula;
        this.contrasena = contrasena;
    }
    
    public Usuario(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return id + cedula + contrasena;
    }
}
