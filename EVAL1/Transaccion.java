import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Transaccion {
    protected double monto;
    protected static class Fecha {
        public static String getCurrentTimestamp() {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return now.format(formatter);
        }
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public Transaccion(double monto) {
        this.monto = monto;
    }
}

