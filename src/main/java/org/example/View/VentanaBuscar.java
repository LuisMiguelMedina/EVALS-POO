package org.example.View;

import org.example.Controller.ClienteController;
import org.example.Controller.ConexionController;
import org.example.Controller.CuentaController;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;
import org.example.Service.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaBuscar extends JFrame implements ActionListener {
    private final JTextField txtIdCliente;
    private final JTextField txtnombre;
    private final JTextField txtcorreo;
    private final JTextField txtCLABE;
    private final JButton btnBuscarCliente;
    private final JButton btnBuscarClienteD;
    private final JButton btnBuscarCuenta;
    public VentanaBuscar() {
        super("Bank Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(0, 0);

        // Window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new VentanaPrincipal().setVisible(true);
            }
        });

        // Panel para buscar cliente por ID
        JPanel panelBuscarID = new JPanel(new BorderLayout());
        panelBuscarID.setBorder(BorderFactory.createTitledBorder("Buscar cliente por ID"));

        JPanel panelBuscarIDInputs = new JPanel(new FlowLayout());
        JLabel lblIdCliente = new JLabel("ID del cliente:");
        txtIdCliente = new JTextField(15);
        panelBuscarIDInputs.add(lblIdCliente);
        panelBuscarIDInputs.add(txtIdCliente);

        btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.addActionListener(this);
        panelBuscarIDInputs.add(btnBuscarCliente);

        panelBuscarID.add(panelBuscarIDInputs, BorderLayout.CENTER);

        // Panel para buscar cliente por datos
        JPanel panelBuscarDatos = new JPanel(new BorderLayout());
        panelBuscarDatos.setBorder(BorderFactory.createTitledBorder("Buscar cliente por datos"));

        JPanel panelBuscarDatosInputs = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel lblNombre = new JLabel("Nombre:");
        txtnombre = new JTextField(15);
        panelBuscarDatosInputs.add(lblNombre);
        panelBuscarDatosInputs.add(txtnombre);

        JLabel lblCorreo = new JLabel("Correo electrónico:");
        txtcorreo = new JTextField(15);
        panelBuscarDatosInputs.add(lblCorreo);
        panelBuscarDatosInputs.add(txtcorreo);

        panelBuscarDatos.add(panelBuscarDatosInputs, BorderLayout.CENTER);

        JPanel panelBuscarDatosButton = new JPanel(new FlowLayout());
        btnBuscarClienteD = new JButton("Buscar");
        btnBuscarClienteD.addActionListener(this);
        panelBuscarDatosButton.add(btnBuscarClienteD);

        panelBuscarDatos.add(panelBuscarDatosButton, BorderLayout.SOUTH);

        // Panel para buscar cuenta
        JPanel panelBuscarCuenta = new JPanel(new BorderLayout());
        panelBuscarCuenta.setBorder(BorderFactory.createTitledBorder("Buscar cuenta"));

        JPanel panelBuscarCuentaInputs = new JPanel(new FlowLayout());
        JLabel lblClabe = new JLabel("CLABE:");
        txtCLABE = new JTextField(15);
        panelBuscarCuentaInputs.add(lblClabe);
        panelBuscarCuentaInputs.add(txtCLABE);

        btnBuscarCuenta = new JButton("Buscar");
        btnBuscarCuenta.addActionListener(this);
        panelBuscarCuentaInputs.add(btnBuscarCuenta);

        panelBuscarCuenta.add(panelBuscarCuentaInputs, BorderLayout.CENTER);

        // Agregamos los paneles al contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2, 5, 5));
        contentPane.add(panelBuscarID);
        contentPane.add(panelBuscarDatos);
        contentPane.add(new JPanel());
        contentPane.add(panelBuscarCuenta);

        setLocationRelativeTo(null);
    }
    public void mostrarMensaje(ResultSet rs) throws SQLException {
        JFrame frame = new JFrame();
        frame.setTitle("Mensaje");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        ResultSetMetaData meta = rs.getMetaData();
        int numCols = meta.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= numCols; i++) {
                String columnName = meta.getColumnName(i);
                String columnValue = rs.getString(i);
                JLabel label = new JLabel(columnName + ":");
                JLabel dato = new JLabel(columnValue);
                panel.add(label);
                panel.add(dato);
            }
        }
        rs.close();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Query query = new Query(new ConexionController());
        if (e.getSource() == btnBuscarCliente) {
            try {
                mostrarMensaje(query.obtenerClientePorIdClienteArray(txtIdCliente.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnBuscarCuenta) {
            try {
                System.out.println(query.obtenerCuentaPorClabe(txtCLABE.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnBuscarClienteD){
            try {
                mostrarMensaje(query.obtenerClientePorPorNombreYCorreoArray(txtnombre.getText(),txtcorreo.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}