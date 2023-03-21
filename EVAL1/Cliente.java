import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class Cliente {
    private String idCliente;
    private String nombre;
    private String telefono;
    private String correo;

    public Cliente(String idCliente, String nombre, String telefono, String correo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }
    public static List<String> listaIdClientes() throws IOException {
        CRUDRegistros crudRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = crudRegistros.leerRegistros();
        List<String> registrosClientes = new ArrayList<String>();
        int i = 0;
        for (String[] registro : registros) {
            String valorCelda = crudRegistros.obtenerCelda(i, 0);
            registrosClientes.add(valorCelda);
            i++;
        }
        return registrosClientes;
    }
    @Override
    public String toString() {
        return "idCliente=" + idCliente + ", nombre='" + nombre + '\'' + ", telefono='" + telefono + '\'' + ", correo='" + correo + '\'';
    }
    public static void main(String[] args) throws IOException {
        CRUDRegistros escribeRegistros = new CRUDRegistros("EVAL1/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = escribeRegistros.leerRegistros();
        for (String[] registro : registros) {
            String idCliente = registro[0];
            String nombre = registro[1];
            String telefono = registro[2];
            String correo = registro[3];
            Cliente cliente = new Cliente(idCliente, nombre, telefono, correo);
            System.out.println(cliente);
            Cuenta.listaIdCuentas(idCliente);
        }
    }
}

