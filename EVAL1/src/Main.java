package src;

import src.DAO.Conexion;

import src.Controller.ClienteController;

import src.Model.Cliente;
import src.Model.Cuenta;
import src.Model.Deposito;
import src.Model.Retiro;
import src.Service.Query;

import java.io.IOException;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Conexion conexion = new Conexion();
        Query query = new Query(conexion);
        ClienteController clienteController = new ClienteController();

        // Creamos un cliente
        Cliente cliente1 = new Cliente("18003410001","Juan Perez", "12345678","jperez@mail.com");

        // Validamos los datos del cliente
        clienteController.validarDatosCliente(cliente1);

        // Creamos un nuevo registro de cliente
        ClienteController.crearCliente(cliente1, conexion);

        // Obtenemos los registros de clientes
        ClienteController.obtenerClientes(conexion);

        // Creamos una cuenta para el cliente
        Cuenta cuenta1 = new Cuenta(cliente1.getIdCliente(), "01", 500.0);

        // Validamos los datos de la cuenta
        CuentaController.validarDatosCuenta(cuenta1);

        // Creamos un nuevo registro de cuenta
        CuentaController.crearCuenta(cuenta1, conexion);

        // Obtenemos los registros de cuentas
        CuentaController.obtenerCuentas(conexion);

        // Realizamos un depósito en la cuenta
        Deposito deposito1 = new Deposito(200.0, cuenta1.getIdCuenta());

        // Validamos los datos del depósito
        TransaccionController.validarDatosTransaccion(deposito1);

        // Realizamos el depósito
        DepositoController.realizarDeposito(deposito1, conexion);

        // Obtenemos los registros de transferencias
        TransaccionController.obtenerTransferencias(conexion);

        // Realizamos un retiro de la cuenta
        Retiro retiro1 = new Retiro(cuenta1.getIdCuenta(),50.0);

        // Validamos los datos del retiro
        TransaccionController.validarDatosTransaccion(retiro1);

        // Realizamos el retiro
        RetiroController.realizarRetiro(retiro1, conexion);

        // Obtenemos los registros de transferencias
        TransaccionController.obtenerTransferencias(conexion);

        // Actualizamos el saldo de la cuenta
        CuentaController.actualizarSaldoCuenta(cuenta1.getIdCuenta(), 650.0, conexion);

        // Eliminamos la cuenta
        CuentaController.eliminarCuenta(cuenta1, conexion);

        // Eliminamos el cliente
        ClienteController.eliminarCliente(cliente1, conexion);
    }
}


