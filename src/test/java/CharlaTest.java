import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datos.Charla;
import excepciones.ExcepcionMaximoValoraciones;

public class CharlaTest {

    private Charla charla;

    @BeforeEach
    void setUp() {
        charla = new Charla("test", "Test", null, null);
    }

    @Test
    void testAñadirValoracionCuandoNoHayEspacio() {
        for (int i = 0; i < 50; i++) {
            try {
                charla.agregarValoracion(5);
            } catch (ExcepcionMaximoValoraciones e) {
            }
        }

        assertThrows(ExcepcionMaximoValoraciones.class, () -> charla.agregarValoracion(5));
    }

}
