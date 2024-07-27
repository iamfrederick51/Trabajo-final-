package logico;

import java.io.*;
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
        component.setId(components.size() + 1);
        components.add(component);
    }

    public void updateComponent(Component component) {
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getId() == component.getId()) {
                components.set(i, component);
                return;
            }
        }
    }

    public void removeComponent(int id) {
        components.removeIf(component -> component.getId() == id);
    }

    public void reduceComponentQuantity(int id, int quantity) {
        for (Component component : components) {
            if (component.getId() == id) {
                int newQuantity = component.getQuantity() - quantity;
                if (newQuantity < 0) {
                    throw new IllegalArgumentException("Cantidad insuficiente en el inventario.");
                }
                component.setQuantity(newQuantity);
                return;
            }
        }
        throw new IllegalArgumentException("Componente no encontrado.");
    }

    public void restockComponent(int id, int quantity) {
        for (Component component : components) {
            if (component.getId() == id) {
                component.setQuantity(component.getQuantity() + quantity);
                return;
            }
        }
        throw new IllegalArgumentException("Componente no encontrado.");
    }

    public void saveComponents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("component.dat"))) {
            oos.writeObject(components);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadComponents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("component.dat"))) {
            components = (List<Component>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Component> getComponents() {
        if (components == null) {
            components = new ArrayList<>();
        }
        return components;
    }

    public void addCustomer(Customer customer) {
        customer.setId(customers.size() + 1);
        customers.add(customer);
    }

    public void updateCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, customer);
                return;
            }
        }
    }

    public void removeCustomer(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }

    public List<Customer> getCustomers() {
        if (customers == null) {
            customers = new ArrayList<>();
        }
        return customers;
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public void removeSale(Sale sale) {
        sales.remove(sale);
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void saveCustomers(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(customers);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomers(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            customers = (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
