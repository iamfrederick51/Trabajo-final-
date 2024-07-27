package logico;

public class Ram extends Component {
    private static final long serialVersionUID = 1L;
    private int size;
    private String type;

    public Ram(String brand, String model, String serialNumber, double price, int quantity, int size, String type) {
        super(brand, model, serialNumber, price, quantity);
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
