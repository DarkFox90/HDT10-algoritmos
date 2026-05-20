import java.util.ArrayList;
import java.util.List;

public class AlgoritmoFloyd {
    private int[][] distancias;
    private int[][] siguiente;
    private List<String> indiceANombre;
    private int numNodos;
    private int infinito;

    public AlgoritmoFloyd(GrafoMatriz grafo) {
        this.numNodos = grafo.getNumNodos();
        this.indiceANombre = grafo.getNodosList();
        this.infinito = GrafoMatriz.getInfinito();
        
        int[][] matrizOriginal = grafo.getMatriz();
        distancias = new int[numNodos][numNodos];
        siguiente = new int[numNodos][numNodos];

        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                distancias[i][j] = matrizOriginal[i][j];
                if (matrizOriginal[i][j] != infinito && i != j) {
                    siguiente[i][j] = j;
                } else {
                    siguiente[i][j] = -1; 
                }
            }
        }

        calcularRutas();
    }

    private void calcularRutas() {
        for (int k = 0; k < numNodos; k++) {
            for (int i = 0; i < numNodos; i++) {
                for (int j = 0; j < numNodos; j++) {
                    if (distancias[i][k] != infinito && distancias[k][j] != infinito) {
                        int nuevaDistancia = distancias[i][k] + distancias[k][j];
                
                        if (nuevaDistancia < distancias[i][j]) {
                            distancias[i][j] = nuevaDistancia;
                            siguiente[i][j] = siguiente[i][k];
                        }
                    }
                }
            }
        }
    }

    public List<String> obtenerRuta(int origenIdx, int destinoIdx) {
        List<String> ruta = new ArrayList<>();
        
        if (siguiente[origenIdx][destinoIdx] == -1) {
            return ruta;
        }
        
        ruta.add(indiceANombre.get(origenIdx));
        
        int actual = origenIdx;
        while (actual != destinoIdx) {
            actual = siguiente[actual][destinoIdx];
            ruta.add(indiceANombre.get(actual));
        }
        
        return ruta;
    }

    public int[][] getDistancias() { 
        return distancias; 
    }
}
