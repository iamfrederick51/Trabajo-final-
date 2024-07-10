package visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Tienda;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;

public class TiendaGUI extends JFrame {
    private Tienda tienda;
    private JTextField tfNombreCliente;
    private JTextField tfDireccionCliente;
    private JTable tableClientes;
    private JTable tableComponentes;
    private DefaultTableModel modelClientes;
    private DefaultTableModel modelComponentes;

    public TiendaGUI() {
        tienda = new Tienda();
        setTitle("Gestión de Tienda de Electrónica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel para agregar clientes
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new BoxLayout(panelCliente, BoxLayout.Y_AXIS));
        panelCliente.setBorder(BorderFactory.createTitledBorder("Agregar Cliente"));
        
        tfNombreCliente = new JTextField(20);
        tfDireccionCliente = new JTextField(20);
        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        btnAgregarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });
        
        panelCliente.add(new JLabel("Nombre:"));
        panelCliente.add(tfNombreCliente);
        panelCliente.add(new JLabel("Dirección:"));
        panelCliente.add(tfDireccionCliente);
        panelCliente.add(btnAgregarCliente);

        // Panel para mostrar clientes
        modelClientes = new DefaultTableModel(new Object[]{"ID", "Nombre", "Dirección"}, 0);
        tableClientes = new JTable(modelClientes);
        JScrollPane scrollClientes = new JScrollPane(tableClientes);

        // Panel para mostrar componentes
        modelComponentes = new DefaultTableModel(new Object[]{"ID", "Marca", "Modelo", "Precio", "Cantidad", "Serie"}, 0);
        tableComponentes = new JTable(modelComponentes);
        JScrollPane scrollComponentes = new JScrollPane(tableComponentes);

        // Layout principal
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollClientes, scrollComponentes);
        getContentPane().add(panelCliente, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private void agregarCliente() {
        String nombre = tfNombreCliente.getText();
        String direccion = tfDireccionCliente.getText();
        int idCliente = generarIdCliente();
        Cliente cliente = new Cliente(idCliente, nombre, direccion, direccion);
        
        // Agregar cliente a la tienda
        tienda.agregarCliente(cliente);
        
        // Actualizar modelo de la tabla de clientes
        modelClientes.addRow(new Object[]{idCliente, nombre, direccion});
    }

    private int generarIdCliente() {
        Random rand = new Random();
        int idCliente;
        do {
            idCliente = 100000 + rand.nextInt(900000);
        } while (tienda.getClientes().containsKey(idCliente));
        return idCliente;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TiendaGUI().setVisible(true);
            }
        });
    }
}

