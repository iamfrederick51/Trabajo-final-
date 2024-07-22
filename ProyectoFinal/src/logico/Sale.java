package logico;

public class Sale {
    private Customer customer;
    private Component component;
    private int quantity;
    private double totalPrice;

    public Sale(Customer customer, Component component, int quantity, double totalPrice) {
        this.customer = customer;
        this.component = component;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters y setters
    public Customer getCustomer() {
        return customer;
    }

    public Component getComponent() {
        return component;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
