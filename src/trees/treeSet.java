import java.util.NoSuchElementException;
import java.util.List;
import java.util.LinkedList;

/**
 * Write a description of class treeSet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class treeSet<T extends Comparable<? super T>> implements SortedSet<T>
{
    // instance variables - replace the example below with your own
    private Node root;
    private int size;
    
    class Node {
        private T key;
        private Node left;
        private Node right;
        
        public Node(T key){
            this.key = key;
            left = right = null;
        }
        
        //Recorrido inorder
        public List inorder(){
            List result = new LinkedList();
            //agrega el hi
            if(left != null)
                result.addAll(left.inorder());
            //agrega raiz
            result.add(key);
            //agrega hd
            if(right != null)
                result.addAll(right.inorder());
            
            return result;
        }
    }

    /**
     * Constructor for objects of class treeSet
     */
    public treeSet(T key)
    {
        root = null;
        size = 0;
    }
    
    //Insercion una nueva hoja del árbol.
    public boolean add(T x){
        //Set no admite repetidos
        if(contains(x))
            return false;
        
        root = add(root, x);
        size++;
        return true;
    }
    
    public boolean remove(T x){
        if(!contains(x))
            return false;
        
        root = remove(root, x);
        size--;
        return true;
    }
    
    private Node remove(Node x, T key){
        if(x == null)
            return null;
        
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = remove(x.left, key);
        else if (cmp > 0)
            x.right = remove(x.right, key);
        else 
            //El elemento a eliminar es una hoja.
            //EL elemento a eliminar tiene solo un hijo izq o der
            if(x.left == null)
                return x.right;
            if(x.right == null)
                return x.left;
            //El elemento a eliminar tiene dos hijos. 
            Node t = x;
            x = max(t.left); // ahora x tiene el maximo de la izquierda (el nuevo padre para los hijos)
            t.left = removeMax(t.left); // elimino el maximo de la izquieda.
            x.left = t.left; // x.left = removeMax(t.left);
            x.right = t.right;
            
            
        return x;    
    }
    
    private Node add(Node x, T key){
        if(x == null)
            return new Node(key);
        int cmp = key.compareTo(x.key); // Compara la clave con el nodo actual.
        if(cmp < 0)
            x.left = add(x.left, key); // Inserta en el subárbol izquierdo.
        else if(cmp > 0)
            x.right = add(x.right, key); // Inserta en el subárbol derecho.
        else 
            assert false; // Podria usar una excepcion de elemento repetido.
            
        return x; // Devuelve el nodo actualizado.
    }

    public boolean contains(T x){
        return containsKey(root, x) != null;           
    }
    
    public int size(){
        return size;
    }
    
    public T min(){
        if(root == null)
            throw new NoSuchElementException("Árbol vacío");
            
        return min(root).key;
    }
    
    private Node min(Node x){
        if(x.left == null)
            return x;
        else
            return min(x.left);
    }
    
    public T max(){
        if(root == null)
            throw new NoSuchElementException("Árbol vacío");
          
        return max(root).key;
    }
    
    private Node max(Node x){
        if(x.right == null)
            return x;
        else
            x.right = max(x.right);
            
        return x;
    }
    
    public void removeMin(){
        if(root == null)
            throw new NoSuchElementException("Árbol vacío");
        
        root = removeMin(root);
        size--;
    }
    
    public void removeMax(){
      if(root == null)
            throw new NoSuchElementException("Árbol vacío");
        
      root = removeMax(root);
      size--;
    }
    
    private Node removeMax(Node x){
        if(x.right == null)
            return x.left;
        
        x.right = removeMax(x.right);
        return x;
    }
    
    private Node removeMin(Node x){
        if(x.left == null)
            return x.right;
            
        x.left = removeMin(x.left);
        return x;
    }
    
    private Node containsKey(Node x, T key){
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return containsKey(x.left, key);
        else if(cmp > 0)
            return containsKey(x.right, key);
        else
            return x;
    }
    
    public boolean repOK(){
        //Invariante -> tiene que cumplir con la propiedad de ABBs
        return isBST(root, null, null);
    }
    
    private boolean isBST(Node x, T min, T max){
        if(x == null)
            return true;
         
        if(min != null && x.key.compareTo(min) <= 0)
            return false;
        if(max != null && x.key.compareTo(max) >= 0)
            return false;
            
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
    
    public List<T> inorder(){
        if(root == null)
            return new LinkedList<T>();
        return root.inorder();    
    }
    
    public String toString(){
        String res = "{";
        boolean first = true;
        //izquierda - raiz - derecha
        List<T> elems = inorder(); // Crea una lista con los elementos del arbol
        for(T item : elems){
            if(!first)
                res += ", ";
            res += item.toString();
            first = false;  
        }
        res += "}";
        return res;
        
    }
}
