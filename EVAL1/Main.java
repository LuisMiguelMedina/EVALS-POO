import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        CRUDRegistroClientes();
        CRUDRegistroCuentas();
    }
    public static void CRUDRegistroClientes() throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = crudRegistros.leerRegistros();
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

    public static void CRUDRegistroCuentas() throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosCuentas.csv");



    }
}
