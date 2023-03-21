import java.io.IOException;
import java.util.List;
public class Deposito extends Transaccion {
    public String idCuentaDestino;
    public Deposito(double monto,String idCuentaDestino) {
        super(monto);
        this.idCuentaDestino = idCuentaDestino;
    }
    public static void depositarMonto(String idCuenta, double monto) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = crudRegistros.leerRegistros();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 2);
            double saldo = Double.parseDouble(valorCelda);
            if (monto < saldo) {
                double fondoTotal = saldo-monto;
                String fondoTotalString = Double.toString(fondoTotal);
                crudRegistros.actualizarCelda(i, 2, fondoTotalString);
                String timestamp = Transaccion.Fecha.getCurrentTimestamp();
                System.out.println("Se retiro "+ monto + "$ el " + timestamp + " de la cuenta " + idCuenta);
                CRUDRegistros crudRegistrosEscritura = new CRUDRegistros("EVAL1/CSVArchivos/DatosTransferencias.csv");
                String[] nuevoRegistro = {idCuenta,"-"+monto,timestamp};
                crudRegistrosEscritura.escribirRegistro(nuevoRegistro);
            } else {
                System.out.println("Fondos insuficientes");
            }
            i++;
        }
    }
}
