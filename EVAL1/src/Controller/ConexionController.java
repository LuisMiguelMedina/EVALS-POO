package src.Controller;

import src.DAO.Conexion;
import java.io.File;
public class ConexionController {
    public void validarConexion() {
        Conexion conexion = new Conexion();
        File carpetaArchivos = new File(conexion.getRutaArchivos());
        if (carpetaArchivos.exists() && carpetaArchivos.isDirectory()) {
            String[] nombresArchivos = {conexion.getRutaClientes(), conexion.getRutaCuentas(), conexion.getRutaTransacciones()};
            for (String nombreArchivo : nombresArchivos) {
                File archivo = new File(carpetaArchivos, nombreArchivo);
                if (!archivo.exists() || archivo.isDirectory()) {
                    System.out.println("Arhivos no encontrados");
                    return;
                }
            }
        }
        System.out.println("Conexion exitosa!");
    }
}