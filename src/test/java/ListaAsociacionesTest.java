import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datos.Asociacion;
import excepciones.ExcepcionListaAsociacionLlena;
import excepciones.ExcepcionIndiceFueraDeRango;
import listas.ListaAsociaciones;

public class ListaAsociacionesTest {

    private ListaAsociaciones listaAsociaciones;

    @BeforeEach
    void setUp() {
        listaAsociaciones = new ListaAsociaciones(100);
    }

    @Test
    void testAgregarAsociacionExito() throws ExcepcionListaAsociacionLlena {
        Asociacion asociacion = new Asociacion("Asociacion1", "correo1@example.com", new String[]{"GEB"});
        assertTrue(listaAsociaciones.agregarAsociacion(asociacion));
        assertEquals(1, listaAsociaciones.getNElem());
    }

    @Test
    void testAgregarAsociacionListaLlena() {
        try {
            for (int i = 0; i < 100; i++) {
                listaAsociaciones.agregarAsociacion(new Asociacion("Asociacion" + i, "correo" + i + "@example.com", new String[]{"GEB"}));
            }
        } catch (ExcepcionListaAsociacionLlena e) {
            fail("No debería lanzar una excepción antes de que la lista esté llena.");
        }

        assertThrows(ExcepcionListaAsociacionLlena.class, () -> {
            listaAsociaciones.agregarAsociacion(new Asociacion("AsociacionExtra", "correoExtra@example.com", new String[]{"GEB"}));
        });
    }

    @Test
    void testObtenerAsociacionExito() throws ExcepcionListaAsociacionLlena, ExcepcionIndiceFueraDeRango {
        Asociacion asociacion = new Asociacion("Asociacion1", "correo1@example.com", new String[]{"GEB"});
        listaAsociaciones.agregarAsociacion(asociacion);
        assertEquals(asociacion, listaAsociaciones.obtenerAsociacion(0));
    }

    @Test
    void testObtenerAsociacionIndiceInvalido() throws ExcepcionListaAsociacionLlena {
        listaAsociaciones.agregarAsociacion(new Asociacion("Asociacion1", "correo1@example.com", new String[]{"GEB"}));
        assertThrows(ExcepcionIndiceFueraDeRango.class, () -> {
            listaAsociaciones.obtenerAsociacion(1);
        });
    }

    @Test
    void testBuscarAsociacionExistente() throws ExcepcionListaAsociacionLlena {
        Asociacion asociacion = new Asociacion("Asociacion1", "correo1@example.com", new String[]{"GEB"});
        listaAsociaciones.agregarAsociacion(asociacion);
        assertEquals(asociacion, listaAsociaciones.buscarAsociacion("Asociacion1"));
    }

    @Test
    void testBuscarAsociacionNoExistente() {
        assertNull(listaAsociaciones.buscarAsociacion("AsociacionInexistente"));
    }
}