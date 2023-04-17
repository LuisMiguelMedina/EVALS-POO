package org.example.Controller;

import org.example.Model.Cliente;
import org.example.Service.Query;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
public class ClienteController {
    public boolean validarDatosCliente(Cliente cliente) {
        if (cliente.getIdCliente().isEmpty() || cliente.getNombre().isEmpty() || cliente.getTelefono().isEmpty() || cliente.getCorreo().isEmpty()) {
            return false;
        }
        if (!cliente.getNombre().matches("^[A-Za-z]+(?:\\s[A-Za-z]+){0,3}$")){
            return false;
        }
        if (!cliente.getCorreo().contains("@")) {
            return false;
        }
        if (!cliente.getTelefono().matches("^\\+\\d{1,3}-\\d{1,3}-\\d{3}-\\d{4}$")){
            return false;
        }
        return true;
    }
    public void obtenerCuentasCliente(Cliente cliente) throws SQLException {
        Query query = new Query(new ConexionController());
        String idCliente = cliente.getIdCliente();
        String[] registroCliente = query.obtenerRegistroClientePorId(idCliente);

        if (registroCliente != null) {
            List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
            List<Object> cuentas = new ArrayList<>();

            for (String[] registro : registrosCuentas) {
                if (registro[0].equals(idCliente)) {
                    cuentas.add(registro[1]);
                }
            }
            if (!cuentas.isEmpty()) {
                System.out.println("Las cuentas del cliente " + cliente.getNombre() + " son:");
                for (Object cuenta : cuentas) {
                    System.out.println(cuenta);
                }
            }
            else
                System.out.println("El cliente " + cliente.getNombre() + " no tiene cuentas.");
        }
        else
            System.out.println("No se encontró el cliente con ID " + idCliente);
    }

    public void crearCliente(Cliente cliente) throws SQLException {
        Query query = new Query(new ConexionController());
        String[] datosCliente = {
                String.format("C%03d", Integer.parseInt(query.obtenerUltimoIdCliente().substring(1)) + 1),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getCorreo()
        };

        if (validarDatosCliente(cliente)) {
            query.escribirRegistroCliente(datosCliente);
            System.out.println("Cliente creado exitosamente.");
        } else {
            System.out.println("Datos de cliente no válidos. No se ha creado el cliente.");
        }
    }
    public void eliminarCliente(Cliente cliente) throws SQLException {
        Query query = new Query(new ConexionController());
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
    }
}




