package visual;

import javax.swing.*;
import logico.Customer;
import logico.HardDrive;
import logico.Microprocessor;
import logico.MotherBoard;
import logico.Ram;
import logico.StoreManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private StoreManager storeManager;
    private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtSerialNumber;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTextField txtSocketType;
    private JTextField txtRamType;
    private JTextField txtConnections;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtAddress;
    private JTable tableComponents;
    private JTable tableCustomers;
    private JComboBox<String> comboBoxComponentType;

    public MainFrame() {
        storeManager = new StoreManager();
        initialize();
    }

    private void initialize() {
        setTitle("PC Store Manager");
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Component Management UI
        JLabel lblBrand = new JLabel("Brand:");
        lblBrand.setBounds(10, 10, 80, 25);
        getContentPane().add(lblBrand);

        txtBrand = new JTextField();
        txtBrand.setBounds(100, 10, 160, 25);
        getContentPane().add(txtBrand);

        JLabel lblModel = new JLabel("Model:");
        lblModel.setBounds(10, 40, 80, 25);
        getContentPane().add(lblModel);

        txtModel = new JTextField();
        txtModel.setBounds(100, 40, 160, 25);
        getContentPane().add(txtModel);

        JLabel lblSerialNumber = new JLabel("Serial Number:");
        lblSerialNumber.setBounds(10, 70, 100, 25);
        getContentPane().add(lblSerialNumber);

        txtSerialNumber = new JTextField();
        txtSerialNumber.setBounds(120, 70, 160, 25);
        getContentPane().add(txtSerialNumber);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(10, 100, 80, 25);
        getContentPane().add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(100, 100, 160, 25);
        getContentPane().add(txtPrice);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(10, 130, 80, 25);
        getContentPane().add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(100, 130, 160, 25);
        getContentPane().add(txtQuantity);

        JLabel lblSocketType = new JLabel("Socket Type:");
        lblSocketType.setBounds(10, 160, 80, 25);
        getContentPane().add(lblSocketType);

        txtSocketType = new JTextField();
        txtSocketType.setBounds(100, 160, 160, 25);
        getContentPane().add(txtSocketType);

        JLabel lblRamType = new JLabel("RAM Type:");
        lblRamType.setBounds(10, 190, 80, 25);
        getContentPane().add(lblRamType);

        txtRamType = new JTextField();
        txtRamType.setBounds(100, 190, 160, 25);
        getContentPane().add(txtRamType);

        JLabel lblConnections = new JLabel("Connections:");
        lblConnections.setBounds(10, 220, 80, 25);
        getContentPane().add(lblConnections);

        txtConnections = new JTextField();
        txtConnections.setBounds(100, 220, 160, 25);
        getContentPane().add(txtConnections);

        comboBoxComponentType = new JComboBox<>(new String[]{"MotherBoard", "Microprocessor", "Ram", "HardDrive"});
        comboBoxComponentType.setBounds(100, 250, 160, 25);
        getContentPane().add(comboBoxComponentType);

        JButton btnAddComponent = new JButton("Add Component");
        btnAddComponent.setBounds(10, 280, 160, 25);
        getContentPane().add(btnAddComponent);

        JButton btnUpdateComponent = new JButton("Update Component");
        btnUpdateComponent.setBounds(180, 280, 160, 25);
        getContentPane().add(btnUpdateComponent);

        JButton btnDeleteComponent = new JButton("Delete Component");
        btnDeleteComponent.setBounds(350, 280, 160, 25);
        getContentPane().add(btnDeleteComponent);

        JScrollPane scrollPaneComponents = new JScrollPane();
        scrollPaneComponents.setBounds(10, 320, 960, 120);
        getContentPane().add(scrollPaneComponents);

        tableComponents = new JTable();
        scrollPaneComponents.setViewportView(tableComponents);

        // Customer Management UI
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(10, 450, 80, 25);
        getContentPane().add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(100, 450, 160, 25);
        getContentPane().add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(10, 480, 80, 25);
        getContentPane().add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setBounds(100, 480, 160, 25);
        getContentPane().add(txtLastName);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(10, 510, 80, 25);
        getContentPane().add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(100, 510, 160, 25);
        getContentPane().add(txtAddress);

        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setBounds(10, 540, 160, 25);
        getContentPane().add(btnAddCustomer);

        JButton btnUpdateCustomer = new JButton("Update Customer");
        btnUpdateCustomer.setBounds(180, 540, 160, 25);
        getContentPane().add(btnUpdateCustomer);

        JButton btnDeleteCustomer = new JButton("Delete Customer");
        btnDeleteCustomer.setBounds(350, 540, 160, 25);
        getContentPane().add(btnDeleteCustomer);

        JScrollPane scrollPaneCustomers = new JScrollPane();
        scrollPaneCustomers.setBounds(300, 450, 670, 120);
        getContentPane().add(scrollPaneCustomers);

        tableCustomers = new JTable();
        scrollPaneCustomers.setViewportView(tableCustomers);

        // Event Listeners
        btnAddComponent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addComponent();
                updateComponentTable();
            }
        });

        btnUpdateComponent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateComponent();
                updateComponentTable();
            }
        });

        btnDeleteComponent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteComponent();
                updateComponentTable();
            }
        });

        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomer();
                updateCustomerTable();
            }
        });

        btnUpdateCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
                updateCustomerTable();
            }
        });

        btnDeleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
                updateCustomerTable();
            }
        });

        updateComponentTable();
        updateCustomerTable();
    }

    private void addComponent() {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        String serialNumber = txtSerialNumber.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String componentType = (String) comboBoxComponentType.getSelectedItem();

        if (componentType.equals("MotherBoard")) {
            String socketType = txtSocketType.getText();
            String ramType = txtRamType.getText();
            String[] connections = txtConnections.getText().split(",");
            MotherBoard motherBoard = new MotherBoard(brand, model, serialNumber, price, quantity, socketType, ramType, connections);
            storeManager.addComponent(motherBoard);
        } else if (componentType.equals("Microprocessor")) {
            String socketType = txtSocketType.getText();
            double speed = Double.parseDouble(txtRamType.getText());
            Microprocessor microprocessor = new Microprocessor(brand, model, serialNumber, price, quantity, socketType, speed);
            storeManager.addComponent(microprocessor);
        } else if (componentType.equals("Ram")) {
            int size = Integer.parseInt(txtSocketType.getText());
            String type = txtRamType.getText();
            Ram ram = new Ram(brand, model, serialNumber, price, quantity, size, type);
            storeManager.addComponent(ram);
        } else if (componentType.equals("HardDrive")) {
            int capacity = Integer.parseInt(txtSocketType.getText());
            String connectionType = txtRamType.getText();
            HardDrive hardDrive = new HardDrive(brand, model, serialNumber, price, quantity, capacity, connectionType);
            storeManager.addComponent(hardDrive);
        }
    }

    private void updateComponent() {
        int selectedRow = tableComponents.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(tableComponents.getValueAt(selectedRow, 0).toString());
            String brand = txtBrand.getText();
            String model = txtModel.getText();
            String serialNumber = txtSerialNumber.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            String componentType = (String) comboBoxComponentType.getSelectedItem();

            if (componentType.equals("MotherBoard")) {
                String socketType = txtSocketType.getText();
                String ramType = txtRamType.getText();
                String[] connections = txtConnections.getText().split(",");
                MotherBoard motherBoard = new MotherBoard(brand, model, serialNumber, price, quantity, socketType, ramType, connections);
                motherBoard.setId(id);
                storeManager.updateComponent(motherBoard);
            } else if (componentType.equals("Microprocessor")) {
                String socketType = txtSocketType.getText();
                double speed = Double.parseDouble(txtRamType.getText());
                Microprocessor microprocessor = new Microprocessor(brand, model, serialNumber, price, quantity, socketType, speed);
                microprocessor.setId(id);
                storeManager.updateComponent(microprocessor);
            } else if (componentType.equals("Ram")) {
                int size = Integer.parseInt(txtSocketType.getText());
                String type = txtRamType.getText();
                Ram ram = new Ram(brand, model, serialNumber, price, quantity, size, type);
                ram.setId(id);
                storeManager.updateComponent(ram);
            } else if (componentType.equals("HardDrive")) {
                int capacity = Integer.parseInt(txtSocketType.getText());
                String connectionType = txtRamType.getText();
                HardDrive hardDrive = new HardDrive(brand, model, serialNumber, price, quantity, capacity, connectionType);
                hardDrive.setId(id);
                storeManager.updateComponent(hardDrive);
            }
        }
    }

    private void deleteComponent() {
        int selectedRow = tableComponents.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(tableComponents.getValueAt(selectedRow, 0).toString());
            storeManager.removeComponent(id);
        }
    }

    private void addCustomer() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        Customer customer = new Customer(firstName, lastName, address);
        storeManager.addCustomer(customer);
    }

    private void updateCustomer() {
        int selectedRow = tableCustomers.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(tableCustomers.getValueAt(selectedRow, 0).toString());
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String address = txtAddress.getText();
            Customer customer = new Customer(firstName, lastName, address);
            customer.setId(id);
            storeManager.updateCustomer(customer);
        }
    }

    private void deleteCustomer() {
        int selectedRow = tableCustomers.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(tableCustomers.getValueAt(selectedRow, 0).toString());
            storeManager.removeCustomer(id);
        }
    }

    private void updateComponentTable() {
        String[] columnNames = {"ID", "Brand", "Model", "Serial Number", "Price", "Quantity", "Type"};
        List<logico.Component> components = storeManager.getComponents();
        String[][] data = new String[components.size()][7];
        for (int i = 0; i < components.size(); i++) {
            logico.Component component = components.get(i);
            data[i][0] = String.valueOf(component.getId());
            data[i][1] = component.getBrand();
            data[i][2] = component.getModel();
            data[i][3] = component.getSerialNumber();
            data[i][4] = String.valueOf(component.getPrice());
            data[i][5] = String.valueOf(component.getQuantity());
            data[i][6] = component.getClass().getSimpleName();
        }
        tableComponents.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void updateCustomerTable() {
        String[] columnNames = {"ID", "First Name", "Last Name", "Address"};
        List<Customer> customers = storeManager.getCustomers();
        String[][] data = new String[customers.size()][4];
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            data[i][0] = String.valueOf(customer.getId());
            data[i][1] = customer.getFirstName();
            data[i][2] = customer.getLastName();
            data[i][3] = customer.getAddress();
        }
        tableCustomers.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
