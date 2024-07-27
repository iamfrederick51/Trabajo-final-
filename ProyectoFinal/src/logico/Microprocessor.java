package logico;

public class Microprocessor extends Component {
    private static final long serialVersionUID = 1L;
    private String socketType;
    private double speed;

    public Microprocessor(String brand, String model, String serialNumber, double price, int quantity, String socketType, double speed) {
        super(brand, model, serialNumber, price, quantity);
        this.socketType = socketType;
        this.speed = speed;
    }

    public String getSocketType() {
        return socketType;
    }

    public double getSpeed() {
        return speed;
    }
}
