package src.Controller;

import src.DAO.Conexion;
import src.Model.Cliente;
import src.Service.Query;

import java.io.IOException;
import java.util.List;

public class ClienteController {
    Conexion conexion = new Conexion();
    Query query = new Query(conexion);
    public boolean validarDatosCliente(Cliente cliente) throws IOException {
            if (cliente.getIdCliente().isEmpty() || cliente.getNombre().isEmpty() || cliente.getTelefono().isEmpty() || cliente.getCorreo().isEmpty()) {
                return false;
            }
            if (cliente.getNombre().contains(".*\\d+.*")){
                return false;
            }
            if (!cliente.getCorreo().contains("@")) {
                return false;
            }
            if (!cliente.getTelefono().contains("^\\+\\d{1,3}-\\d{1,3}-\\d{3}-\\d{4}$")){
                return false;
            }
            return true;
    }
    public void obtenerClientes(Cliente cliente, Conexion conexion){

    }
    public void crearCliente(Cliente cliente, Conexion conexion){
        
    }
    public void eliminarCliente(Cliente cliente, Conexion conexion){

    }

}




