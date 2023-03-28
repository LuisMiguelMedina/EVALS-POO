package src.Model;

import src.DAO.Conexion;
import src.Service.Query;

import java.io.IOException;
import java.util.List;

public class Retiro extends Transaccion {
    private final String idCuenta;

    public Retiro(String idCuenta, double monto) {
        super(monto);
        this.idCuenta = idCuenta;
    }

    public void hacerRetiro(String idCuenta, double monto) throws IOException {
        Query query = new Query(new Conexion());
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();

        double saldo = 0;
        int i = 0;
        for (String[] registro : registrosCuentas) {
            if (registro[1].equals(idCuenta)) {
                saldo = Double.parseDouble(registro[2]);
                if (monto <= saldo) {
                    double nuevoSaldo = saldo - monto;
                    query.actualizarSaldoCuenta(idCuenta, nuevoSaldo);
                    Retiro retiro = new Retiro(idCuenta,monto);
                    retiro.registrarRetiro(idCuenta);

                    System.out.println("Retiro de " + monto + " realizado con éxito de la cuenta " + idCuenta);
                } else {
                    System.out.println("Saldo insuficiente para realizar el retiro de " + monto + " de la cuenta " + idCuenta);
                }
                break;
            }
            i++;
        }
        if (i == registrosCuentas.size()) {
            System.out.println("No se encontró la cuenta " + idCuenta);
        }
    }
    public void registrarRetiro(String idCuenta) throws IOException {
        Query query = new Query(new Conexion());
        String[] registroTransferencia = {idCuenta, String.valueOf(monto), Transaccion.Fecha.getCurrentTimestamp()};
        query.escribirRegistroTransferencia(registroTransferencia);
    }
}