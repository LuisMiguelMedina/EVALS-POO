package src.Controller;

import src.DAO.Conexion;
import src.Model.Cliente;
import src.Model.Cuenta;
import src.Service.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CuentaController {
    ClienteController clienteController = new ClienteController();
    public boolean validarDatosCuenta(Cuenta cuenta) throws IOException {
        if (cuenta.getIdCliente().isEmpty() || cuenta.getIdCuenta().isEmpty() || Double.valueOf(cuenta.getSaldo()).equals(0.0))  {
            return false;
        }
        if (!cuenta.getIdCliente().matches("^C\\d{3}$")){
            return false;
        }
        if (!cuenta.getIdCuenta().matches("^CC\\d{2}$")) {
            return false;
        }
        if (!String.valueOf(cuenta.getSaldo()).matches("^(?:0|[1-9]\\d*)(?:\\.\\d+|\\.0+)?$")) {
            return false;
        }
        return true;
    }
    public void crearCuenta(Cuenta cuenta, Conexion conexion) throws IOException {
        Query query = new Query(conexion);
        String saldoCuenta = String.valueOf(cuenta.getSaldo());
        String[] datosCuentas = {
                cuenta.getIdCliente(),
                cuenta.getIdCuenta(),
                saldoCuenta
        };
        if (validarDatosCuenta(cuenta)) {
            query.escribirRegistroCuenta(datosCuentas);
            System.out.println("Cuenta creada exitosamente.");
        }
        else {
            System.out.println("Datos de cuenta no válidos. No se ha creado la cuenta.");
        }
    }
    public void eliminarCuenta(Cuenta cuenta, Conexion conexion) throws IOException {
        Query query = new Query(conexion);
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        int i = 0;
        for (String[] registro : registrosCuentas) {
            if (registro[0].equals(cuenta.getIdCliente()) && registro[1].equals(cuenta.getIdCuenta())) {
                query.eliminarRegistroCuenta(i);
                System.out.println("Cuenta eliminada exitosamente.");
                return;
            }
            i++;
        }
    }
}