import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Deposito.depositarMonto("1800341001","1800341002",100);
    }
    public static void CRUDRegistroClientes() throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        for (String[] registro : registros) {
            for (String valor : registro) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
        // Escribir una nueva fila al final del archivo CSV
        String[] nuevoRegistro = {"18003410", "Luis Medina", "9959595","eso@gmail.com"};
        crudRegistros.escribirRegistro(nuevoRegistro);

        // Actualizar una fila en el archivo CSV
        String[] registroActualizado = {"1855504", "Luis Medina", "9959598","eso@gmail.com"};
        crudRegistros.actualizarRegistro(1, registroActualizado);

        // Eliminar una fila del archivo CSV
        crudRegistros.eliminarRegistro(0);

        // Actualizar el valor de una celda en el archivo CSV
        crudRegistros.actualizarCelda(1, 1, "López");

    }
    public static void consultarCuentasCliente(String idClient) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        List<String> registroCuentas = new ArrayList<String>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 0);
            registroCuentas.add(valorCelda);
            boolean existeCuenta = Cuenta.validarCuenta(idClient, registroCuentas);
            if (existeCuenta){
                String valorCuenta = crudRegistros.obtenerCelda(i, 1);
                System.out.println("Cuenta "+ valorCuenta + "");
            }
            i++;
        }
    }
    public static void consultarCliente(String idCliente) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        List<String> registrosClientes = new ArrayList<String>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 0);
            registrosClientes.add(valorCelda);
            if (Cliente.validarCliente(idCliente, registrosClientes)){
                String[] fila = registros.get(i);
                for (String dato : fila) {
                    System.out.print(dato + " ");
                }
                break;
            }
            i++;
        }
    }
    public static void consultarCuenta(String idCuenta) throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");
        List<String[]> registros = CRUDRegistros.leerRegistros();
        List<String> consultaCuenta = new ArrayList<String>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 1);
            consultaCuenta.add(valorCelda);
            if (Cuenta.validarCuenta(idCuenta, consultaCuenta)){
                String[] fila = registros.get(i);
                for (String dato : fila) {
                    System.out.print(dato + " ");
                }
                break;
            } else {
                System.out.println("La cuenta no existe");
            }
            i++;
        }
    }
}
