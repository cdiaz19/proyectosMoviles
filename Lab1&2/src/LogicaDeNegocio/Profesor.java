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
public class Profesor {
    private String id;
    private String nombre;
    private String correo;
    private String contrasena;
    private int telefono;
    private Usuario usuario;

    public Profesor() {
        id="";
        nombre = "";
        correo = "";
        telefono = 0;
        usuario = new Usuario();
    }

    public Profesor(String id, String nombre, String correo, int telefono, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return id + nombre + correo +telefono + usuario.toString();
    }
}
