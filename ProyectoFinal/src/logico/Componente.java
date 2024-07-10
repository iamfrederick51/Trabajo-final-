package logico;

abstract class Componente {
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private int cantidadDisponible;
    private String numeroSerie;

    public Componente(int id, String marca, String modelo, double precio, int cantidadDisponible, String numeroSerie) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.numeroSerie = numeroSerie;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public double getPrecio() { return precio; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public String getNumeroSerie() { return numeroSerie; }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
