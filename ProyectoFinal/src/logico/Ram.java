package logico;

public class Ram extends Component {
    private int size;
    private String type;

    public Ram(String brand, String model, String serialNumber, double price, int quantity, int size, String type) {
        super(brand, model, serialNumber, price, quantity);
        this.size = size;
        this.type = type;
    }

    // Getters y setters...
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

