package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Component implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String brand;
    private String model;
    private String serialNumber;
    private double price;
    private int quantity;
    private List<Review> reviews;

    public Component(String brand, String model, String serialNumber, double price, int quantity) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.price = price;
        this.quantity = quantity;
        this.reviews = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public int getStock() {
        return getQuantity();
    }

    public String toString() {
        return brand + " " + model;
    }
}
