package org.example.test;

import org.example.Controller.ClienteController;
import org.example.Controller.ConexionController;
import org.example.Controller.CuentaController;
import org.example.Controller.TransaccionController;
import org.example.Model.Cliente;
import org.example.Model.Cuenta;
import org.example.Model.Deposito;
import org.example.Model.Retiro;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
public class MainTest {
    ConexionController conexionController = new ConexionController();
    ClienteController clienteController = new ClienteController();
    CuentaController cuentaController = new CuentaController();
    TransaccionController transaccionController = new TransaccionController();
    Cliente cliente1 = new Cliente("C999","Juan Perez", "+1-555-123-4567","jperez@mail.com");
    Cliente cliente2 = new Cliente("C998","Perez Juan Alfonso", "+1-555-123-4567","fma@gmail.com");
    Cuenta cuenta1 = new Cuenta(cliente1.getIdCliente(), "CC01", 500.0, "99901");
    Cuenta cuenta2 = new Cuenta("C998", "CC01", 500.0,"99801");
    @Test
    public void testConexion() {
        conexionController.getConnection();
    }
    @Test
    public void testCrearCliente() throws SQLException {
        clienteController.crearCliente(cliente1);
        clienteController.eliminarCliente(cliente1);
    }
    @Test
    public void testCrearCuenta() throws SQLException {
        clienteController.crearCliente(cliente1);
        cuentaController.crearCuenta(cuenta1);
        cuentaController.eliminarCuenta(cuenta1);
        clienteController.eliminarCliente(cliente1);
    }
    @Test
    public void testHacerRetiro() throws SQLException {
        clienteController.crearCliente(cliente1);
        cuentaController.crearCuenta(cuenta1);
        Retiro retiro1 = new Retiro(50.00);
        transaccionController.hacerRetiro(retiro1,cuenta1);
        cuentaController.eliminarCuenta(cuenta1);
        clienteController.eliminarCliente(cliente1);
    }
    @Test
    public void testHacerDeposito() throws SQLException {
        clienteController.crearCliente(cliente1);
        clienteController.crearCliente(cliente2);
        cuentaController.crearCuenta(cuenta1);
        cuentaController.crearCuenta(cuenta2);
        Deposito deposito1 = new Deposito(200.0, cuenta1.getClabe());
        transaccionController.hacerDeposito(deposito1,cuenta2);
        cuentaController.eliminarCuenta(cuenta1);
        cuentaController.eliminarCuenta(cuenta2);
        clienteController.eliminarCliente(cliente1);
        clienteController.eliminarCliente(cliente2);
    }
}