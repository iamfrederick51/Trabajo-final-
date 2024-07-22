package logico;

public class MotherBoard extends Component {
    private String socketType;
    private String ramType;
    private String[] connections;

    public MotherBoard(String brand, String model, String serialNumber, double price, int quantity, String socketType, String ramType, String[] connections) {
        super(brand, model, serialNumber, price, quantity);
        this.socketType = socketType;
        this.ramType = ramType;
        this.connections = connections;
    }

    // Métodos getter y setter específicos de MotherBoard
    public String getSocketType() {
        return socketType;
    }

    public String getRamType() {
        return ramType;
    }

    public String[] getConnections() {
        return connections;
    }
}
