package logico;

public class HardDrive extends Component {
    private static final long serialVersionUID = 1L;
    private int capacity;
    private String connectionType;

    public HardDrive(String brand, String model, String serialNumber, double price, int quantity, int capacity, String connectionType) {
        super(brand, model, serialNumber, price, quantity);
        this.capacity = capacity;
        this.connectionType = connectionType;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getConnectionType() {
        return connectionType;
    }
}
