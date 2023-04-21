package org.example.View;

import org.example.Controller.ConexionController;
import org.example.Controller.TransaccionController;
import org.example.Model.Cuenta;
import org.example.Model.Deposito;
import org.example.Model.Retiro;
import org.example.Service.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
public class VentanaTransacciones extends JFrame implements ActionListener{
    private final JTextField txtMonto;
    private final JTextField txtMontoRetiro;
    private final JTextField txtClabe;
    private final JTextField txtClabeRetiro;
    private final JTextField txtClabeDestino;
    private final JButton btnDepositar;
    private final JButton btnRetiro;
    public VentanaTransacciones() {
        super("Bank Application");
        setSize(0, 0);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Window listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new VentanaPrincipal().setVisible(true);
            }
        });

        // Panel para deposito
        JPanel panelDeposito = new JPanel(new GridBagLayout());
        panelDeposito.setBorder(BorderFactory.createTitledBorder("Datos para deposito"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        panelDeposito.add(new JLabel("Escribe tu cuenta CLABE:"), c);
        txtClabe = new JTextField(15);
        panelDeposito.add(txtClabe, c);

        panelDeposito.add(new JLabel("Escribe la cuenta CLABE destino:"), c);
        txtClabeDestino = new JTextField(15);
        panelDeposito.add(txtClabeDestino, c);

        panelDeposito.add(new JLabel("Monto a depositar:"), c);
        txtMonto = new JTextField(15);
        panelDeposito.add(txtMonto, c);

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(this);
        panelDeposito.add(btnDepositar, c);

        // Panel para Retiro
        JPanel panelRetiro = new JPanel(new GridBagLayout());
        panelRetiro.setBorder(BorderFactory.createTitledBorder("Datos para retiro"));

        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(5, 5, 5, 5);

        panelRetiro.add(new JLabel("Escribe tu cuenta CLABE:"), d);
        txtClabeRetiro = new JTextField(15);
        panelRetiro.add(txtClabeRetiro, d);

        panelRetiro.add(new JLabel("Monto a retirar:"), d);
        txtMontoRetiro = new JTextField(15);
        panelRetiro.add(txtMontoRetiro, d);

        btnRetiro = new JButton("Retirar");
        btnRetiro.addActionListener(this);
        panelRetiro.add(btnRetiro, d);

        // Agregamos los paneles al contenedor principal
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(panelDeposito);
        contentPane.add(panelRetiro);
        pack();
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed (ActionEvent e){
        TransaccionController transaccionController = new TransaccionController();
        Query query = new Query(new ConexionController());
        if (e.getSource() == btnDepositar) {
            try {
                Cuenta cuentaD = query.obtenerCuentaPorClabe(txtClabe.getText());
                double montoD = Double.parseDouble(txtMonto.getText());
                Deposito deposito = new Deposito(montoD, txtClabeDestino.getText());
                transaccionController.hacerDeposito(deposito,cuentaD);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnRetiro) {
            try {
                Cuenta cuentaR = query.obtenerCuentaPorClabe(txtClabeRetiro.getText());
                double montoR = Double.parseDouble(txtMontoRetiro.getText());
                Retiro retiro = new Retiro(montoR);
                transaccionController.hacerRetiro(retiro,cuentaR);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}