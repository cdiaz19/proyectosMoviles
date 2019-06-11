/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

/**
 *
 * @author alejandro
 */
public class Order {
    private int id, cantidad, total;
    private String fecha;
    private Videojuego videogame;
    private Client client;

    public Order(int id, String fecha,int cantidad, int total, Videojuego videogame, Client client) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
        this.videogame = videogame;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", cantidad=" + cantidad + ", total=" + total + ", fecha=" + fecha + ", videogame=" + videogame.getCodigoJuego() + ", client=" + client.user.getCedula() + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Videojuego getVideogame() {
        return videogame;
    }

    public void setVideogame(Videojuego videogame) {
        this.videogame = videogame;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
