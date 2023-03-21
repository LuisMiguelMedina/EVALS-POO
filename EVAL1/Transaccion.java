import java.util.List;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
public class Transaccion {
    protected double monto;
    protected Fecha fecha;
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public Transaccion(Fecha fecha, double monto) {
        this.fecha = fecha;
        this.monto = monto;
    }
    public class Fecha {
        public static String getTimestamp() {
            Instant instant = Instant.now();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
            return formatter.format(instant);
        }
    }
    public static void main(String[] args) {
        System.out.println(Fecha.getTimestamp());
    }
}
