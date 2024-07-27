package logico;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;
    private Customer customer;
    private Component component;
    private int quantity;
    private double totalPrice;
    private Date date;

    public Sale(Customer customer, Component component, int quantity, double totalPrice) {
        this.customer = customer;
        this.component = component;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = new Date(); // Establecer la fecha de la venta al momento de la creación
    }

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

    public Date getDate() {
        return date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}