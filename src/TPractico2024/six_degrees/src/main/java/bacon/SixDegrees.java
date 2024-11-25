package bacon;

import graph.GraphList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * La clase SixDegrees implenenta diferentes funcionalidades para resolver el juego de los seis grados de Bacon.
 */

public class SixDegrees {

    //Grafo que contiene a las peliculas y actores
    private GraphList grafo;

    /**
     * @post Construye una instancia de SixDegrees con la información contenida en la base de datos fileName.
     * @param fileName el nombre de un archivo que contine nombres de películas y sus respectivos actores y actrices.
     * @param delimiter el caracter especial que permite identificar la separación entre los elementos del archivo fileName.
     */
    public SixDegrees(String fileName, String delimiter) {
        grafo = new GraphList<>();
        lectorArchivo(fileName, delimiter);
    }

    /**
     * @post lee un archivo linea a linea, con la información dada se crea un grafo
     */
    private void lectorArchivo(String fileName, String delimiter)
    {
        try (BufferedReader ready = new BufferedReader(new FileReader(fileName))) {
            String line;
            //El ciclo se recorre para cada linea del archivo
            while ((line = ready.readLine()) != null) {
                //Arma un arreglo de Strings tomando como puntos de corte a delimiter
                String[] palabras = line.split(delimiter); 
                //Cada uno de los String del arreglo son agregados al nodo, si no existen
                agregarVertices(palabras);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error");
        }
    }

    /**
     * @post Dado un arreglo de palabras, si no existen, las agrega al grado. 
     * Genera también aristas entre la primera palabra y el resto de las palabras
     */
    private void agregarVertices(String[] palabras){
        //La primera palabra corresponde a la pelicula, si no existe se agrega al grafo
        String pelicula = palabras[0];
        if (!grafo.containsVertex(pelicula)){
            grafo.addVertex(pelicula);            
        }
        //El resto de las palabras son actores o actrices, si no existe se agregan
        for(int i=1; i < palabras.length; i++){
            if (!grafo.containsVertex(palabras[i])){
                grafo.addVertex(palabras[i]);
            }
            grafo.addEdge(pelicula, palabras[i]); //Se crea una arista con la pelicula
        }
    }

    /**
     * @pre act1 y act2 deben existir en el archivo pasado como parametro al constructor.
     * @post Retorna la película en la que trabajaron juntos act1 y act2, o null si no fueron co-protagonistas.
     * Si los actores fueron co-protagonistas en más de una pelicula, se devuelve la primera pelicula encontrada
     */
    public String coStars(String act1, String act2) {
        //Compruebo que ambos actores estén en el grafo
        if(!grafo.containsVertex(act1) || !grafo.containsVertex(act2))
            throw new IllegalArgumentException("Uno o ambos actores no existen en el grafo");
        
        //Creamos una lista para las peliculas en las que actuó act1
        List<String> peliculas_act1 = grafo.adj(act1);
        //Creamos una lista para las peliculas en las que actuó act2
        List<String> peliculas_act2 = grafo.adj(act2);
        
        //Buscamos coincidencias entre ambas listas
        for(String pelicula : peliculas_act1){
            if(peliculas_act2.contains(pelicula)){
                //Retorna la primera pelicula encontrada en que hay coincidencia
                return pelicula;
            }
        }

        //En caso de no haber encontrado coincidencias, es decir, act1 y act2 no fueron co-actores retorna null
        return null;
    }

    /**
     * @pre act1 y act2 deben existir en el archivo pasado como parametro al constructor.
     * @post Retorna la distancia de act1 a act2, donde distancia esta dada por:
     *       1, si trabajaron en la misma película
     *       (i+1), si act2 esta relacionado/a con algun actor o actriz que tiene distancia i con act1.
     *       En caso de no encontrar el camino retorna -1
     */
    public int degreesOfSeparation(String act1, String act2){
        if(!grafo.containsVertex(act1) || !grafo.containsVertex(act2))
            throw new IllegalArgumentException("Uno o ambos actores no existen en el grafo");

        //si es el mismo actor entonces es el mismo vertice, y la distancia es 0
        if(act1.equals(act2)){
            return 0;
        }

        //se utiliza bfs para encontrar el camino y devolver la distancia
        List<Integer> camino = grafo.bfs(act1, act2);

        //si no hay camino, retornamos -1
        if(camino == null){
            return -1;
        }

        //la distancia es el número de aristas
        return camino.size() / 2;
    }

    /**
     * @pre act1 y act2 deben existir en el archivo pasado como parametro al constructor.
     * @post Retorna la lista de actores y actrices involucrados en el camino para obtener la menor distancia de act1 a act2.
     */
    public List<String> pathOfSeparation(String act1, String act2) {
    //Si los actores son el mismo, la ruta debería ser una lista con un solo actor, ya que no es necesaria realizar ningún recorrido
        if(!grafo.containsVertex(act1) || !grafo.containsVertex(act2))
            throw new IllegalArgumentException("Uno o ambos actores no existen en el grafo");
        //Obtener el camino entre act1 y act2 con índices
        List<Integer> ruta = grafo.bfs(act1, act2);

        //Si no existe una ruta entre los actores
        if(ruta == null)
            return null;
        
        //Devolver la ruta encontrada
        return grafo.obtenerRutaAcotada(ruta);
    }

    /**
     * @pre movie debe existir en el archivo pasado como parametro al constructor.
     * @post Retorna los actores y actrices que protagonizan movie.
     */
    public Set<String> actors(String movie) {
        //Verificar que la pelicula existe en el grafo
        if(!grafo.containsVertex(movie))
            throw new IllegalArgumentException("La pelicula no existe en el grafo");

        return new HashSet<>(grafo.adj(movie));
    }

    /**
     * @pre act debe existir en el archivo pasado como parametro al constructor.
     * @post Retorna todas las peliculas en las que actua act.
     */
    public Set<String> movies(String act) {
        //Verificar que el actor existe en el grafo
        if(!grafo.containsVertex(act))
            throw new IllegalArgumentException("El actor no existe en el grafo");

        return new HashSet<>(grafo.adj(act));
    }
}
