import java.io.*;
import java.util.*;

public class LectorArchivos {
    private String rutaArchivo;

    public LectorArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public List<String[]> leerArchivoCSV() {
        List<String[]> registros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] registro = linea.split(",");
                registros.add(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registros;
    }

    public static void main(String[] args) {
        LectorArchivos lector = new LectorArchivos("src/CSVArchivos/DatosClientes.csv");
        List<String[]> registros = lector.leerArchivoCSV();
        for (String[] registro : registros) {
            for (String campo : registro) {
                System.out.print(campo + " ");
            }
            System.out.println();
        }
    }
}


