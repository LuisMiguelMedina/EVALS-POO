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

}
