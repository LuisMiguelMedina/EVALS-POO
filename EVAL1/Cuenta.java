import java.io.IOException;
import java.util.ArrayList;
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
    public static boolean validarCuenta(String idCliente, List<String> registroClientes) {
        for (String cuenta : registroClientes) {
            if (cuenta.equals(idCliente)) {
                return true;
            }
        }
        return false;
    }
    public static void listaIdCuentas(String idClient) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = crudRegistros.leerRegistros();
        List<String> registroCuentas = new ArrayList<String>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 0);
            registroCuentas.add(valorCelda);
            boolean existeCuenta = validarCuenta(idClient, registroCuentas);
            if (existeCuenta){
                String valorCuenta = crudRegistros.obtenerCelda(i, 1);
                System.out.println("Cuenta "+ valorCuenta + "");
            }
            i++;
        }
    }
    @Override
    public String toString() {
        return "idCliente=" + idCliente + ", idCuenta=" + idCuenta + ", saldo=" + saldo;
    }

    public static void main(String[] args) throws IOException {
        CRUDRegistros escribeRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = escribeRegistros.leerRegistros();
        for (String[] registro : registros) {
            String idCliente = registro[0];
            String idCuenta = registro[1];
            String saldoString = registro[2];
            double saldo = Double.parseDouble(saldoString);
            Cuenta cuenta = new Cuenta(idCliente, idCuenta, saldo);
            System.out.println(cuenta);
            List<String> valoresClientes = Cliente.listaIdClientes();
            boolean existeCuenta = validarCuenta(idCliente, valoresClientes);
            if (!existeCuenta) {
                System.out.println("La cuenta " + idCuenta + " no existe");
            }
            Transaccion transaccion = new Transaccion(100.0);
            Retiro.retirarMonto(idCuenta, transaccion.monto);
        }
        List<String[]> consultaRegistros = escribeRegistros.leerRegistros();
        for (String[] registroTransaccion : consultaRegistros) {
            String idCliente = registroTransaccion[0];
            String idCuenta = registroTransaccion[1];
            String saldoString = registroTransaccion[2];
            double saldo = Double.parseDouble(saldoString);
            Cuenta cuenta = new Cuenta(idCliente, idCuenta, saldo);
            System.out.println(cuenta);

        }

    }
}
