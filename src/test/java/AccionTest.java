import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import datos.Accion;
import datos.Alumno;

public class AccionTest {

    private Accion accion;

    @BeforeEach
    void setUp() {
        accion = new Accion("TEST101", "Prueba Accion", new Alumno("juan123", "juan@urv.cat", "GESST", "01/09/2022")) {};
    }

    @Test
    void testGetters() {
        assertEquals("TEST101", accion.getCodigo(), "El código debería coincidir.");
        assertEquals("Prueba Accion", accion.getTitulo(), "El título debería coincidir.");
        assertNotNull(accion.getResponsable(), "El responsable debería estar definido.");
    }

    @Test
    void testSetters() {
        accion.setCodigo("NEW102");
        accion.setTitulo("Nueva Prueba");
        assertEquals("NEW102", accion.getCodigo(), "El código debería actualizarse.");
        assertEquals("Nueva Prueba", accion.getTitulo(), "El título debería actualizarse.");
    }
}
