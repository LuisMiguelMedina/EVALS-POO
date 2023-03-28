package src.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CRUDRegistros {
    private String archivo;

    public CRUDRegistros(String archivo) {
        this.archivo = archivo;
    }

    public List<String[]> leerRegistros() throws IOException {
        List<String[]> registros = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] registro = linea.split(",");
            registros.add(registro);
        }
        br.close();
        return registros;
    }

    public void escribirRegistro(String[] registro) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
        bw.write(String.join(",", registro));
        bw.newLine();
        bw.close();
    }

    public void actualizarRegistro(int numeroFila, String[] registro) throws IOException {
        List<String[]> registros = leerRegistros();
        registros.set(numeroFila, registro);
        escribirRegistros(registros);
    }

    public void eliminarRegistro(int numeroFila) throws IOException {
        List<String[]> registros = leerRegistros();
        registros.remove(numeroFila);
        escribirRegistros(registros);
    }

    private void escribirRegistros(List<String[]> registros) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        for (String[] registro : registros) {
            bw.write(String.join(",", registro));
            bw.newLine();
        }
        bw.close();
    }

    public void actualizarCelda(int numeroFila, int numeroColumna, String valor) throws IOException {
        List<String[]> registros = leerRegistros();
        registros.get(numeroFila)[numeroColumna] = valor;
        escribirRegistros(registros);
    }
}
