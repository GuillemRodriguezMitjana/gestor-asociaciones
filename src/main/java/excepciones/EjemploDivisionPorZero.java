package excepciones;

public class EjemploDivisionPorZero extends RuntimeException {
    public EjemploDivisionPorZero() {
        super("No se puede dividir por zero");
    }
}
