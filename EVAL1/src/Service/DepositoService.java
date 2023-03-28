package src.Service;

import src.Model.Transaccion;

import java.io.IOException;

public class DepositoService {
    private final Query query;

    public DepositoService(Query query) {
        this.query = query;
    }

    public boolean depositarMonto(String idCuentaOrigen, String idCuentaDestino, double monto) throws IOException {
        boolean exito = false;

        // Obtener registros de cuentas y transferencias
        double saldoOrigen = 0;
        double saldoDestino = 0;
        for (String[] registroCuenta : query.obtenerRegistrosCuentas()) {
            if (registroCuenta[1].equals(idCuentaOrigen)) {
                saldoOrigen = Double.parseDouble(registroCuenta[2]);
            }
            if (registroCuenta[1].equals(idCuentaDestino)) {
                saldoDestino = Double.parseDouble(registroCuenta[2]);
            }
        }

        // Validar que hay fondos suficientes en la cuenta de origen
        if (monto <= saldoOrigen) {
            // Actualizar saldo de la cuenta de origen
            double nuevoSaldoOrigen = saldoOrigen - monto;
            query.actualizarSaldoCuenta(idCuentaOrigen, nuevoSaldoOrigen);

            // Actualizar saldo de la cuenta de destino
            double nuevoSaldoDestino = saldoDestino + monto;
            query.actualizarSaldoCuenta(idCuentaDestino, nuevoSaldoDestino);

            // Registrar depósito y retiro en transferencias
            String[] registroDeposito = {idCuentaDestino, "+" + monto, Transaccion.Fecha.getCurrentTimestamp()};
            query.escribirRegistroTransferencia(registroDeposito);

            String[] registroRetiro = {idCuentaOrigen, "-" + monto, Transaccion.Fecha.getCurrentTimestamp()};
            query.escribirRegistroTransferencia(registroRetiro);

            exito = true;
        } else {
            System.out.println("Fondos insuficientes");
        }

        return exito;
    }
}
