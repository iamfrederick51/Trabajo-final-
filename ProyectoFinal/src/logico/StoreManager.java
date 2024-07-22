package logico;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    private List<Component> components;
    private List<Customer> customers;
    private List<Sale> sales;

    public StoreManager() {
        components = new ArrayList<>();
        customers = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void updateComponent(Component component) {
        // Implementar lógica de actualización
    }

    public void removeComponent(int id) {
        components.removeIf(component -> component.getId() == id);
    }

    public List<Component> getComponents() {
        return components;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(Customer customer) {
        // Implementar lógica de actualización
    }

    public void removeCustomer(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public List<Sale> getSales() {
        return sales;
    }
}
