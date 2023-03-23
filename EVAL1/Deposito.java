import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Deposito extends Transaccion {
    public String idCuentaDestino;
    public Deposito(double monto,String idCuentaDestino) {
        super(monto);
        this.idCuentaDestino = idCuentaDestino;
    }
    public static void depositarMonto(String idCuenta,String idCuentaDestino, double monto) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        List<String> registroDeposito = new ArrayList<>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 1);
            registroDeposito.add(valorCelda);
            boolean existeCuentaDestino = Cuenta.validarCuenta(idCuentaDestino, registroDeposito);
            boolean existeCuentaLocal = Cuenta.validarCuenta(idCuenta, registroDeposito);
            String valorSaldo = crudRegistros.obtenerCelda(i, 2);
            double saldo = Double.parseDouble(valorSaldo);
            if (existeCuentaDestino){
                if (existeCuentaLocal){
                registrarDeposito(idCuenta,saldo,monto, idCuentaDestino, i);
                break;
                }
            }
            i++;
        }
    }
    public static void registrarDeposito(String idCuenta,double saldo, double monto, String idCuentaDestino, int i) throws IOException {
        CRUDRegistros crudRegistrosEscritura = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        CRUDRegistros crudRegistrosLectura = new CRUDRegistros("EVAL1/CSVArchivos/DatosTransferencias.csv");

        if (monto < saldo) {
            //Crear
            double fondoTotal = saldo - monto;
            String montoString = Double.toString(monto);
            String[] depositoRegistro = {idCuentaDestino, "+" + montoString, Transaccion.Fecha.getCurrentTimestamp()};
            System.out.println("La cuenta " + idCuentaDestino + " Recibio +" + monto + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
            crudRegistrosLectura.escribirRegistro(depositoRegistro);

            //Actualizar DatosCuentas para deposito
            String fondoTotalString = Double.toString(fondoTotal);
            crudRegistrosEscritura.actualizarCelda(i, 2, fondoTotalString);
            String[] retiroRegistro = {idCuenta, "-" + fondoTotalString, Transaccion.Fecha.getCurrentTimestamp()};
            System.out.println("La cuenta " + idCuenta + " retiro -" + montoString + "$ el " + Transaccion.Fecha.getCurrentTimestamp());
            crudRegistrosLectura.escribirRegistro(retiroRegistro);
        } else {
            System.out.println("Fondos insuficientes");
        }
    }
}
