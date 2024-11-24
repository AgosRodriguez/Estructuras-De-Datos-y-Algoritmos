import java.lang.Comparable;
/**
 * Write a description of class HeapPriorityQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HeapPriorityQueue<T extends Comparable<? super T>> implements PriorityQueue<T>
{
  private T[] queue;
  private int size;
  private static final int INIT_CAPACITY = 8;
  
  public HeapPriorityQueue(){
      queue = (T[]) new Comparable[INIT_CAPACITY+1];
      size = 0;
  }
  
  public void insert(T x){
      if(size == INIT_CAPACITY+1)
          resize(queue.length * 2); //  Redimensionar si el arreglo está lleno
          
      int i = size + 1;    
      queue[i] = x;
      swim(i);    
      size++;
  }
  
  public T max(){
      return queue[1];
  }
  
  public T removeMax(){
      if(size == 0)
            throw new IllegalStateException("Arreglo vacío");
      T aux = max();
      //Intercambio raiz por hoja (mas a la derecha)
      swap(1, size);
      //Eliminar la ultima hoja
      queue[size] = null;
      size--;
      
      sink(1);
      return aux;
  }
  
  public boolean isEmpty(){
      return size == 0;
  }
  
  public int size(){
      return size;
  }
  // Redimensionar el arreglo cuando se llena
    private void resize(int newCapacity) {
        T[] temp = (T[]) new Comparable[newCapacity];
        for (int i = 1; i <= size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

  public boolean repOk(){
      for(int i = 1; i<= size; i++){
          if(queue[i] == null) return false;
      }
      
      for(int i = size + 1; i < queue.length; i++){
          if(queue[i] != null) return false;
      }
      
      if(queue[0] != null) return false;
      
      return isMaxHeapOrdered(1);
  }
  
  private boolean isMaxHeapOrdered(int k){
      if(k > size) return true;
      int left = 2 * k;
      int rigth = 2*k + 1;
      if(left <= size && queue[k].compareTo(queue[left]) < 0) 
          return false;
      if(rigth <= size && queue[k].compareTo(queue[rigth]) < 0)
          return false;
      return isMaxHeapOrdered(left) && isMaxHeapOrdered(rigth);
  }
  
  // Opera de abajo hacia arriba
  private void swim(int k){
      Comparable aux;
      while(k > 1 && queue[k].compareTo(queue[k/2]) > 0){
          swap(k, k/2);
          k = k/2;
      }
  }
  
   private void swap(int i, int j) {
        T temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }
  
  // Opera de arriba hacia abajo
  private void sink(int k){
    // Verifica que exista rama izquierda  
    while(2*k <= size){
        int j = 2*k;
        if(j < size && queue[j].compareTo(queue[j+1]) < 0)
            j++;
        if(queue[k].compareTo(queue[j]) >= 0) break;
        
        swap(k,j);
        k = j;
    }
  }
}
