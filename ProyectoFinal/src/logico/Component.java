package logico;

public abstract class Component {
    private static int idCounter = 1;
    private int id;
    private String brand;
    private String model;
    private String serialNumber;
    private double price;
    private int quantity;

    public Component(String brand, String model, String serialNumber, double price, int quantity) {
        this.id = idCounter++;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters y setters...
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


