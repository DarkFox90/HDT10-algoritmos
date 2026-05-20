import java.util.Set;

public interface Grafo {
    void agregarNodo(String ciudad);
    void agregarArco(String origen, String destino, int kilometros);
    void eliminarArco(String origen, String destino);
    boolean existeArco(String origen, String destino);
    int obtenerDistancia(String origen, String destino);
    Set<String> obtenerNodos();
}
    
   

