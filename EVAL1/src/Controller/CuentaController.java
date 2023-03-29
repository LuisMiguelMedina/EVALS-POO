package src.Controller;

import src.DAO.Conexion;
import src.Model.Cliente;
import src.Model.Cuenta;
import src.Service.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CuentaController {
    ClienteController clienteController = new ClienteController();
    public boolean validarDatosCuenta(Cuenta cuenta, Conexion conexion) throws IOException {
    Query query = new Query(conexion);
        if (cuenta.getIdCliente().isEmpty() || cuenta.getIdCuenta().isEmpty() || Double.valueOf(cuenta.getSaldo()).equals(0.0))  {
            return false;
        }
        if (!cuenta.getIdCliente().matches("^C\\d{3}$")){
            return false;
        }
        if (!cuenta.getIdCuenta().matches("^CC\\d{2}$")) {
            return false;
        }
        if (!String.valueOf(cuenta.getSaldo()).matches("^(?:0|[1-9]\\d*)(?:\\.\\d+|\\.0+)?$")) {
            return false;
        }
        return true;
    }
    public List<Cuenta> obtenerCuentas(Cuenta cuenta, Conexion conexion) throws IOException {
        Query query = new Query(conexion);
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        List<Cuenta> cuentas = new ArrayList<>();

        for (String[] registro : registrosCuentas) {
            if (cuentas == null || registro[0].equals(cuenta.getIdCliente())) {
                double saldo = Double.parseDouble(registro[2]);
                Cuenta c = new Cuenta(registro[0], registro[1], saldo);
                cuentas.add(c);
            }
        }
        return cuentas;
    }

    public void crearCuenta(Cuenta cuenta, Conexion conexion) throws IOException {
        Query query = new Query(conexion);
        String saldoCuenta = String.valueOf(cuenta.getSaldo());
        String[] datosCuentas = {
                cuenta.getIdCliente(),
                String.format("CC%02d", Integer.parseInt(query.obtenerIdCuentaPorCliente(cuenta.getIdCliente())) + 1),
                saldoCuenta
        };
        if (validarDatosCuenta(cuenta, conexion)) {
            query.escribirRegistroCuenta(datosCuentas);
            System.out.println("Cuenta creada exitosamente.");
        }
        else {
            System.out.println("Datos de cuenta no válidos. No se ha creado la cuenta.");
        }
    }

    public void eliminarCliente(Cliente cliente, Conexion conexion) throws IOException{
        Query query = new Query(conexion);
        List<String[]> registrosClientes = query.obtenerRegistrosClientes();
        int i = 0;

        for (String[] registro : registrosClientes) {
            if (registro[0].equals(cliente.getIdCliente())) {
                query.eliminarRegistroCliente(i);
                System.out.println("Cliente eliminado exitosamente.");
                return;
            }
            i++;
        }
        System.out.println("No se encontró el cliente con ID " + cliente.getIdCliente() + ".");
    }
}