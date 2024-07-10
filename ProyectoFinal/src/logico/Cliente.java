package logico;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;

    public Cliente(int id, String nombre, String apellido, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    // Constructor sin parámetros (opcional)
    public Cliente() {
        // Puedes inicializar valores por defecto si es necesario
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }
}

