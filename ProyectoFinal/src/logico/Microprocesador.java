package logico;

class Microprocesador extends Componente {
    private String tipoConexion;
    private double velocidadMHz;

    public Microprocesador(int id, String marca, String modelo, double precio, int cantidadDisponible, String numeroSerie,
                           String tipoConexion, double velocidadMHz) {
        super(id, marca, modelo, precio, cantidadDisponible, numeroSerie);
        this.tipoConexion = tipoConexion;
        this.velocidadMHz = velocidadMHz;
    }

    // Getters y Setters
    public String getTipoConexion() { return tipoConexion; }
    public double getVelocidadMHz() { return velocidadMHz; }
}