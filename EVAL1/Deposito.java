import java.util.List;
public class Deposito extends Transaccion {
    private String idCuentaDestino;
    public Deposito(Fecha fecha, double monto, String idCuentaDestino) {
        super(fecha, monto);
        this.idCuentaDestino = idCuentaDestino;
    }
    public void depositarMonto(){
        LectorArchivos lector = new LectorArchivos("src/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = lector.leerArchivoCSV();
        for (String[] registro : registros) {
            String idCuenta = registro[1];
            String saldo = registro[2];

            if (idCuenta == idCuentaDestino) {

            }
        }
    }
    public static void main(String[] args) {

    }
}
