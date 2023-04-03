package src.Model;

import src.DAO.Conexion;
import src.Service.Query;
import java.io.IOException;
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
        Retiro retiro = new Retiro(deposito.getMonto());
        //Retiro
        retiro.hacerRetiro(cuenta,retiro);
        // Deposito
        String[] depositoRegistro = {deposito.getClabeDestino(), "+" + deposito.getMonto(), Transaccion.Fecha.getCurrentTimestamp()};
        query.escribirRegistroTransaccion(depositoRegistro);
        System.out.println("La cuenta " + deposito.getClabeDestino() + " recibió +" + deposito.getMonto() + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
    }
}