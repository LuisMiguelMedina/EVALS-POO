package src.main.Model;

import src.main.DAO.Conexion;
import src.main.Service.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cuenta {
    private final String idCliente;
    private final String idCuenta;
    private final String clabe;
    private final double saldo;
    public Cuenta(String idCliente, String idCuenta, double saldo, String clabe) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.clabe = clabe;
    }
    public String getIdCliente() {
        return idCliente;
    }
    public String getIdCuenta() {
        return idCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getClabe() {return clabe;}
    public List<String> estadoDeCuenta() throws IOException {
        Query query = new Query(new Conexion());
        List<String[]> registrosTransferencias = query.obtenerRegistrosTransferencias();
        List<String> estadoDeCuenta = new ArrayList<>();
        for (String[] registro : registrosTransferencias) {
            if (registro[0].equals(getClabe())) {
                String historial = Arrays.toString(registro);
                estadoDeCuenta.add(historial);
            }
        }
        return estadoDeCuenta;
    }
}