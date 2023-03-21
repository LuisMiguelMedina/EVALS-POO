import java.util.List;
public class Cuenta {
    private String idCliente;
    private String idCuenta;
    private double saldo;
    public Cuenta(String idCliente, String idCuenta, double saldo) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.saldo = saldo;
    }
    public String getIdCliente() {
        return idCliente;
    }
    @Override
    public String toString() {
        return "idCliente=" + idCliente + ", idCuenta=" + idCuenta + ", saldo=" + saldo;
    }
    public static void main(String[] args) {
        LectorArchivos lector = new LectorArchivos("src/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = lector.leerArchivoCSV();
        for (String[] registro : registros) {
            for (String campo : registro) {
                System.out.print(campo + " ");
            }
            System.out.println();
        }
    }
}
