package logico;

import java.util.HashMap;
import java.util.Map;

public class Tienda {
    private Map<Integer, Componente> componentes;
    private Map<Integer, Cliente> clientes;
    private Map<Integer, Pedido> pedidos;

    public Tienda() {
        this.componentes = new HashMap<>();
        this.clientes = new HashMap<>();
        this.pedidos = new HashMap<>();
    }

    // Getters y Setters para los mapas
    public Map<Integer, Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(Map<Integer, Componente> componentes) {
        this.componentes = componentes;
    }

    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Map<Integer, Cliente> clientes) {
        this.clientes = clientes;
    }

    public Map<Integer, Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Map<Integer, Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // Métodos para agregar, eliminar y buscar componentes
    public void agregarComponente(Componente componente) {
        this.componentes.put(componente.getId(), componente);
    }

    public void eliminarComponente(int id) {
        this.componentes.remove(id);
    }

    public Componente buscarComponente(int id) {
        return this.componentes.get(id);
    }

    // Métodos para agregar clientes y realizar pedidos
    public void agregarCliente(Cliente cliente) {
        this.clientes.put(cliente.getId(), cliente);
    }

    public void eliminarCliente(int id) {
        this.clientes.remove(id);
    }

    public Cliente buscarCliente(int id) {
        return this.clientes.get(id);
    }

    public void realizarPedido(Pedido pedido) {
        this.pedidos.put(pedido.getId(), pedido);
    }

    public void eliminarPedido(int id) {
        this.pedidos.remove(id);
    }

    public Pedido buscarPedido(int id) {
        return this.pedidos.get(id);
    }
}


