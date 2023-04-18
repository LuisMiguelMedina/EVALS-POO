package src.Service;

import src.DAO.CRUDRegistros;
import src.DAO.Conexion;

import java.io.IOException;
import java.util.List;

public class Query {
    private CRUDRegistros crudRegistros;
    private final Conexion conexion;
    public Query(Conexion conexion) {
        this.conexion = conexion;
    }

    //Leer registros
    public List<String[]> obtenerRegistrosClientes() throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + conexion.getRutaClientes();
        crudRegistros = new CRUDRegistros(rutaClientes);
        return crudRegistros.leerRegistros();
    }
    public List<String[]> obtenerRegistrosCuentas() throws IOException {
        String rutaCuentas = conexion.getRutaArchivos() + conexion.getRutaCuentas();
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaCuentas);
        return crudRegistros.leerRegistros();
    }
    public List<String[]> obtenerRegistrosTransferencias() throws IOException {
        String rutaTransferencias = conexion.getRutaArchivos() + conexion.getRutaTransacciones();
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaTransferencias);
        return crudRegistros.leerRegistros();
    }
    public String obtenerUltimoIdCliente() throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + conexion.getRutaClientes();
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaClientes);
        List<String[]> registrosClientes = crudRegistros.leerRegistros();
        String ultimoIdCliente = "";
        if (registrosClientes.size() > 0) {
            String[] ultimoRegistro = registrosClientes.get(registrosClientes.size() - 1);
            ultimoIdCliente = ultimoRegistro[0];
        }
        return ultimoIdCliente;
    }
    public String[] obtenerRegistroClientePorId(String idCliente) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + conexion.getRutaClientes();
        crudRegistros = new CRUDRegistros(rutaClientes);
        List<String[]> registrosClientes = crudRegistros.leerRegistros();
        for (String[] registro : registrosClientes) {
            if (registro[0].equals(idCliente)) {
                return registro;
            }
        }
        return null;
    }
    public String obtenerNombreClientePorId(String idCliente) throws IOException {
        List<String[]> registrosClientes = obtenerRegistrosClientes();
        for (String[] registro : registrosClientes) {
            if (registro[0].equals(idCliente)) {
                return registro[1];
            }
        }
        return null;
    }
    public String obtenerSaldoCuentas(String cuentaClabe) throws IOException {
        List<String[]> registrosCuentas = obtenerRegistrosCuentas();
        for (String[] registro : registrosCuentas) {
            if (registro[3].equals(cuentaClabe)) {
                return registro[2];
            }
        }
        return null;
    }
    // Crear Registros
    public void escribirRegistroCliente(String[] registro) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + conexion.getRutaClientes();
        crudRegistros = new CRUDRegistros(rutaClientes);
        crudRegistros.escribirRegistro(registro);
    }

    public void escribirRegistroCuenta(String[] registro) throws IOException {
        String rutaCuentas = conexion.getRutaArchivos() + conexion.getRutaCuentas();
        crudRegistros = new CRUDRegistros(rutaCuentas);
        crudRegistros.escribirRegistro(registro);
    }
    public void escribirRegistroTransaccion(String[] registro) throws IOException {
        String rutaTransferencias = conexion.getRutaArchivos() + conexion.getRutaTransacciones();
        crudRegistros = new CRUDRegistros(rutaTransferencias);
        crudRegistros.escribirRegistro(registro);
    }
    // Update Registros
    public void actualizarCeldaSaldo(int numeroFila, int numeroColumna, String valor) throws IOException {
        String rutaCuentas = conexion.getRutaArchivos() + conexion.getRutaCuentas();
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaCuentas);
        crudRegistros.actualizarCelda(numeroFila, numeroColumna, valor);
    }

    //Delete Registros
    public void eliminarRegistroCliente(int numeroFila) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + conexion.getRutaClientes();
        crudRegistros = new CRUDRegistros(rutaClientes);
        crudRegistros.eliminarRegistro(numeroFila);
    }
    public void eliminarRegistroCuenta(int numeroFila) throws IOException {
        String rutaCuenta = conexion.getRutaArchivos() + conexion.getRutaCuentas();
        crudRegistros = new CRUDRegistros(rutaCuenta);
        crudRegistros.eliminarRegistro(numeroFila);
    }
}