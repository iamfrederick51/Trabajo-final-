package logico;

public abstract class Component {
    private String brand;
    private String model;
    private String serialNumber;
    private double price;
    private int quantity;

    public Component(String brand, String model, String serialNumber, double price, int quantity) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.price = price;
        this.quantity = quantity;
    }

    // Métodos getter y setter
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
