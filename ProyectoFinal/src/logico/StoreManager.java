package logico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public void removeSale(Sale sale) {
        sales.remove(sale);
    }
//Método para guardar clientes en un archivo
public void saveCustomers(String filename) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        oos.writeObject(customers);
        oos.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Método para cargar clientes desde un archivo
public void loadCustomers(String filename) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
        customers = (List<Customer>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}
