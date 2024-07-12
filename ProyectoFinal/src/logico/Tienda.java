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

    public void realizarPedido(Pedido pedido) {
        this.pedidos.put(pedido.getId(), pedido);
    }

    public void eliminarPedido(int id) {
        this.pedidos.remove(id);
    }

    public Pedido buscarPedido(int id) {
        return this.pedidos.get(id);
    }



    // Métodos para buscar componentes por tipo
    public String buscarComponente(int id) {
        Componente componente = this.componentes.get(id);
        if (componente != null) {
            if (componente instanceof DiscoDuro) {
                return "DiscoDuro";
            } else if (componente instanceof MemoriaRAM) {
                return "MemoriaRAM";
            } else if (componente instanceof Microprocesador) {
                return "Microprocesador";
            } else if (componente instanceof TarjetaMadre) {
                return "TarjetaMadre";
            } else {
                return "Componente";
            }
        } else {
            return "Componente con ID " + id + " no encontrado.";
        }
    }


    // Método para actualizar el inventario
    private void actualizarInventario(Pedido pedido) {
        for (Map.Entry<Componente, Integer> entry : pedido.getComponentes().entrySet()) {
            Componente componente = entry.getKey();
            int cantidadPedida = entry.getValue();
            Componente inventarioComponente = componentes.get(componente.getId());
            if (inventarioComponente != null) {
                int nuevaCantidad = inventarioComponente.getCantidadDisponible() - cantidadPedida;
                inventarioComponente.setCantidadDisponible(nuevaCantidad);
            }
        }
    }

    // Métodos para agregar, eliminar, actualizar y buscar clientes
    public void agregarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == 0 || cliente.getNombre().isEmpty() || cliente.getApellido().isEmpty()) {
            System.out.println("Datos del cliente no válidos.");
            return;
        }
        this.clientes.put(cliente.getId(), cliente);
    }

    public void eliminarCliente(int id) {
        if (this.clientes.containsKey(id)) {
            this.clientes.remove(id);
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public Cliente buscarCliente(int id) {
        Cliente cliente = this.clientes.get(id);
        if (cliente != null) {
            return cliente;
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
            return null;
        }
    }

    public void actualizarCliente(int id, Cliente clienteActualizado) {
        if (this.clientes.containsKey(id)) {
            this.clientes.put(id, clienteActualizado);
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public void listarClientes() {
        if (this.clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : this.clientes.values()) {
                System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() + ", Apellido: " + cliente.getApellido() + ", Dirección: " + cliente.getDireccion());
            }
        }
    }

}


