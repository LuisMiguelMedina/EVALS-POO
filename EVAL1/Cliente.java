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
    public static boolean validarCliente(String idCliente, List<String> registroClientes) {
        for (String cuenta : registroClientes) {
            if (cuenta.equals(idCliente)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "idCliente=" + idCliente + ", nombre='" + nombre + '\'' + ", telefono='" + telefono + '\'' + ", correo='" + correo + '\'';
    }
}

