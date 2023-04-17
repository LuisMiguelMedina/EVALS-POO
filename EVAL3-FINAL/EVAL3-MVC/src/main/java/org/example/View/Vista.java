package org.example.View;

import org.example.Controller.CuentaController;
import org.example.Controller.ClienteController;
import org.example.Controller.TransaccionController;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Vista extends JFrame implements ActionListener {
    private final JTextField txtNombre;
    private final JTextField txtTelefono;
    private final JTextField txtEmail;
    private final JTextField txtIdCliente;
    private final JTextField txtIdCuenta;
    private final JTextField txtSaldo;
    private final JTextField txtClabe;
    private final JButton btnCrearCliente;
    private final JButton btnCrearCuenta;
    private final JButton btnDepositar;
    private final JButton btnEliminarCuenta;
    public Vista() {
        super("Bank Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Panel para datos del cliente
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridLayout(4, 2));
        panelCliente.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        panelCliente.add(lblNombre);
        panelCliente.add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();
        panelCliente.add(lblTelefono);
        panelCliente.add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        panelCliente.add(lblEmail);
        panelCliente.add(txtEmail);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        txtIdCliente = new JTextField();
        panelCliente.add(lblIdCliente);
        panelCliente.add(txtIdCliente);

        // Panel para datos de la cuenta
        JPanel panelCuenta = new JPanel();
        panelCuenta.setLayout(new GridLayout(4, 2));
        panelCuenta.setBorder(BorderFactory.createTitledBorder("Datos de la Cuenta"));

        JLabel lblIdCuenta = new JLabel("ID Cuenta:");
        txtIdCuenta = new JTextField();
        panelCuenta.add(lblIdCuenta);
        panelCuenta.add(txtIdCuenta);

        JLabel lblSaldo = new JLabel("Saldo:");
        txtSaldo = new JTextField();
        panelCuenta.add(lblSaldo);
        panelCuenta.add(txtSaldo);

        JLabel lblClabe = new JLabel("CLABE:");
        txtClabe = new JTextField();
        panelCuenta.add(lblClabe);
        panelCuenta.add(txtClabe);

        // Panel para botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.addActionListener(this);
        panelBotones.add(btnCrearCliente);

        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.addActionListener(this);
        panelBotones.add(btnCrearCuenta);

        btnEliminarCuenta = new JButton("Eliminar Cuenta");
        btnEliminarCuenta.addActionListener(this);
        panelBotones.add(btnEliminarCuenta);

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(this);
        panelBotones.add(btnDepositar);

        // Agregamos los paneles al contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(panelCliente);
        contentPane.add(panelCuenta);
        contentPane.add(panelBotones);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClienteController clienteController = new ClienteController();
        CuentaController cuentaController = new CuentaController();
        if (e.getSource() == btnCrearCliente) {
            // Código para crear un cliente
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String idCliente = txtIdCliente.getText();
            // Llamar a la función del controlador para crear un cliente
            Cliente cliente = new Cliente(nombre, telefono, email, idCliente);
            try {
                clienteController.crearCliente(cliente);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnCrearCuenta) {
            // Código para crear una cuenta
            String idCliente = txtIdCliente.getText();
            String idCuenta = txtIdCuenta.getText();
            String Saldo = txtSaldo.getText();
            double saldoTotal = Double.parseDouble(Saldo);
            String Clabe = txtClabe.getText();
            // Llamar a la función del controlador para crear una cuenta
            Cuenta cuenta = new Cuenta(idCliente, idCuenta, saldoTotal, Clabe);
            try {
                cuentaController.crearCuenta(cuenta);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } /* else if (e.getSource() == btnDepositar) {
            // Código para realizar un depósito
            String clabe = txtClabe.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            // Llamar a la función del controlador para realizar un depósito
            transaccionController.hacerDeposito(monto, clabe);
        } else if (e.getSource() == btnRetirar) {
            // Código para realizar un retiro
            String clabe = txtClabe.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            // Llamar a la función del controlador para realizar un retiro
            transaccionController.hacerRetiro(monto, clabe);
        } else if (e.getSource() == btnEliminarCuenta) {
            // Código para eliminar una cuenta
            String clabe = txtClabe.getText();
            // Llamar a la función del controlador para eliminar una cuenta
            cuentaController.eliminarCuenta(clabe);
        } else if (e.getSource() == btnEliminarCliente) {
            // Código para eliminar un cliente
            String idCliente = txtIdCliente.getText();
            // Llamar a la función del controlador para eliminar un cliente
            clienteController.eliminarCliente(idCliente);
        } */
    }
}