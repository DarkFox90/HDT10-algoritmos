import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LectorGrafo lector = new LectorGrafo();

        GrafoMatriz grafo = lector.cargarGrafo("guategrafo.txt");

        if (grafo == null || grafo.getNumNodos() == 0) {
            System.out.println("Error al cargar grafo");
            return;
        }
        
        mostrarMatriz(grafo);

        AlgoritmoFloyd floyd = new AlgoritmoFloyd(grafo);
        CentroGrafo centro = new CentroGrafo();

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n1. Buscar ruta más corta entre ciudades");
            System.out.println("2. Indicar la ciudad centro del grafo");
            System.out.println("3. Modificar el grafo (Desastres/Cordones sanitarios)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingresa la ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingresa la ciudad destino: ");
                    String destino = scanner.nextLine();

                    List<String> ciudades = grafo.getNodosList();
                    int idxOrigen = ciudades.indexOf(origen);
                    int idxDestino = ciudades.indexOf(destino);

                    if (idxOrigen == -1 || idxDestino == -1) {
                        System.out.println("Una o ambas ciudades no existen.");
                    } else {
                        int distancia = floyd.getDistancias()[idxOrigen][idxDestino];
                        if (distancia == GrafoMatriz.getInfinito()) {
                            System.out.println("No existe una ruta entre " + origen + " y " + destino);
                        } else {
                            System.out.println("\nDistancia total: " + distancia + " km");
                            List<String> ruta = floyd.obtenerRuta(idxOrigen, idxDestino);
                            System.out.println("Ruta sugerida: " + String.join(" - ", ruta));
                        }
                    }
                    break;

                case "2":
                    String ciudadCentro = centro.encontrarCentro(floyd, grafo);
                    System.out.println("\nLa ciudad mas centrica es: " + ciudadCentro);
                    break;

                case "3":
                    System.out.println("a) Reportar interrupcion de trafico (eliminar ruta)");
                    System.out.println("b) Establecer nueva conexion");
                    System.out.print("Escoja una opcion: ");
                    String subOpcion = scanner.nextLine();

                    if (subOpcion.equalsIgnoreCase("a")) {
                        System.out.print("Ciudad origen de interrupcion: ");
                        String o = scanner.nextLine();
                        System.out.print("Ciudad destino de interrupcion: ");
                        String d = scanner.nextLine();
                        grafo.eliminarArco(o, d);
                        System.out.println("Ruta eliminada.");
                    } else if (subOpcion.equalsIgnoreCase("b")) {
                        System.out.print("Ciudad origen de conexión: ");
                        String o = scanner.nextLine();
                        System.out.print("Ciudad destino de conexión: ");
                        String d = scanner.nextLine();
                        System.out.print("Distancia en KM: ");
                        try {
                            int km = Integer.parseInt(scanner.nextLine());
                            grafo.agregarNodo(o);
                            grafo.agregarNodo(d);
                            grafo.agregarArco(o, d, km);
                            System.out.println("Nueva ruta establecida.");
                        } catch (NumberFormatException e) {
                            System.out.println("La distancia debe ser un número entero.");
                        }
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    floyd = new AlgoritmoFloyd(grafo);
                    break;

                case "4":
                    continuar = false;
                    System.out.println("Gracias por usar el programa");
                    break;

                default:
                    System.out.println("Opcion invalida, seleccione otra vez");
            }
        }
        scanner.close();
    }

    private static void mostrarMatriz(GrafoMatriz grafo) {
        System.out.println("\nMatriz de Adyacencia");
        int[][] matriz = grafo.getMatriz();
        List<String> nodos = grafo.getNodosList();
        int inf = GrafoMatriz.getInfinito();
        
        System.out.print(String.format("%-15s", ""));
        for (String nodo : nodos) {
            System.out.print(String.format("%-15s", nodo));
        }
        System.out.println();

        for (int i = 0; i < nodos.size(); i++) {
            System.out.print(String.format("%-15s", nodos.get(i)));
            for (int j = 0; j < nodos.size(); j++) {
                if (matriz[i][j] == inf) {
                    System.out.print(String.format("%-15s", "INF"));
                } else {
                    System.out.print(String.format("%-15s", matriz[i][j]));
                }
            }
            System.out.println();
        }
    }
}