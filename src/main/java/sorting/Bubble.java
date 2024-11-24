
/**
 * Write a description of class Bubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bubble extends Sorting {
   public Bubble(){}
   
   
   public void sort(Comparable[] a){
       int n = a.length;
       for(int i= 0; i < a.length; i++){
            int k = 0;
            for(int j = k + 1; j < n; j++){
                if(less(a[j], a[k]))
                    exch(a, k, j);
                k++;
            }
            n--;
       }
       
   }
   
   public void bubbleRecursivo(Comparable[] a, int n){
       if(n == 1)
           return;
       
       bubbleSort(a, n);    
           
       bubbleRecursivo(a, n-1);
   }
   
   private void bubbleSort(Comparable[] a, int n){
        for(int i = 0; i < n-1; i++){
            if(less(a[i], a[i+1]))
                exch(a, i, i+1);
        }
   }
   
}
