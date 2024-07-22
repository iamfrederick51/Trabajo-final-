package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logico.Customer;
import logico.StoreManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private List<User> users = new ArrayList<>();
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StoreManager storeManager;

    public MainFrame() {
        storeManager = new StoreManager();
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
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        registerPanel.add(createAccountButton, gbc);

        gbc.gridy = 5;
        registerPanel.add(backButton, gbc);

        return registerPanel;
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new BorderLayout());
        adminPanel.setBackground(Color.WHITE);

        JPanel menuPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        menuPanel.setBackground(new Color(230, 230, 250));

        JButton btnComponentManagement = createStyledButton("Gestión de Componentes");
        JButton btnCustomerManagement = createStyledButton("Gestión de Clientes");
        JButton btnSalesManagement = createStyledButton("Gestión de Ventas");
        JButton btnExit = createStyledButton("Salir");

        menuPanel.add(btnComponentManagement);
        menuPanel.add(btnCustomerManagement);
        menuPanel.add(btnSalesManagement);
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
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(titleLabel, gbc);

        JLabel lblBrand = new JLabel("Marca:");
        lblBrand.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblBrand, gbc);

        JTextField txtBrand = new JTextField(15);
        txtBrand.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtBrand, gbc);

        JLabel lblModel = new JLabel("Modelo:");
        lblModel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblModel, gbc);

        JTextField txtModel = new JTextField(15);
        txtModel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtModel, gbc);

        JLabel lblSerialNumber = new JLabel("Número de Serie:");
        lblSerialNumber.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblSerialNumber, gbc);

        JTextField txtSerialNumber = new JTextField(15);
        txtSerialNumber.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtSerialNumber, gbc);

        JLabel lblPrice = new JLabel("Precio:");
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblPrice, gbc);

        JTextField txtPrice = new JTextField(15);
        txtPrice.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtPrice, gbc);

        JLabel lblQuantity = new JLabel("Cantidad:");
        lblQuantity.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblQuantity, gbc);

        JTextField txtQuantity = new JTextField(15);
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtQuantity, gbc);

        JLabel lblSocketType = new JLabel("Tipo de Socket:");
        lblSocketType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lblSocketType, gbc);

        JTextField txtSocketType = new JTextField(15);
        txtSocketType.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtSocketType, gbc);

        JLabel lblRamType = new JLabel("Tipo de RAM:");
        lblRamType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(lblRamType, gbc);

        JTextField txtRamType = new JTextField(15);
        txtRamType.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtRamType, gbc);

        JLabel lblConnections = new JLabel("Conexiones:");
        lblConnections.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(lblConnections, gbc);

        JTextField txtConnections = new JTextField(15);
        txtConnections.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtConnections, gbc);

        JLabel lblComponentType = new JLabel("Tipo de Componente:");
        lblComponentType.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(lblComponentType, gbc);

        JComboBox<String> comboBoxComponentType = new JComboBox<>(new String[]{"MotherBoard", "Microprocessor", "Ram", "HardDrive"});
        comboBoxComponentType.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(comboBoxComponentType, gbc);

        JButton btnAddComponent = createStyledButton("Agregar Componente");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        panel.add(btnAddComponent, gbc);

        JButton btnUpdateComponent = createStyledButton("Actualizar Componente");
        gbc.gridx = 1;
        panel.add(btnUpdateComponent, gbc);

        JButton btnDeleteComponent = createStyledButton("Eliminar Componente");
        btnDeleteComponent.setBackground(new Color(255, 99, 71));
        gbc.gridx = 2;
        panel.add(btnDeleteComponent, gbc);

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
        panel.add(lblFirstName, gbc);

        JTextField txtFirstName = new JTextField(15);
        txtFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtFirstName, gbc);

        JLabel lblLastName = new JLabel("Apellido:");
        lblLastName.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblLastName, gbc);

        JTextField txtLastName = new JTextField(15);
        txtLastName.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtLastName, gbc);

        JLabel lblAddress = new JLabel("Dirección:");
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblAddress, gbc);

        JTextField txtAddress = new JTextField(15);
        txtAddress.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtAddress, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblEmail, gbc);

        JTextField txtEmail = new JTextField(15);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        JLabel lblPhone = new JLabel("Teléfono:");
        lblPhone.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lblPhone, gbc);

        JTextField txtPhone = new JTextField(15);
        txtPhone.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(txtPhone, gbc);

        JButton btnAddCustomer = createStyledButton("Agregar Cliente");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(btnAddCustomer, gbc);

        JButton btnUpdateCustomer = createStyledButton("Actualizar Cliente");
        gbc.gridx = 1;
        panel.add(btnUpdateCustomer, gbc);

        JButton btnDeleteCustomer = createStyledButton("Eliminar Cliente");
        btnDeleteCustomer.setBackground(new Color(255, 99, 71));
        gbc.gridx = 2;
        panel.add(btnDeleteCustomer, gbc);

        return panel;
    }

    private JPanel createSalesManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Gestión de Ventas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JButton btnNewSale = createStyledButton("Nueva Venta");
        btnNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSale();
            }
        });

        JButton btnUpdateSale = createStyledButton("Actualizar Venta");
        btnUpdateSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSale();
            }
        });

        JButton btnDeleteSale = createStyledButton("Eliminar Venta");
        btnDeleteSale.setBackground(new Color(255, 99, 71));
        btnDeleteSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSale();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(btnNewSale);
        buttonPanel.add(btnUpdateSale);
        buttonPanel.add(btnDeleteSale);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void createNewSale() {
        JFrame saleFrame = new JFrame("Nueva Venta");
        saleFrame.setSize(400, 300);
        saleFrame.setLocationRelativeTo(null);

        JPanel salePanel = new JPanel(new GridLayout(6, 2, 10, 10));
        salePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblCustomer = new JLabel("Cliente:");
        JComboBox<Customer> customerComboBox = new JComboBox<>(storeManager.getCustomers().toArray(new Customer[0]));
        salePanel.add(lblCustomer);
        salePanel.add(customerComboBox);

        JLabel lblComponent = new JLabel("Componente:");
        JComboBox<Component> componentComboBox = new JComboBox<>(storeManager.getComponents().toArray(new Component[0]));
        salePanel.add(lblComponent);
        salePanel.add(componentComboBox);

        JLabel lblQuantity = new JLabel("Cantidad:");
        JTextField txtQuantity = new JTextField();
        salePanel.add(lblQuantity);
        salePanel.add(txtQuantity);

        JLabel lblPrice = new JLabel("Precio Total:");
        JTextField txtPrice = new JTextField();
        txtPrice.setEditable(false);
        salePanel.add(lblPrice);
        salePanel.add(txtPrice);

        JButton btnCalculate = createStyledButton("Calcular Precio");
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
        salePanel.add(btnCalculate);

        JButton btnCompleteSale = createStyledButton("Completar Venta");
        btnCompleteSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer selectedCustomer = (Customer) customerComboBox.getSelectedItem();
                Component selectedComponent = (Component) componentComboBox.getSelectedItem();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double totalPrice = Double.parseDouble(txtPrice.getText());

                if (selectedCustomer != null && selectedComponent != null) {
                    storeManager.addSale(new Sale(selectedCustomer, selectedComponent, quantity, totalPrice));
                    JOptionPane.showMessageDialog(saleFrame, "Venta completada con éxito.");
                    saleFrame.dispose();
                }
            }
        });
        salePanel.add(btnCompleteSale);

        saleFrame.add(salePanel);
        saleFrame.setVisible(true);
    }


    private void updateSale() {
        // Implementar lógica para actualizar una venta existente
    }

    private void deleteSale() {
        // Implementar lógica para eliminar una venta
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

class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class Sale {
    private Customer customer;
    private logico.Component component;
    private int quantity;
    private double totalPrice;

    public Sale(Customer customer, logico.Component component, int quantity, double totalPrice) {
        this.customer = customer;
        this.component = component;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public logico.Component getComponent() {
        return component;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
