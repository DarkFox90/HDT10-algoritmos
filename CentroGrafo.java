import java.util.List;

public class CentroGrafo {
    public String encontrarCentro(AlgoritmoFloyd floyd, GrafoMatriz grafo) {
        int[][] distancias = floyd.getDistancias();
        int numNodos = grafo.getNumNodos();
        List<String> ciudades = grafo.getNodosList();
        int infinito = GrafoMatriz.getInfinito();

        int minExcentricidad = Integer.MAX_VALUE;
        int indiceCentro = -1;

        for (int i = 0; i < numNodos; i++) {
            int excentricidadActual = 0;

            for(int j = 0; j < numNodos; j++) {
                if (i != j && distancias[i][j] != infinito) {
                    if (distancias[i][j] > excentricidadActual) {
                        excentricidadActual = distancias[i][j];
                    }
                }
            }

            if (excentricidadActual > 0 && excentricidadActual < minExcentricidad) {
                minExcentricidad = excentricidadActual;
                indiceCentro = i;
            }
        }

        if (indiceCentro != -1) {
            return ciudades.get(indiceCentro);
        } else {
            return "no se pudo encontrar el centro";
        }
    }
}
