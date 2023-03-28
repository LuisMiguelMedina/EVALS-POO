package src.DAO;

import java.io.File;
public class Conexion {
    private static final String RUTA_ARCHIVOS = "EVAL1/CSVArchivos";
    public boolean validarConexion() {
        File carpetaArchivos = new File(RUTA_ARCHIVOS);
        if (carpetaArchivos.exists() && carpetaArchivos.isDirectory()) {
            String[] nombresArchivos = {"DatosClientes.csv", "DatosCuentas.csv", "DatosTransferencias.csv"};
            for (String nombreArchivo : nombresArchivos) {
                File archivo = new File(carpetaArchivos, nombreArchivo);
                if (!archivo.exists() || archivo.isDirectory()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public String getRutaArchivos() {
        return RUTA_ARCHIVOS;
    }
}
