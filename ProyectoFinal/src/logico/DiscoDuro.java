package logico;

class DiscoDuro extends Componente {
    private int capacidadGB;
    private String tipoConexion;

    public DiscoDuro(int id, String marca, String modelo, double precio, int cantidadDisponible, String numeroSerie,
                     int capacidadGB, String tipoConexion) {
        super(id, marca, modelo, precio, cantidadDisponible, numeroSerie);
        this.capacidadGB = capacidadGB;
        this.tipoConexion = tipoConexion;
    }

    // Getters y Setters
    public int getCapacidadGB() { return capacidadGB; }
    public String getTipoConexion() { return tipoConexion; }
}