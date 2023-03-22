import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Cuenta {
    private final String idCliente;
    private final String idCuenta;
    private final double saldo;
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
    public static List<String> cuentasCliente(String idClient) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        List<String> registroCuentas = new ArrayList<String>();
        List<String> cuentasCliente = new ArrayList<String>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 0);
            registroCuentas.add(valorCelda);
            boolean existeCuenta = validarCuenta(idClient, registroCuentas);
            if (existeCuenta){
                String valorCuenta = crudRegistros.obtenerCelda(i, 1);
                cuentasCliente.add(valorCuenta);
            }
            i++;
        }
        return cuentasCliente;
    }
    @Override
    public String toString() {
        return "idCliente=" + idCliente + ", idCuenta=" + idCuenta + ", saldo=" + saldo;
    }
}
