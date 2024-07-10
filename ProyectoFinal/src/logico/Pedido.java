package logico;

import java.util.Date;
import java.util.List;

class Pedido {
    private int id;
    private Cliente cliente;
    private List<Componente> componentes;
    private Date fechaPedido;
    private Date fechaEntrega;

    public Pedido(int id, Cliente cliente, List<Componente> componentes, Date fechaPedido, Date fechaEntrega) {
        this.id = id;
        this.cliente = cliente;
        this.componentes = componentes;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
    }

    // Getters y Setters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Componente> getComponentes() { return componentes; }
    public Date getFechaPedido() { return fechaPedido; }
    public Date getFechaEntrega() { return fechaEntrega; }
}
