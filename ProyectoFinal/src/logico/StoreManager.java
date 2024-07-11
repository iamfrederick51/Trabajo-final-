package logico;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    private List<Component> components;
    private List<Customer> customers;

    public StoreManager() {
        this.components = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Component> getComponentsByType(Class<? extends Component> componentType) {
        List<Component> result = new ArrayList<>();
        for (Component component : components) {
            if (componentType.isInstance(component)) {
                result.add(component);
            }
        }
        return result;
    }

    public void removeComponent(int id) {
        components.removeIf(component -> component.getId() == id);
    }

    public void updateComponent(Component updatedComponent) {
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getId() == updatedComponent.getId()) {
                components.set(i, updatedComponent);
                break;
            }
        }
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}





