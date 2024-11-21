package listas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import datos.Ejemplo;

public class ListaEjemplosTest {

    @Test
    public void testAñadirCalculadora() {
        ListaEjemplos lista = new ListaEjemplos(2);
        Ejemplo ej1 = new Ejemplo();
        Ejemplo ej2 = new Ejemplo();

        lista.añadirEjemplo(ej1);
        lista.añadirEjemplo(ej2);

        assertEquals(2, lista.obtenerNumElementos());
        assertEquals(ej1, lista.obtenerEjemplo(0));
        assertEquals(ej2, lista.obtenerEjemplo(1));

        assertThrows(IndexOutOfBoundsException.class, () -> lista.obtenerEjemplo(5));
    }

}
