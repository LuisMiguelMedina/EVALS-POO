package src.Service;

import src.Model.Transaccion;

import java.io.IOException;

public class RetiroService {
    private final Query query;

    public RetiroService(Query query) {
        this.query = query;
    }

    public boolean retirarMonto(String idCuenta, double monto) throws IOException {
        boolean exito = false;

        // Obtener registros de cuentas y transferencias
        double saldoActual = 0;
        for (String[] registroCuenta : query.obtenerRegistrosCuentas()) {
            if (registroCuenta[1].equals(idCuenta)) {
                saldoActual = Double.parseDouble(registroCuenta[2]);
                break;
            }
        }

        // Validar que hay fondos suficientes
        if (monto <= saldoActual) {
            // Actualizar saldo de la cuenta
            double nuevoSaldo = saldoActual - monto;
            query.actualizarSaldoCuenta(idCuenta, nuevoSaldo);

            // Registrar retiro en transferencias
            String[] registroTransferencia = {idCuenta, "-" + monto, Transaccion.Fecha.getCurrentTimestamp()};
            query.escribirRegistroTransferencia(registroTransferencia);

            exito = true;
        } else {
            System.out.println("Fondos insuficientes");
        }

        return exito;
    }
}
