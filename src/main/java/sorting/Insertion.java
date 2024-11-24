
public class Insertion extends Sorting
{
   public Insertion(){}
   
   public void sort(Comparable[] a){
       for(int i = 1; i < a.length; i++){
           for(int j = i-1; j >= 0; j--){
               if(a[j+1].compareTo(a[j]) < 0){
                    exch(a, j+1, j);
               }
           }
       }
   }
}
