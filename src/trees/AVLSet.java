
/**
 * Write a description of class AVLSet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AVLSet<T extends Comparable<? super T>>
{
    private Node root;
    private int size; 

    /**
     * Constructor for objects of class AVLSet
     */
    public AVLSet()
    {
        root = null;
        size = 0;
    }
    
    public boolean contains(T x){
        return containsKey(root, x) != null;
    }
    
    public int size(){
        return size;
    }
    
    private Node containsKey(Node x, T key){
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return containsKey(x.left, key);
        else if(cmp > 0)
            return containsKey(x.right, key);
        else 
            return x;
    }
    
    private Node balance(Node x){
        // Altura(der) > Altura(Izq)
        if(balanceFactor(x) < -1){
            if(balanceFactor(x) > 0){
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        //Altura(izq) > Altura(der)
    }else if(balanceFactor(x) > 1){
            if(balanceFactor(x.left) < 0){
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
    }
        return x;
    }
    
    private Node rotateLeft(Node x){
        Node t = x.right;
        x.right = t.left;
        t.left = x;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        t.height = 1 + Math.max(height(t.left), height(t.right));
            
        return t;
    }
    
    private Node rotateRight(Node x){
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        
        x.height = 1 + Math.max(height(x.left), height(x.right));
        t.height = 1 + Math.max(height(t.left), height(t.right));
        return t;
    }
    // Altura del arbol
    public int height(){
        return height(root);
    }
    /** 
     * Si el arbol es vacio su altura es -1.
     * Si tiene un solo nodo entonces su altura es 0.
    */
    private int height(Node x){
        if(x == null)
            return -1;
        return x.height;
    }
    
    //Diferencia de altura entre arbol izq y arbol der
    private int balanceFactor(Node x){
        return height(x.left) - height(x.right);
    }

    private class Node{
        private T key;
        private Node left;
        private Node right;
        private int height;
        
        public Node(T key, int height){
            this.key = key;
            this.height = height;
            left = null;
            right = null;
        }
    }
}
