import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datos.Alumno;
import datos.Miembro;
import datos.Profesor;
import listas.ListaMiembros;

public class ListaMiembrosTest {

    private ListaMiembros listaMiembros;

    @BeforeEach
    void setUp() {
        listaMiembros = new ListaMiembros();
    }

    @Test
    void testAgregarMiembroExito() {
        Miembro alumno = new Alumno("alias1", "correo1@example.com", "2025-01-01", "GEB");
        assertTrue(listaMiembros.agregarMiembro(alumno));
        assertEquals(1, listaMiembros.getNElem());
    }

    @Test
    void testAgregarMiembroListaLlena() {
        for (int i = 0; i < 100; i++) {
            Miembro miembro = new Alumno("alias" + i, "correo" + i + "@example.com", "2025-01-01", "GEB");
            assertTrue(listaMiembros.agregarMiembro(miembro));
        }

        Miembro extra = new Alumno("aliasExtra", "correoExtra@example.com", "2025-01-01", "GEB");
        assertFalse(listaMiembros.agregarMiembro(extra));
    }

    @Test
    void testObtenerMiembroExito() {
        Miembro profesor = new Profesor("aliasProfesor", "correoProfesor@example.com", "2025-01-01", "DEIM", 101);
        listaMiembros.agregarMiembro(profesor);
        assertEquals(profesor, listaMiembros.obtenerMiembro(0));
    }

    @Test
    void testObtenerMiembroIndiceInvalido() {
        assertNull(listaMiembros.obtenerMiembro(1));
    }

    @Test
    void testBuscarMiembroExistente() {
        Miembro alumno = new Alumno("aliasBuscado", "correoBuscado@example.com", "2025-01-01", "GEB");
        listaMiembros.agregarMiembro(alumno);
        assertEquals(alumno, listaMiembros.buscarMiembro("aliasBuscado"));
    }

    @Test
    void testBuscarMiembroNoExistente() {
        assertNull(listaMiembros.buscarMiembro("aliasInexistente"));
    }
}