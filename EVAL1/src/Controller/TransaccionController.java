package src.Controller;

import src.DAO.Conexion;
import src.Model.Cuenta;
import src.Model.Deposito;
import src.Service.Query;
import java.io.IOException;
import java.util.List;

public class TransaccionController {
    //Deposito
    public boolean validarDatosDeposito(Deposito deposito) throws IOException {
        if (Double.valueOf(deposito.getMonto()).equals(0.0) || deposito.getClabeDestino().isEmpty()){
            return false;
        }
        if (deposito.getMonto() <= 0){
            System.out.println("Monto debe ser mayor que 0");
            return false;
        }
        if (!deposito.getClabeDestino().matches("^0*[1-9][0-9]*$")) {
            return false;
        }
        if (!validarCuentaDestino(deposito)){
            return false;
        }
        return true;
    }
    public boolean validarSaldo(Deposito deposito, Cuenta cuenta) {
        if (cuenta.getSaldo() < deposito.getMonto()){
            System.out.println("Fondos insuficientes");
            return false;
        }
        return true;
    }
    public boolean validarCuentaDestino(Deposito deposito) throws IOException {
        Query query = new Query(new Conexion());
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        for (String[] registro : registrosCuentas) {
            if (registro[3].equals(deposito.getClabeDestino())) {
                return true;
            }
        }
        System.out.println("Cuenta destino no encontrada");
        return false;
    }
    public void hacerDeposito(Deposito deposito, Cuenta cuenta) throws IOException {
        CuentaController cuentaController = new CuentaController();
        Query query = new Query(new Conexion());
        if (cuentaController.validarDatosCuenta(cuenta)) {
            if (validarDatosDeposito(deposito) && validarSaldo(deposito, cuenta)) {
                deposito.depositarMonto(cuenta, deposito);
                System.out.println("Deposito realizado! " + deposito.getMonto() + " a la cuenta de " + query.obtenerNombreClientePorId(cuenta.getIdCliente()));
            } else
                System.out.println("Datos para deposito invalidos");
        } else
            System.out.println("Error deposito no realizado");
    }
}