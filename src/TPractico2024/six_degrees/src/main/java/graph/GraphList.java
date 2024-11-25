package graph;

import java.util.TreeMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
 
/*Implementación  de Graph con arreglo de listas encadenadas, para tipo de datos genéricos */

public class GraphList<T extends Comparable<? super T>> implements Graph<T>
{
    //Cantidad de vértices en el grafo
    private int V;
    //Cantidad de aristas en el grafo
    private int E;
    //Estructura que gusda el elemento del vertice con su índice
    private TreeMap<T, Integer> map;
    //Arreglo de elementos que hay en los vertices
    private T[] keys;
    //Arreglo de listas con los adyacentes a cada vertice
    private List<Integer>[] adj;
    

    /**
     * Constructor de la clase GraphList 
     */
    public GraphList(){
        adj = new LinkedList[0];
        V = 0;
        E = 0;
        map = new TreeMap<>();
        keys = (T[]) new Comparable[0];
    }
    
     /**
     * @post Returns the number of vertices in this graph.
     */
    public int V(){
        return V;
    }

    /**
     * @post Returns the number of edges in this graph.
     */
    public int E(){
        return E;
    }

    /**
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph.
     */
    public void addVertex(T v){
        //Compruebo que se cumpla la precondición
        if (containsVertex(v)) 
            throw new IllegalArgumentException("El vértice ya existe");

        map.put(v, V);
        if (V >= adj.length){    //Actualizo el tamaño del arrglo si llego a la última posición
            resize((V+1)*2);
        }
        adj[V] = new LinkedList<>();
        keys[V] = v;
        V++;        
    }
    
    /**
     * @post Actualiza el tamaño de los arreglos adj y keys
     */
      private void resize(int capacity){
        List<Integer>[] remplazo = (List<Integer>[]) new LinkedList[capacity];
        T[] remplazo2 = (T[]) new Comparable[capacity];
        for(int i=0; i<V; i++){
            remplazo[i] = adj[i];
            remplazo2[i] = keys[i];
        } 
        adj = remplazo;
        keys = remplazo2;
    }
    
    /**
    * @post Returns true iff there is a vertex with label v
    * in this graph.
    */
    public boolean containsVertex(T v){
            return map.containsKey(v);
    }

    /**
     * @pre v and w are vertices of the graph
     * @post Adds the undirected edge v-w to this graph.
     */
    public void addEdge(T v, T w){
        if (!containsVertex(v)) 
            throw new IllegalArgumentException("El vértice no existe");
        if (!containsVertex(w)) 
            throw new IllegalArgumentException("El vértice no existe");

        E++;
        int vid = map.get(v); 
        int wid = map.get(w); 
        adj[vid].add(wid);
        adj[wid].add(vid);
    }

    /**
     * @pre v is a vertex of the graph
     * @post Returns the list of vertices adjacent to vertex v.
     */
    public List<T> adj(T v){
        if (!containsVertex(v)) 
            throw new IllegalArgumentException("El vértice no existe");
            
        List<T> elems = new LinkedList<>();
        
        Integer i = map.get(v);
        for(Integer elem : adj[i]){
            elems.add(keys[elem]);
        }
        return elems;
    }
    

    /**
     * @pre Los elementos s y n son distintos
     * @post Retorna la lista de indices que se recorren desde el elemento s al elelmento n
     */
    public List<Integer> bfs(T s, T n){
        int ss = map.get(s);  //obtengo indice del vertice s
        int nn = map.get(n);  //obtengo indice del vertice s
        int[] edgeTo = new int[V()];  //guarda desde donde viene el recorrido
        boolean[] marked = new boolean[V()]; //gurda elementos marcados
        
        Queue<Integer> q = new LinkedList<Integer>();  //cola de los vertices que se van recorriendo
        marked[ss] = true;  // marca verdadera la posición del índice desde donde parto
        q.add(ss);  //agrego indice inicial a la cola
        
        //Recorro el ciclo mientras la cola tiene elementos
        while (!q.isEmpty()) {
            int v = q.poll();  //saco el primer elemento de la cola se lo asigno a v
            for(int w : adj[v]) { //Recorro los elementos adyacentes al vertice v
                if (!marked[w]){     
                    marked[w] = true;   //marco que ya recorri el vertice de la posición w   
                    edgeTo[w] = v;      //guardo desde donde viene el recorrido 
                    q.add(w);           //lo agrego al final de la cola
                    
                    if(w==nn){
                        //cuando encuentra al segundo elemento, retorna la ruta hasta el punto encontrado 
                        return descubrirRuta(edgeTo, ss, nn); 
                    }
                }
            }
            
        }

        //si recorrió todos los vértices desde s, y no encontro a n, entonces no hay ruta que los conecte y retorna null
        return null;
    }

    /**
     * @pre ini y fin deben ser indices dentro del rango de path
     * @post Retorna la lista de indices que se recorren desde el elemento s al elelmento n
     */
    private List<Integer> descubrirRuta(int[] path, int ini, int fin){ 
        List<Integer> ruta = new LinkedList<>();
        //ciclo que añade los elementos a la ruta desde el final
        for (int x = fin; x != ini; x = path[x]) {
            ruta.add(x);
        }
        ruta.add(ini); // Agregar el nodo inicial

        //Invertir para obtener el camino en el orden correcto
        List<Integer> rutaInvertida = new LinkedList<>();
        for (int i = ruta.size() - 1; i >= 0; i--) {
            rutaInvertida.add(ruta.get(i));
        }
        return rutaInvertida;
    }
    
    /**
     * @post Retorna una lista, reemplazando los índices por los elementos, y sacando las peliculas
     */
    public List<T> obtenerRutaAcotada(List<Integer> path) {
        // Crear una lista vacía para almacenar la ruta convertida
        List<T> ruta = new LinkedList<>();
        boolean actor = true;
        
        // Recorrer la lista de índices y convertir cada uno a su etiqueta
        for (Integer index : path) {
            T vertice = getVertexByIndex(index);  //Comprueba que el indice sea correcto, y convierte índice a elemento T 
            if (actor) {
                ruta.add(vertice);  // Agrega el elemento a ruta
            }
            actor = !actor;
        }
        // Retornar la lista de solo actores y actrices
        return ruta;
    }
    
    
    /**
     * Devuelve el vértice asociado a un índice dado.
     */
    private T getVertexByIndex(int index) {
        if (index < 0 || index >= keys.length) {
            throw new IllegalArgumentException("Indice fuera de rango");
        }
        return keys[index];
    }
}
