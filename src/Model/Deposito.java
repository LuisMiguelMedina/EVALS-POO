package src.Model;

import src.Controller.CuentaController;
import src.DAO.Conexion;
import src.Service.Query;
import java.io.IOException;
import java.util.List;

public class Deposito extends Transaccion {
    private final String clabeDestino;
    public Deposito(double monto, String clabeDestino) {
        super(monto);
        this.clabeDestino = clabeDestino;
    }
    public String getClabeDestino() {
        return clabeDestino;
    }
    public void depositarMonto(Cuenta cuenta, Deposito deposito) throws IOException {
        Query query = new Query(new Conexion());
        Retiro retiro = new Retiro(monto);
        //Retiro
        retiro.hacerRetiro(cuenta);
        // Deposito
        String[] depositoRegistro = {deposito.getClabeDestino(), "+" + deposito.getMonto(), Transaccion.Fecha.getCurrentTimestamp()};
        query.escribirRegistroTransaccion(depositoRegistro);
        registrarDeposito(deposito);
    }
    public void registrarDeposito(Deposito deposito) throws IOException {
        CuentaController cuentaController = new CuentaController();
        Query query = new Query(new Conexion());
        List<String[]> registrosCuentas = query.obtenerRegistrosCuentas();
        double fondoTotal = cuentaController.validarSaldoCuenta(deposito.getClabeDestino()) + deposito.getMonto();
        System.out.println(fondoTotal);
        String fondoTotalString = Double.toString(fondoTotal);
        int i = 0;
        for (String[] registro : registrosCuentas) {
            if (registro[3].equals(deposito.getClabeDestino())) {
                query.actualizarCeldaSaldo(i,2,fondoTotalString);
                System.out.println("La cuenta " + deposito.getClabeDestino() + " recibió +" + deposito.getMonto() + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
                break;
            }
            i++;
        }
    }
}