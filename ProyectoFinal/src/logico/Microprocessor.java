package logico;

public class Microprocessor extends Component {
    private String socketType;
    private double speed;

    public Microprocessor(String brand, String model, String serialNumber, double price, int quantity, String socketType, double speed) {
        super(brand, model, serialNumber, price, quantity);
        this.socketType = socketType;
        this.speed = speed;
    }

    // Getters y setters...
    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

