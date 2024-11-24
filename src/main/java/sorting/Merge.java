public class Merge extends Sorting
{
   public Merge(){}
   
   public void sort(Comparable[] a){
       mergeSort(a, 0, a.length-1);
    }
   
   private void mergeSort(Comparable[] a, int inicio, int fin){
       int medio = 0;
       if(inicio < fin){
           medio = (inicio + fin) / 2;
           
           //Llamadas recursivas
           mergeSort(a, inicio, medio);
           mergeSort(a, medio +1, fin);
       
           //Mezclar las dos mitades ordenadas
           mezclar(a, inicio, medio, fin);
       }
   }
   private void mezclar(Comparable[] a, int inicio, int medio, int fin){
      Comparable[] aux = new Comparable[(fin-inicio) +1];
      int i = inicio; // Inicio para la primera mitad.
      int j = medio+1; // Inicio para la segunda mitad.
      int k = 0; // Indice para el arreglo auxiliar.
  
          // Mezclar elementos de ambas mitades
          while(i <= medio && j <= fin){
                if(a[i].compareTo(a[j]) <= 0){
                      aux[k] = a[i];
                      i++;
                      k++;
                }else {
                      aux[k] = a[j];
                      j++;
                      k++;
                }
          }
          
         // Copiar los elementos restantes de la primera mitad, si los hay 
         while(i <= medio){
            aux[k] = a[i];
            i++;
            k++;
         }
          
         // Copiar los elementos restantes de la segunda mitad, si los hay 
         while(j <= fin){
            aux[k] = a[j];
            j++;
            k++;
        }
        
        for(k = 0; k < aux.length; k++){
            a[inicio + k] = aux[k];
        }
    }    
} 
