import java.io.IOException;
import java.util.List;

public class Retiro extends Transaccion{
    public Retiro(double monto) {
        super(monto);
    }
    public static void retirarMonto(String idCuenta, double monto) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 2);
            double saldo = Double.parseDouble(valorCelda);
            if (monto < saldo) {
                double fondoTotal = saldo-monto;
                String fondoTotalString = Double.toString(fondoTotal);
                crudRegistros.actualizarCelda(i, 2, fondoTotalString);
                registrarRetiro(idCuenta,monto);
                break;
            } else {
                System.out.println("Fondos insuficientes");
            }
            i++;
        }
    }
    public static void registrarRetiro(String idCuenta, double monto) throws IOException {
        System.out.println("Se retiro "+ monto + "$ el " + Transaccion.Fecha.getCurrentTimestamp() + " de la cuenta " + idCuenta);
        CRUDRegistros crudRegistrosEscritura = new CRUDRegistros("EVAL1/CSVArchivos/DatosTransferencias.csv");
        String[] nuevoRegistro = {idCuenta,"-"+monto,Transaccion.Fecha.getCurrentTimestamp()};
        crudRegistrosEscritura.escribirRegistro(nuevoRegistro);
    }
}

