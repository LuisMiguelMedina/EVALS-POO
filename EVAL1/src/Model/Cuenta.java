package src.Model;

public class Cuenta {
    private final String idCliente;
    private final String idCuenta;
    private final double saldo;
    public Cuenta(String idCliente, String idCuenta, double saldo) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.saldo = saldo;
    }
    public String getIdCliente() {
        return idCliente;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public double getSaldo() {
        return saldo;
    }
}
