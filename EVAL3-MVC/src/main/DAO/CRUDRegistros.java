package src.main.DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CRUDRegistros {
    private final String archivo;
    private final List<String[]> registros;
    public CRUDRegistros(String archivo) throws IOException {
        this.archivo = archivo;
        this.registros = leerRegistros();
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
        registros.add(registro);
    }
    public void eliminarRegistro(int numeroFila) throws IOException {
        registros.remove(numeroFila);
        escribirRegistros();
    }
    private void escribirRegistros() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        for (String[] registro : registros) {
            bw.write(String.join(",", registro) + System.lineSeparator());
        }
        bw.close();
    }
    public void actualizarCelda(int numeroFila, int numeroColumna, String valor) throws IOException {
        registros.get(numeroFila)[numeroColumna] = valor;
        escribirRegistros(); // Se llama al método que escribe toda la lista en el archivo
    }
}