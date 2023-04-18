package Model;

import DAO.Conexion;
import Service.Query;
import java.io.IOException;
import java.util.List;
public class Retiro extends Transaccion {
    public Retiro(double monto) {
        super(monto);
    }
    public void hacerRetiro(Cuenta cuenta) throws IOException {
        Query query = new Query(new Conexion());
        String saldoCuentaString = query.obtenerSaldoCuentas(cuenta.getClabe());
        double saldoCuenta = Double.parseDouble(saldoCuentaString);
        double fondoTotal = saldoCuenta - monto;
        String fondoTotalString = Double.toString(fondoTotal);
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        int i = 0;
        for (String[] registro : registrosCuentas) {
            if (registro[3].equals(cuenta.getClabe())) {
                query.actualizarCeldaSaldo(i,2,fondoTotalString);
            }
            i++;
        }
        String[] retiroRegistro = {cuenta.getClabe(), "-" + monto, Transaccion.Fecha.getCurrentTimestamp()};
        query.escribirRegistroTransaccion(retiroRegistro);
        System.out.println("Tu cuenta " + cuenta.getClabe() + " retiró -" + monto + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
    }
}