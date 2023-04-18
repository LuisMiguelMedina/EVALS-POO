package org.example;

import org.example.Controller.ClienteController;
import org.example.Controller.ConexionController;
import org.example.Controller.CuentaController;
import org.example.Controller.TransaccionController;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;
import org.example.Model.Deposito;
import org.example.Model.Retiro;
import org.example.View.Vista;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        vista.setSize(800, 600); // establece el tamaño de la ventana a 800x600 píxeles
        vista.setLocationRelativeTo(null); // centra la ventana en la pantalla
        vista.setVisible(true);
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}