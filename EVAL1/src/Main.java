package src;

import src.Controller.CuentaController;
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
        ClienteController clienteController = new ClienteController();
        CuentaController cuentaController = new CuentaController();

        // Creamos un cliente
        Cliente cliente1 = new Cliente("C003","Juan Perez", "+1-555-123-4567","jperez@mail.com");

        // Validamos los datos del cliente
        clienteController.validarDatosCliente(cliente1);

        // Creamos un nuevo registro de cliente
        clienteController.crearCliente(cliente1, conexion);

        // Obtenemos los registros de clientes
        clienteController.obtenerClientes(cliente1,conexion);

        // Creamos una cuenta para el cliente
        Cuenta cuenta1 = new Cuenta(cliente1.getIdCliente(), "CC02", 500.0);

        // Validamos los datos de la cuenta
        cuentaController.validarDatosCuenta(cuenta1, conexion);

        // Creamos un nuevo registro de cuenta
        cuentaController.crearCuenta(cuenta1, conexion);

        // Obtenemos los registros de cuentas
        cuentaController.obtenerCuentas(cuenta1, conexion);

        /*
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
        cuentaController.eliminarCuenta(cuenta1, conexion);

        // Eliminamos el cliente
        clienteController.eliminarCliente(cliente1, conexion);
        */
    }
}


