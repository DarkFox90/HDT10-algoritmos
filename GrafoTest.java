import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class GrafoTest {

    private GrafoMatriz grafo;

    @Before
    public void setUp() {
        grafo = new GrafoMatriz(5);
    }

    @Test
    public void testAgregarNodoYArco() {
        grafo.agregarNodo("Guatemala");
        grafo.agregarNodo("Antigua");
        grafo.agregarArco("Guatemala", "Antigua", 40);
        
        assertTrue(grafo.existeArco("Guatemala", "Antigua"));
        assertEquals(40, grafo.obtenerDistancia("Guatemala", "Antigua"));
    }

    @Test
    public void testEliminarArco() {
        grafo.agregarNodo("Guatemala");
        grafo.agregarNodo("Antigua");
        grafo.agregarArco("Guatemala", "Antigua", 40);
        grafo.eliminarArco("Guatemala", "Antigua");
        
        assertFalse(grafo.existeArco("Guatemala", "Antigua"));
        assertEquals(GrafoMatriz.getInfinito(), grafo.obtenerDistancia("Guatemala", "Antigua"));
    }

    @Test
    public void testAlgoritmoFloyd() {
        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarArco("A", "B", 10);
        grafo.agregarArco("B", "C", 15);

        AlgoritmoFloyd floyd = new AlgoritmoFloyd(grafo);
        
        int distanciaTotal = floyd.getDistancias()[0][2];
        assertEquals(25, distanciaTotal);
        
        List<String> ruta = floyd.obtenerRuta(0, 2);
        assertEquals(3, ruta.size());
        assertEquals("A", ruta.get(0));
        assertEquals("B", ruta.get(1));
        assertEquals("C", ruta.get(2));
    }
}