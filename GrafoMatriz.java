import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoMatriz implements Grafo{
        private static final int infinito = 999999999;

        private int[][] matriz;
        private Map<String, Integer> nombreAIndice;
        private List<String> indiceANombre;
        private int numNodos;

        public GrafoMatriz(int maxNodos) {
            matriz = new int[maxNodos][maxNodos];
            nombreAIndice = new HashMap<>();
            indiceANombre = new ArrayList<>();
            numNodos = 0;

            for (int i = 0; i < maxNodos; i++) {
                for (int j = 0; j < maxNodos; j++) {
                    if (i == j) {
                        matriz[i][j] = 0;
                    } else {
                        matriz[i][j] = infinito;
                    }
                }
            }
        }

        @Override
        public void agregarNodo(String ciudad) {
            if (!nombreAIndice.containsKey(ciudad)) {
                nombreAIndice.put(ciudad, numNodos);
                indiceANombre.add(ciudad);
                numNodos++;
            }
        }

        @Override
        public void agregarArco(String origen, String destino, int kilometros) {
            Integer i = nombreAIndice.get(origen);
            Integer j = nombreAIndice.get(destino);
            if (i != null && j != null) {
                matriz[i][j] = kilometros;
            }
        }

        @Override
        public void eliminarArco(String origen, String destino) {
            Integer i = nombreAIndice.get(origen);
            Integer j = nombreAIndice.get(destino);
            if (i != null && j != null) {
                matriz[i][j] = infinito;
            }
        }

        @Override
        public boolean existeArco(String origen, String destino) {
            Integer i = nombreAIndice.get(origen);
            Integer j = nombreAIndice.get(destino);
            return (i != null && j != null && matriz[i][j] != infinito && matriz[i][j] != 0);
        }

        @Override
        public int obtenerDistancia(String origen, String destino) {
            Integer i = nombreAIndice.get(origen);
            Integer j = nombreAIndice.get(destino);
            if (i != null && j != null) {
                return matriz[i][j];
            }
            return infinito;
        }

        @Override
        public Set<String> obtenerNodos() {
            return new LinkedHashSet<>(indiceANombre);
        }

        public int[][] getMatriz() {
            return matriz;
        }

        public List<String> geNodosList() {
            return indiceANombre;
        }

        public int getNumNodos() {
            return numNodos;
        }

        public static int getInfinito() {
            return infinito;
        }


}
