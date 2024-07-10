package logico;

public class HardDrive extends Component {
    private int capacity;
    private String connectionType;

    public HardDrive(String brand, String model, String serialNumber, double price, int quantity, int capacity, String connectionType) {
        super(brand, model, serialNumber, price, quantity);
        this.capacity = capacity;
        this.connectionType = connectionType;
    }

    // Getters y setters...
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}


