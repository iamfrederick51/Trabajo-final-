package logico;

class MemoriaRAM extends Componente {
    private String tipoMemoria;
    private int capacidadMB;

    public MemoriaRAM(int id, String marca, String modelo, double precio, int cantidadDisponible, String numeroSerie,
                      String tipoMemoria, int capacidadMB) {
        super(id, marca, modelo, precio, cantidadDisponible, numeroSerie);
        this.tipoMemoria = tipoMemoria;
        this.capacidadMB = capacidadMB;
    }

    // Getters y Setters
    public String getTipoMemoria() { return tipoMemoria; }
    public int getCapacidadMB() { return capacidadMB; }
}