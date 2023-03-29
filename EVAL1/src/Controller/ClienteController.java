package src.Controller;

import src.DAO.Conexion;
import src.Model.Cliente;
import src.Service.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    public boolean validarDatosCliente(Cliente cliente) throws IOException {
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
    public List<Cliente> obtenerClientes(Cliente cliente, Conexion conexion) throws IOException {
        Query query = new Query(conexion);
        List<String[]> registrosClientes = query.obtenerRegistrosClientes();
        List<Cliente> clientes = new ArrayList<>();

        for (String[] registro : registrosClientes) {
            if (cliente == null || registro[0].equals(cliente.getIdCliente())) {
                Cliente c = new Cliente(registro[0], registro[1], registro[2], registro[3]);
                clientes.add(c);
            }
        }
        return clientes;
    }

    public void crearCliente(Cliente cliente, Conexion conexion) throws IOException {
        Query query = new Query(conexion);
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




