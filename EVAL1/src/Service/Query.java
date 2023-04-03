package src.Service;

import src.DAO.CRUDRegistros;
import src.DAO.Conexion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Query {
    private CRUDRegistros crudRegistros;
    private final Conexion conexion;
    public Query(Conexion conexion) {
        this.conexion = conexion;
    }
    public Conexion obtenerConexion() {
        return conexion;
    }
    public boolean validarConexion() {
        return conexion.validarConexion();
    }

    //Cambiar rutas a constantes!
    //Leer registros
    public List<String[]> obtenerRegistrosClientes() throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        return crudRegistros.leerRegistros();
    }
    public List<String[]> obtenerRegistrosCuentas() throws IOException {
        String rutaCuentas = conexion.getRutaArchivos() + "/DatosCuentas.csv";
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaCuentas);
        return crudRegistros.leerRegistros();
    }
    public List<String[]> obtenerRegistrosTransferencias() throws IOException {
        String rutaTransferencias = conexion.getRutaArchivos() + "/DatosTransferencias.csv";
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaTransferencias);
        return crudRegistros.leerRegistros();
    }
    public String obtenerUltimoIdCliente() throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
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
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        List<String[]> registrosClientes = crudRegistros.leerRegistros();
        for (String[] registro : registrosClientes) {
            if (registro[0].equals(idCliente)) {
                return registro;
            }
        }
        return null;
    }
    // Crear Registros
    public void escribirRegistroCliente(String[] registro) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        crudRegistros.escribirRegistro(registro);
    }

    public void escribirRegistroCuenta(String[] registro) throws IOException {
        String rutaCuentas = conexion.getRutaArchivos() + "/DatosCuentas.csv";
        crudRegistros = new CRUDRegistros(rutaCuentas);
        crudRegistros.escribirRegistro(registro);
    }
    public void escribirRegistroTransaccion(String[] registro) throws IOException {
        String rutaTransferencias = conexion.getRutaArchivos() + "/DatosTransferencias.csv";
        crudRegistros = new CRUDRegistros(rutaTransferencias);
        crudRegistros.escribirRegistro(registro);
    }
    // Update Registros
    public void actualizarSaldoCuenta(String idCuenta, double nuevoSaldo) throws IOException {
        List<String[]> registrosCuentas = obtenerRegistrosCuentas();
        int i = 0;
        for (String[] registroCuenta : registrosCuentas) {
            if (registroCuenta[1].equals(idCuenta)) {
                crudRegistros.actualizarCelda(i, 2, String.valueOf(nuevoSaldo));
                break;
            }
            i++;
        }
    }
    //Delete Registros
    public void eliminarRegistroCliente(int numeroFila) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        crudRegistros.eliminarRegistro(numeroFila);
    }

    public void eliminarRegistroCuenta(int numeroFila) throws IOException {
        String rutaCuenta = conexion.getRutaArchivos() + "/DatosCuentas.csv";
        crudRegistros = new CRUDRegistros(rutaCuenta);
        crudRegistros.eliminarRegistro(numeroFila);
    }
}