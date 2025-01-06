import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import datos.Charla;
import datos.Accion;
import datos.Alumno;
import listas.ListaAcciones;
import datos.Fecha;

public class ListaAccionesTest {

    private ListaAcciones listaAcciones;
    private Charla charla;

    @BeforeEach
    void setUp() {
        listaAcciones = new ListaAcciones();
        charla = new Charla("TECH100", "Introducción a Java", new Alumno("pepe123", "pepe@urv.cat", "GEI", "01/01/2020"), Fecha.parse("10/01/2025"));
    }

    @Test
    void testAgregarAccion() {
        boolean resultado = listaAcciones.agregarAccion(charla);
        assertTrue(resultado, "La charla debería añadirse correctamente.");
        assertEquals(1, listaAcciones.getNElem(), "Debería haber 1 elemento en la lista.");
    }

    @Test
    void testAgregarAccionMaximo() {
        for (int i = 0; i < 100; i++) {
            listaAcciones.agregarAccion(new Charla("TECH" + i, "Charla " + i, null, Fecha.parse("01/01/2025")));
        }
        boolean resultado = listaAcciones.agregarAccion(new Charla("TECH200", "Charla extra", null, Fecha.parse("01/01/2025")));
        assertFalse(resultado, "No debería añadirse una acción cuando se excede el límite de 100.");
    }

    @Test
    void testBuscarAccionPorCodigo() {
        listaAcciones.agregarAccion(charla);
        Accion accionEncontrada = listaAcciones.buscarAccionPorCodigo("TECH100");
        assertNotNull(accionEncontrada, "La charla debería encontrarse por su código.");
        assertEquals("Introducción a Java", accionEncontrada.getTitulo(), "El título debería coincidir.");
    }

    @Test
    void testBuscarAccionInexistente() {
        Accion accionEncontrada = listaAcciones.buscarAccionPorCodigo("NO_EXISTE");
        assertNull(accionEncontrada, "No debería encontrarse ninguna acción con un código inexistente.");
    }

    @Test
    void testEliminarAccion() {
        listaAcciones.agregarAccion(charla);
        listaAcciones.eliminarAccion(0);
        assertEquals(0, listaAcciones.getNElem(), "Debería eliminarse la acción correctamente.");
    }

    @Test
    void testMostrarCharlasConMasAsistentes() {
        charla.incrementarAsistentes();
        charla.incrementarAsistentes();
        listaAcciones.agregarAccion(charla);

        listaAcciones.mostrarCharlasConMasAsistentes(1);
        assertEquals(1, listaAcciones.getNElem(), "La charla con asistentes debería mostrarse.");
    }
}
