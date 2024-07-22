package logico;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    private List<Sale> sales;
    private List<Customer> customers;
    private List<Component> components;

    public StoreManager() {
        sales = new ArrayList<>();
        customers = new ArrayList<>();
        components = new ArrayList<>();
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }
}
