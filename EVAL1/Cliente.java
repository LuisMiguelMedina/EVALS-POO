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
    public boolean validarIdCliente(Cuenta cuenta) {
        return this.idCliente.equals(cuenta.getIdCliente());
    }
    @Override
    public String toString() {
        return "idCliente=" + idCliente + ", nombre='" + nombre + '\'' + ", telefono='" + telefono + '\'' + ", correo='" + correo + '\'';
    }
    public static void main(String[] args) {
        LectorArchivos lector = new LectorArchivos("src/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = lector.leerArchivoCSV();

        for (String[] registro : registros) {
            String idCliente = registro[0];
            String nombre = registro[1];
            String telefono = registro[2];
            String correo = registro[3];

            Cliente cliente = new Cliente(idCliente, nombre, telefono, correo);
            System.out.println(cliente);
        }
    }
}

