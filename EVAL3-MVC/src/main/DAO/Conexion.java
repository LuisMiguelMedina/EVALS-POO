package src.main.DAO;
public class Conexion {
    private static final String RUTA_ARCHIVOS = "EVAL3-MVC/src/main/CSVArchivos/";
    private static final String RUTA_CLIENTES = "DatosClientes.csv";
    private static final String RUTA_CUENTAS = "DatosCuentas.csv";
    private static final String RUTA_TRANSACCIONES = "DatosTransferencias.csv";
    public String getRutaArchivos() {
        return RUTA_ARCHIVOS;
    }
    public String getRutaClientes() {
        return RUTA_CLIENTES;
    }
    public String getRutaCuentas() {
        return RUTA_CUENTAS;
    }
    public String getRutaTransacciones() {
        return RUTA_TRANSACCIONES;
    }
}