package logico;

import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Customer cliente;
    private List<Component> componentes;
    private Date fechaPedido;
    private Date fechaEntrega;

    public Pedido(int id, Customer cliente, List<Component> componentes, Date fechaPedido, Date fechaEntrega) {
        this.id = id;
        this.cliente = cliente;
        this.componentes = componentes;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
    }

    // Getters y Setters
    public int getId() { return id; }
    public Customer getCliente() { return cliente; }
    public List<Component> getComponentes() { return componentes; }
    public Date getFechaPedido() { return fechaPedido; }
    public Date getFechaEntrega() { return fechaEntrega; }
}
