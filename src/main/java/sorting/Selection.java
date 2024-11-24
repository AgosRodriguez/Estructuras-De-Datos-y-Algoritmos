 /**
 *  Implements the selection sort algorithm for
 *  sorting an array.
 */
public class Selection extends Sorting {

    /**
     * @post Creates an object that encapsulates the selection 
     *   sort algorithm.
     */
    public Selection() { }

    /**
     * @post Rearranges the array a in ascending order
     *   using the selection sort algorithm.
     */
    public void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // Invariant: a[0...i-1] is sorted
            assert isSorted(a, 0, i-1);
            // Exchange a[i] with smallest entry in a[i...n-1]
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) 
                    min = j;
            }
            exch(a, i, min);
            // Now a[0...i] is sorted
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }
}

