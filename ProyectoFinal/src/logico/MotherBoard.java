package logico;

public class MotherBoard extends Component {
    private String socketType;
    private String ramType;
    private String[] hardDriveConnections;

    public MotherBoard(String brand, String model, String serialNumber, double price, int quantity, String socketType, String ramType, String[] hardDriveConnections) {
        super(brand, model, serialNumber, price, quantity);
        this.socketType = socketType;
        this.ramType = ramType;
        this.hardDriveConnections = hardDriveConnections;
    }

    // Getters y setters...
    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String[] getHardDriveConnections() {
        return hardDriveConnections;
    }

    public void setHardDriveConnections(String[] hardDriveConnections) {
        this.hardDriveConnections = hardDriveConnections;
    }
}


