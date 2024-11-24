public class QuickSort extends Sorting
{
   public QuickSort() {}
    
   public void sort(Comparable[] a){
       ordenar(a, 0, a.length-1);
   }
   
   protected void ordenar(Comparable[] a, int inicio, int fin){ 
       if(fin <= inicio) return;
           int j = partition(a, inicio, fin);
           ordenar(a, inicio, j-1);
           ordenar(a, j+1, fin);   
   }
    
   private int partition(Comparable[] a, int ini, int fin){
       Comparable v = a[ini];
       int i = ini + 1;
       int j = fin;
       
       while(true){
           while(i < j && less(a[i],v ))
               i++;            
           while(i <= j && less(v, a[j])){
               j--;
           }
           
           if (i >= j) break;
           exch(a, i,j);
       }
           exch(a,ini,j);
           
           return j;
   }
}

