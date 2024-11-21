package datos;

import excepciones.EjemploDivisionPorZero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EjemploTest {

    Ejemplo ejemplo;

    @BeforeEach
    void setUp() {
        ejemplo = new Ejemplo();
    }

    @Test
    public void testSumar() {
        assertEquals(5, ejemplo.sumar(3, 2));
        assertEquals(0, ejemplo.sumar(-2, 2));
    }

    @Test
    public void testRestar() {
        assertEquals(-1, ejemplo.restar(2, 3));
        assertEquals(-4, ejemplo.restar(-2, 2));
    }

    @Test
    public void testDividir() {
        assertEquals(2.0, ejemplo.dividir(6, 3));
        assertEquals(-2.5, ejemplo.dividir(-5, 2));

        assertThrows(EjemploDivisionPorZero.class, () -> ejemplo.dividir(5, 0));
    }

}
