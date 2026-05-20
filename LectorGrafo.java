import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LectorGrafo {
    public GrafoMatriz cargarGrafo(String rutaArchivo) {
        Set<String> ciudades = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes.length == 3) { 
                    ciudades.add(partes[0]);
                    ciudades.add(partes[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        GrafoMatriz grafo = new GrafoMatriz(ciudades.size());

        for (String ciudad : ciudades) {
            grafo.agregarNodo(ciudad);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes.length == 3) {
                    String origen = partes[0];
                    String destino = partes[1];
                    int distancia = Integer.parseInt(partes[2]);
                    
                    grafo.agregarArco(origen, destino, distancia);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error de formato en los KM del archivo: " + e.getMessage());
        }
        return grafo;
    }
}
