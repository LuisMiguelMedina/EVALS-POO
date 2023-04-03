package src.Model;

import src.DAO.Conexion;
import src.Service.Query;
import java.io.IOException;
public class Retiro extends Transaccion {
    public Retiro(double monto) {
        super(monto);
    }
    public void hacerRetiro(Cuenta cuenta, Retiro retiro) throws IOException {
        Query query = new Query(new Conexion());
        double fondoTotal = cuenta.getSaldo() - retiro.getMonto();
        // Actualizar DatosCuentas para retiro
        query.actualizarSaldoCuenta(cuenta.getClabe(), fondoTotal);
        String[] retiroRegistro = {cuenta.getClabe(), "-" + fondoTotal, Transaccion.Fecha.getCurrentTimestamp()};
        query.escribirRegistroTransaccion(retiroRegistro);
        System.out.println("Tu cuenta " + cuenta.getClabe() + " retiró -" + retiro.getMonto() + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
    }
}