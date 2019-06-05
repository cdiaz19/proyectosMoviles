package com.example.mobilecontrol;
import java.io.Serializable;
public class Estudiante implements Serializable {

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    Estudiante(String id, String nombre, int edad){
        this.nombre=nombre;
        this.edad=edad;
    }
    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private String Id;
    private int edad;
    private String nombre;
}