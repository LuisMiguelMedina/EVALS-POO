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
    public List<String[]> obtenerRegistrosClientes() throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        return crudRegistros.leerRegistros();
    }

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

    public void actualizarRegistroCliente(int numeroFila, String[] registro) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        crudRegistros.actualizarRegistro(numeroFila, registro);
    }

    public void eliminarRegistroCliente(int numeroFila) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        crudRegistros = new CRUDRegistros(rutaClientes);
        crudRegistros.eliminarRegistro(numeroFila);
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

    public List<String> obtenerCuentasCliente(String idCliente) throws IOException {
        List<String[]> registrosCuentas = obtenerRegistrosCuentas();
        List<String> cuentasCliente = new ArrayList<String>();
        for (String[] registroCuenta : registrosCuentas) {
            if (registroCuenta[0].equals(idCliente)) {
                cuentasCliente.add(registroCuenta[1]);
            }
        }
        return cuentasCliente;
    }

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

    public void escribirRegistroTransferencia(String[] registro) throws IOException {
        String rutaTransferencias = conexion.getRutaArchivos() + "/DatosTransferencias.csv";
        crudRegistros = new CRUDRegistros(rutaTransferencias);
        crudRegistros.escribirRegistro(registro);
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

    public String[] buscarClientePorId(String idCliente) throws IOException {
        String rutaClientes = conexion.getRutaArchivos() + "/DatosClientes.csv";
        CRUDRegistros crudRegistros = new CRUDRegistros(rutaClientes);
        List<String[]> registrosClientes = crudRegistros.leerRegistros();

        for (String[] registroCliente : registrosClientes) {
            if (registroCliente[1].equals(idCliente)) {
                return registroCliente;
            }
        }
        return null; // Si no se encuentra el cliente, devuelve null
    }

    public String obtenerIdCuentaPorCliente(String idCliente) throws IOException {
        List<String[]> registrosCuentas = obtenerRegistrosCuentas();
        String idCuenta = null;
        for (String[] registroCuenta : registrosCuentas) {
            if (registroCuenta[0].equals(idCliente)) {
                idCuenta = registroCuenta[1];
                break;
            }
        }
        return idCuenta;
    }
}