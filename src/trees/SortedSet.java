import java.util.Iterator;

/**
 * SortedSet are unbounded sets of objects of type T. 
 * A typical SortedSet is {o1, . . . , on}.
 * 
 * SortedSet requires that the key type T implements the 
 * Comparable interface.
 * 
 * The methods use compareTo to determine equality of elements.
 */
public interface SortedSet<T extends Comparable<? super T>> {       
    /**
     * @post Adds 'x' to the elements of 'this'.
     *   More formally, it satisfies: this = old(this) U {e}.
     */
    public boolean add(T x);
    /**
     * @post Removes 'x' from 'this'. Returns true iff 'x' 
     *   was removed. More formally, it satisfies: 
     *      result = (e in old(this)) && this = old(this) \ {e}.
     */    
    public boolean remove(T x);
    /**
     * @post Returns true iff 'x' is in 'this'.
     */ 
    public boolean contains(T x);
    /**
     * @post Returns the cardinality of 'this'.
     *   More formally, it satisfies: #this.
     */  
    public int size();
    /**
     * @pre !isEmpty()
     * @post Returns the smallest element of 'this'.
     */   
    public T min();
    /**
     * @pre !isEmpty()
     * @post Returns the largest element of 'this'.
     */   
    public T max();
    /**
     * @post Deletes the smallest element of 'this'.
     */   
    public void removeMin();
    /**
     * @post Deletes the largest element of 'this'.
     */   
    public void removeMax();
    /**
     * @post Returns true if and only if the structure is a 
     *   valid set.
     */
    public boolean repOK();
    /**
     * @post Returns a string representation of the set. Implements
     *   the abstraction function. It represents the set by showing
     *   its elements in increasing order "{o1, o2,..., on}".
     */
    public String toString();
    
    /**
     * @post Returns an iterator that iterates through 
     *   the elements in the set in sorted order.
     */
    //public Iterator<T> iterator();
}