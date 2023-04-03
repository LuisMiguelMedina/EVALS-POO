package src.Model;

import src.DAO.Conexion;
import src.Service.Query;

import java.io.IOException;
import java.util.List;

public class Deposito extends Transaccion {
    public String idCuentaDestino;
    public Deposito(double monto,String idCuentaDestino) {
        super(monto);
        this.idCuentaDestino = idCuentaDestino;
    }
    public void depositarMonto(String idCuenta,String idCuentaDestino, double monto) throws IOException {
        Query query = new Query(new Conexion());

        // Obtener registros de cuentas
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();

        for (String[] registro : registrosCuentas) {
            String valorCelda = registro[1];
            boolean existeCuentaDestino = valorCelda.equals(idCuentaDestino);
            boolean existeCuentaLocal = valorCelda.equals(idCuenta);

            double saldo = Double.parseDouble(registro[2]);
            if (existeCuentaDestino && existeCuentaLocal){
                registrarDeposito(idCuenta,saldo,monto, idCuentaDestino, query);
                break;
            }
        }
    }

    public void registrarDeposito(String idCuenta,double saldo, double monto, String idCuentaDestino, Query query) throws IOException {
        if (monto <= 0) {
            System.out.println("Monto debe ser mayor que 0");
            return;
        }
        if (saldo < monto) {
            System.out.println("Fondos insuficientes");
            return;
        }
        //Crear
        double fondoTotal = saldo - monto;
        String montoString = Double.toString(monto);
        String[] depositoRegistro = {idCuentaDestino, "+" + montoString, Transaccion.Fecha.getCurrentTimestamp()};
        System.out.println("La cuenta " + idCuentaDestino + " Recibio +" + monto + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
        query.escribirRegistroTransaccion(depositoRegistro);

        //Actualizar DatosCuentas para deposito
        String fondoTotalString = Double.toString(fondoTotal);
        query.actualizarSaldoCuenta(idCuenta, fondoTotal);
        String[] retiroRegistro = {idCuenta, "-" + fondoTotalString, Transaccion.Fecha.getCurrentTimestamp()};
        System.out.println("La cuenta " + idCuenta + " retiro -" + montoString + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
        query.escribirRegistroTransaccion(retiroRegistro);
    }
}
