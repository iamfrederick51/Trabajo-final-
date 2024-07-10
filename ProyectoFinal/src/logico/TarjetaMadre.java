package logico;

import java.util.List;

class TarjetaMadre extends Componente {
    private String tipoConectorMicro;
    private String tipoMemoriaRAM;
    private List<String> conexionesDiscos;

    public TarjetaMadre(int id, String marca, String modelo, double precio, int cantidadDisponible, String numeroSerie,
                        String tipoConectorMicro, String tipoMemoriaRAM, List<String> conexionesDiscos) {
        super(id, marca, modelo, precio, cantidadDisponible, numeroSerie);
        this.tipoConectorMicro = tipoConectorMicro;
        this.tipoMemoriaRAM = tipoMemoriaRAM;
        this.conexionesDiscos = conexionesDiscos;
    }

    // Getters y Setters
    public String getTipoConectorMicro() { return tipoConectorMicro; }
    public String getTipoMemoriaRAM() { return tipoMemoriaRAM; }
    public List<String> getConexionesDiscos() { return conexionesDiscos; }
}
