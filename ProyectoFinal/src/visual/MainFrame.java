package visual;

import logico.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private List<User> users = new ArrayList<>();
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StoreManager storeManager;

    public MainFrame() {
        storeManager = new StoreManager();
        storeManager.loadComponents();
        storeManager.loadCustomers("clientes.dat");
        users.add(new User("admin", "password", "Administrador")); // Default user
        initialize();
    }

    private void initialize() {
        setTitle("PC Store La Pulguita");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createRegisterPanel(), "register");
        mainPanel.add(createAdminPanel(), "admin");
        mainPanel.add(createUserPanel(), "user");

        getContentPane().add(mainPanel);

        cardLayout.show(mainPanel, "login");
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        loginPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("PC Store La Pulguita");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextField userField = new JTextField(15);
        userField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPasswordField passField = new JPasswordField(15);
        passField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel roleLabel = new JLabel("Rol:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        String[] roles = {"Administrador", "Usuario"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton loginButton = createStyledButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if (authenticate(username, password, roleCombo.getSelectedItem().toString())) {
                    if (roleCombo.getSelectedItem().toString().equals("Administrador")) {
                        cardLayout.show(mainPanel, "admin");
                    } else {
                        cardLayout.show(mainPanel, "user");
                    }
                }
            }
        });

        JButton registerButton = createStyledButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        loginPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(roleLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(roleCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        loginPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        loginPanel.add(registerButton, gbc);

        return loginPanel;
    }

    private JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        registerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Registrar Usuario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));

        JLabel userLabel = new JLabel("Nuevo Usuario:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel passLabel = new JLabel("Nueva Contraseña:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel roleLabel = new JLabel("Rol:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        String[] roles = {"Administrador", "Usuario"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        roleCombo.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton createAccountButton = createStyledButton("Crear Cuenta");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = usernameField.getText();
                String newPassword = new String(passwordField.getPassword());
                String role = roleCombo.getSelectedItem().toString();
                if (createUser(newUsername, newPassword, role)) {
                    JOptionPane.showMessageDialog(registerPanel, "Cuenta creada exitosamente.");
                    cardLayout.show(mainPanel, "login");
                } else {
                    JOptionPane.showMessageDialog(registerPanel, "El nombre de usuario ya existe.");
                }
            }
        });

        JButton backButton = createStyledButton("Atrás");
        backButton.setBackground(new Color(255, 99, 71));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 30, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        registerPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        registerPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        registerPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(roleLabel, gbc);

        gbc.gridx = 1;
        registerPanel.add(roleCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerPanel.add(createAccountButton, gbc);

        gbc.gridy = 5;
        registerPanel.add(backButton, gbc);

        return registerPanel;
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new BorderLayout());
        adminPanel.setBackground(Color.WHITE);

        JPanel menuPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuPanel.setBackground(new Color(230, 230, 250));

        JButton btnComponentManagement = createStyledButton("Gestión de Componentes");
        JButton btnCustomerManagement = createStyledButton("Gestión de Clientes");
        JButton btnSalesManagement = createStyledButton("Gestión de Ventas");
        JButton btnWarehouseManagement = createStyledButton("Gestión de Almacén");
        JButton btnExit = createStyledButton("Salir");

        menuPanel.add(btnComponentManagement);
        menuPanel.add(btnCustomerManagement);
        menuPanel.add(btnSalesManagement);
        menuPanel.add(btnWarehouseManagement);
        menuPanel.add(btnExit);

        adminPanel.add(menuPanel, BorderLayout.NORTH);

        btnComponentManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                adminPanel.add(menuPanel, BorderLayout.NORTH);
                adminPanel.add(createComponentManagementPanel(), BorderLayout.CENTER);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        btnCustomerManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                adminPanel.add(menuPanel, BorderLayout.NORTH);
                adminPanel.add(createCustomerManagementPanel(), BorderLayout.CENTER);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        btnSalesManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                adminPanel.add(menuPanel, BorderLayout.NORTH);
                adminPanel.add(createSalesManagementPanel(), BorderLayout.CENTER);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        btnWarehouseManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminPanel.removeAll();
                adminPanel.add(menuPanel, BorderLayout.NORTH);
                adminPanel.add(createWarehouseManagementPanel(), BorderLayout.CENTER);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        adminPanel.add(new JLabel("Seleccione una opción del menú para comenzar"), BorderLayout.CENTER);
        return adminPanel;
    }

    private JPanel createComponentManagementPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Gestión de Componentes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblBrand = new JLabel("Marca:");
        lblBrand.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblBrand, gbc);

        JTextField txtBrand = new JTextField(15);
        txtBrand.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtBrand, gbc);

        JLabel lblModel = new JLabel("Modelo:");
        lblModel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblModel, gbc);

        JTextField txtModel = new JTextField(15);
        txtModel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtModel, gbc);

        JLabel lblSerialNumber = new JLabel("Número de Serie:");
        lblSerialNumber.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblSerialNumber, gbc);

        JTextField txtSerialNumber = new JTextField(15);
        txtSerialNumber.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtSerialNumber, gbc);

        JLabel lblPrice = new JLabel("Precio:");
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblPrice, gbc);

        JTextField txtPrice = new JTextField(15);
        txtPrice.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtPrice, gbc);

        JLabel lblQuantity = new JLabel("Cantidad:");
        lblQuantity.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblQuantity, gbc);

        JTextField txtQuantity = new JTextField(15);
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtQuantity, gbc);

        JLabel lblSocketType = new JLabel("Tipo de Socket:");
        lblSocketType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblSocketType, gbc);

        JTextField txtSocketType = new JTextField(15);
        txtSocketType.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtSocketType, gbc);

        JLabel lblRamType = new JLabel("Tipo de RAM:");
        lblRamType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblRamType, gbc);

        JTextField txtRamType = new JTextField(15);
        txtRamType.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtRamType, gbc);

        JLabel lblConnections = new JLabel("Conexiones:");
        lblConnections.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblConnections, gbc);

        JTextField txtConnections = new JTextField(15);
        txtConnections.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtConnections, gbc);

        JLabel lblComponentType = new JLabel("Tipo de Componente:");
        lblComponentType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblComponentType, gbc);

        JComboBox<String> comboBoxComponentType = new JComboBox<>(new String[]{"MotherBoard", "Microprocessor", "Ram", "HardDrive"});
        comboBoxComponentType.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(comboBoxComponentType, gbc);

        JButton btnAddComponent = createStyledButton("Agregar Componente");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAddComponent, gbc);

        JButton btnCancel = createStyledButton("Cancelar");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnCancel, gbc);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBrand.setText("");
                txtModel.setText("");
                txtSerialNumber.setText("");
                txtPrice.setText("");
                txtQuantity.setText("");
                txtSocketType.setText("");
                txtRamType.setText("");
                txtConnections.setText("");
                comboBoxComponentType.setSelectedIndex(0);
            }
        });

        // Acción para agregar componente
        btnAddComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String brand = txtBrand.getText();
                    String model = txtModel.getText();
                    String serialNumber = txtSerialNumber.getText();
                    double price = Double.parseDouble(txtPrice.getText());
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    String type = (String) comboBoxComponentType.getSelectedItem();

                    Component component = null;
                    switch (type) {
                        case "MotherBoard":
                            String socketType = txtSocketType.getText();
                            String ramType = txtRamType.getText();
                            String[] connections = txtConnections.getText().split(",");
                            component = new MotherBoard(brand, model, serialNumber, price, quantity, socketType, ramType, connections);
                            break;
                        case "Microprocessor":
                            socketType = txtSocketType.getText();
                            double speed = Double.parseDouble(txtConnections.getText()); // Aquí se usa txtConnections para la velocidad
                            component = new Microprocessor(brand, model, serialNumber, price, quantity, socketType, speed);
                            break;
                        case "Ram":
                            int size = Integer.parseInt(txtRamType.getText()); // Aquí se usa txtRamType para el tamaño
                            String ramTypeText = txtConnections.getText(); // Aquí se usa txtConnections para el tipo de RAM
                            component = new Ram(brand, model, serialNumber, price, quantity, size, ramTypeText);
                            break;
                        case "HardDrive":
                            int capacity = Integer.parseInt(txtSocketType.getText()); // Aquí se usa txtSocketType para la capacidad
                            String connectionType = txtConnections.getText(); // Aquí se usa txtConnections para el tipo de conexión
                            component = new HardDrive(brand, model, serialNumber, price, quantity, capacity, connectionType);
                            break;
                    }

                    if (component != null) {
                        storeManager.addComponent(component);
                        storeManager.saveComponents();  // Guardar cambios
                        JOptionPane.showMessageDialog(panel, "Componente guardado exitosamente.");
                        txtBrand.setText("");
                        txtModel.setText("");
                        txtSerialNumber.setText("");
                        txtPrice.setText("");
                        txtQuantity.setText("");
                        txtSocketType.setText("");
                        txtRamType.setText("");
                        txtConnections.setText("");
                        comboBoxComponentType.setSelectedIndex(0);
                        cardLayout.show(mainPanel, "admin");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Error en la entrada de datos: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel createCustomerManagementPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Gestión de Clientes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(titleLabel, gbc);

        JLabel lblFirstName = new JLabel("Nombre:");
        lblFirstName.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblFirstName, gbc);

        JTextField txtFirstName = new JTextField(15);
        txtFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtFirstName, gbc);

        JLabel lblLastName = new JLabel("Apellido:");
        lblLastName.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblLastName, gbc);

        JTextField txtLastName = new JTextField(15);
        txtLastName.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtLastName, gbc);

        JLabel lblAddress = new JLabel("Dirección:");
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblAddress, gbc);

        JTextField txtAddress = new JTextField(15);
        txtAddress.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtAddress, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblEmail, gbc);

        JTextField txtEmail = new JTextField(15);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtEmail, gbc);

        JLabel lblPhone = new JLabel("Teléfono:");
        lblPhone.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblPhone, gbc);

        JTextField txtPhone = new JTextField(15);
        txtPhone.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(txtPhone, gbc);

        JButton btnAddCustomer = createStyledButton("Agregar Cliente");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAddCustomer, gbc);

        JButton btnUpdateCustomer = createStyledButton("Actualizar Cliente");
        gbc.gridx = 1;
        panel.add(btnUpdateCustomer, gbc);

        JButton btnDeleteCustomer = createStyledButton("Eliminar Cliente");
        btnDeleteCustomer.setBackground(new Color(255, 99, 71));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(btnDeleteCustomer, gbc);

        JButton btnSaveCustomers = createStyledButton("Guardar Clientes");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(btnSaveCustomers, gbc);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Dirección", "Email", "Teléfono"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scrollPane, gbc);

        // Cargar datos al inicio
        updateCustomerTable(tableModel);

        // Acción para agregar cliente
        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                String address = txtAddress.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();

                Customer customer = new Customer(firstName, lastName, address, email, phone);
                storeManager.addCustomer(customer);
                updateCustomerTable(tableModel);
                JOptionPane.showMessageDialog(panel, "Cliente agregado exitosamente.");
            }
        });

        // Acción para actualizar cliente
        btnUpdateCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String address = txtAddress.getText();
                    String email = txtEmail.getText();
                    String phone = txtPhone.getText();

                    Customer customer = new Customer(firstName, lastName, address, email, phone);
                    customer.setId(id);
                    storeManager.updateCustomer(customer);
                    updateCustomerTable(tableModel);
                    JOptionPane.showMessageDialog(panel, "Cliente actualizado exitosamente.");
                }
            }
        });

        // Acción para eliminar cliente
        btnDeleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    storeManager.removeCustomer(id);
                    updateCustomerTable(tableModel);
                    JOptionPane.showMessageDialog(panel, "Cliente eliminado exitosamente.");
                }
            }
        });

        // Acción para guardar clientes
        btnSaveCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeManager.saveCustomers("clientes.dat");
                JOptionPane.showMessageDialog(panel, "Clientes guardados exitosamente.");
            }
        });

        return panel;
    }

    private JPanel createSalesManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Gestión de Ventas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel salesPanel = new JPanel(new BorderLayout());
        DefaultTableModel salesTableModel = new DefaultTableModel(new Object[]{"Cliente", "Componente", "Cantidad", "Precio Total"}, 0);
        JTable salesTable = new JTable(salesTableModel);
        JScrollPane salesScrollPane = new JScrollPane(salesTable);
        salesPanel.add(salesScrollPane, BorderLayout.CENTER);

        JButton btnNewSale = createStyledButton("Nueva Venta");
        btnNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSale(salesTableModel);
            }
        });

        JButton btnUpdateSale = createStyledButton("Actualizar Venta");
        btnUpdateSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSale(salesTableModel, salesTable.getSelectedRow());
            }
        });

        JButton btnDeleteSale = createStyledButton("Eliminar Venta");
        btnDeleteSale.setBackground(new Color(255, 99, 71));
        btnDeleteSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSale(salesTableModel, salesTable.getSelectedRow());
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(btnNewSale);
        buttonPanel.add(btnUpdateSale);
        buttonPanel.add(btnDeleteSale);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(salesPanel, BorderLayout.CENTER);

        return panel;
    }

    private void createNewSale(DefaultTableModel salesTableModel) {
        JFrame saleFrame = new JFrame("Nueva Venta");
        saleFrame.setSize(400, 300);
        saleFrame.setLocationRelativeTo(null);

        JPanel salePanel = new JPanel(new GridBagLayout());
        salePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblCustomer = new JLabel("Cliente:");
        lblCustomer.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        salePanel.add(lblCustomer, gbc);

        JComboBox<Customer> customerComboBox = new JComboBox<>(storeManager.getCustomers().toArray(new Customer[0]));
        customerComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        salePanel.add(customerComboBox, gbc);

        JLabel lblComponent = new JLabel("Componente:");
        lblComponent.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        salePanel.add(lblComponent, gbc);

        JComboBox<Component> componentComboBox = new JComboBox<>(storeManager.getComponents().toArray(new Component[0]));
        componentComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        salePanel.add(componentComboBox, gbc);

        JLabel lblQuantity = new JLabel("Cantidad:");
        lblQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        salePanel.add(lblQuantity, gbc);

        JTextField txtQuantity = new JTextField(10);
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        salePanel.add(txtQuantity, gbc);

        JLabel lblPrice = new JLabel("Precio Total:");
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        salePanel.add(lblPrice, gbc);

        JTextField txtPrice = new JTextField(10);
        txtPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPrice.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        salePanel.add(txtPrice, gbc);

        JButton btnCalculate = createStyledButton("Calcular Precio");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        salePanel.add(btnCalculate, gbc);

        JButton btnCompleteSale = createStyledButton("Completar Venta");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        salePanel.add(btnCompleteSale, gbc);

        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component selectedComponent = (Component) componentComboBox.getSelectedItem();
                int quantity = Integer.parseInt(txtQuantity.getText());
                if (selectedComponent != null) {
                    double totalPrice = selectedComponent.getPrice() * quantity;
                    txtPrice.setText(String.valueOf(totalPrice));
                }
            }
        });

        btnCompleteSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer selectedCustomer = (Customer) customerComboBox.getSelectedItem();
                Component selectedComponent = (Component) componentComboBox.getSelectedItem();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double totalPrice = Double.parseDouble(txtPrice.getText());

                if (selectedCustomer != null && selectedComponent != null) {
                    try {
                        storeManager.reduceComponentQuantity(selectedComponent.getId(), quantity);
                        storeManager.addSale(new Sale(selectedCustomer, selectedComponent, quantity, totalPrice));
                        salesTableModel.addRow(new Object[]{
                                selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName(),
                                selectedComponent.getBrand() + " " + selectedComponent.getModel(),
                                quantity,
                                totalPrice
                        });
                        JOptionPane.showMessageDialog(saleFrame, "Venta completada con éxito.");
                        saleFrame.dispose();
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(saleFrame, "Error: " + ex.getMessage());
                    }
                }
            }
        });

        saleFrame.add(salePanel);
        saleFrame.setVisible(true);
    }

    private void updateSale(DefaultTableModel salesTableModel, int selectedRow) {
        if (selectedRow >= 0) {
            Sale selectedSale = storeManager.getSales().get(selectedRow);

            JFrame updateFrame = new JFrame("Actualizar Venta");
            updateFrame.setSize(400, 300);
            updateFrame.setLocationRelativeTo(null);

            JPanel updatePanel = new JPanel(new GridBagLayout());
            updatePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            GridBagConstraints gbc = new GridBagConstraints();

            JLabel lblCustomer = new JLabel("Cliente:");
            lblCustomer.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(5, 5, 5, 5);
            updatePanel.add(lblCustomer, gbc);

            JComboBox<Customer> customerComboBox = new JComboBox<>(storeManager.getCustomers().toArray(new Customer[0]));
            customerComboBox.setSelectedItem(selectedSale.getCustomer());
            customerComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            updatePanel.add(customerComboBox, gbc);

            JLabel lblComponent = new JLabel("Componente:");
            lblComponent.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.EAST;
            updatePanel.add(lblComponent, gbc);

            JComboBox<Component> componentComboBox = new JComboBox<>(storeManager.getComponents().toArray(new Component[0]));
            componentComboBox.setSelectedItem(selectedSale.getComponent());
            componentComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            updatePanel.add(componentComboBox, gbc);

            JLabel lblQuantity = new JLabel("Cantidad:");
            lblQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.EAST;
            updatePanel.add(lblQuantity, gbc);

            JTextField txtQuantity = new JTextField(String.valueOf(selectedSale.getQuantity()));
            txtQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            updatePanel.add(txtQuantity, gbc);

            JLabel lblPrice = new JLabel("Precio Total:");
            lblPrice.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            updatePanel.add(lblPrice, gbc);

            JTextField txtPrice = new JTextField(String.valueOf(selectedSale.getTotalPrice()));
            txtPrice.setFont(new Font("Arial", Font.PLAIN, 14));
            txtPrice.setEditable(false);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            updatePanel.add(txtPrice, gbc);

            JButton btnCalculate = createStyledButton("Calcular Precio");
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(10, 0, 10, 0);
            updatePanel.add(btnCalculate, gbc);

            JButton btnUpdateSale = createStyledButton("Actualizar Venta");
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            updatePanel.add(btnUpdateSale, gbc);

            btnCalculate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Component selectedComponent = (Component) componentComboBox.getSelectedItem();
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    if (selectedComponent != null) {
                        double totalPrice = selectedComponent.getPrice() * quantity;
                        txtPrice.setText(String.valueOf(totalPrice));
                    }
                }
            });

            btnUpdateSale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Customer selectedCustomer = (Customer) customerComboBox.getSelectedItem();
                    Component selectedComponent = (Component) componentComboBox.getSelectedItem();
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    double totalPrice = Double.parseDouble(txtPrice.getText());

                    if (selectedCustomer != null && selectedComponent != null) {
                        try {
                            storeManager.reduceComponentQuantity(selectedComponent.getId(), quantity - selectedSale.getQuantity());
                            selectedSale.setCustomer(selectedCustomer);
                            selectedSale.setComponent(selectedComponent);
                            selectedSale.setQuantity(quantity);
                            selectedSale.setTotalPrice(totalPrice);
                            updateSalesTable(salesTableModel);
                            JOptionPane.showMessageDialog(updateFrame, "Venta actualizada con éxito.");
                            updateFrame.dispose();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(updateFrame, "Error: " + ex.getMessage());
                        }
                    }
                }
            });

            updateFrame.add(updatePanel);
            updateFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una venta para actualizar.");
        }
    }

    private void deleteSale(DefaultTableModel salesTableModel, int selectedRow) {
        if (selectedRow >= 0) {
            Sale selectedSale = storeManager.getSales().get(selectedRow);
            storeManager.removeSale(selectedSale);
            storeManager.restockComponent(selectedSale.getComponent().getId(), selectedSale.getQuantity());
            salesTableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(null, "Venta eliminada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una venta para eliminar.");
        }
    }

    private JPanel createWarehouseManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Gestión de Almacén");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Marca", "Modelo", "Cantidad"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnRestock = createStyledButton("Reabastecer");
        btnRestock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String quantityStr = JOptionPane.showInputDialog(panel, "Ingrese la cantidad a reabastecer:");
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        storeManager.restockComponent(id, quantity);
                        updateWarehouseTable(tableModel);
                        JOptionPane.showMessageDialog(panel, "Componente reabastecido exitosamente.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Cantidad inválida.");
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnRestock);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Cargar datos al inicio
        updateWarehouseTable(tableModel);

        return panel;
    }

    private void updateWarehouseTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Limpiar la tabla
        List<Component> components = storeManager.getComponents();
        if (components == null) {
            components = new ArrayList<>();
        }
        for (Component component : components) {
            tableModel.addRow(new Object[]{
                    component.getId(),
                    component.getBrand(),
                    component.getModel(),
                    component.getQuantity()
            });
        }
    }

    private void updateCustomerTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Customer customer : storeManager.getCustomers()) {
            tableModel.addRow(new Object[]{
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getPhone()
            });
        }
    }

    private void updateSalesTable(DefaultTableModel salesTableModel) {
        salesTableModel.setRowCount(0); // Limpiar la tabla
        for (Sale sale : storeManager.getSales()) {
            salesTableModel.addRow(new Object[]{
                    sale.getCustomer().getFirstName() + " " + sale.getCustomer().getLastName(),
                    sale.getComponent().getBrand() + " " + sale.getComponent().getModel(),
                    sale.getQuantity(),
                    sale.getTotalPrice()
            });
        }
    }

    private JPanel createUserPanel() {
        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.WHITE);
        userPanel.add(new JLabel("Bienvenido Usuario"));
        return userPanel;
    }

    private boolean authenticate(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if ((role.equals("Administrador") && user.getRole().equals(role)) ||
                        (role.equals("Usuario") && user.getRole().equals(role))) {
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Usuario, contraseña o rol incorrectos.");
        return false;
    }

    private boolean createUser(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password, role));
        return true;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
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
