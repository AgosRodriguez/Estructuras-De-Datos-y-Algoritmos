import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Stream;
import java.util.Arrays;

/**
 * The test class SelectionTest.
 */
public class SortingTest
{
    @Test
    public void test0()
    {
        Integer [] arr = new Integer [] {3,2,1};
        Integer [] expected = new Integer [] {1,2,3};        
        Sorting s = new Bubble();
        s.sort(arr);
        assertTrue(Arrays.equals(arr, expected));
        assertTrue(s.isSorted(arr));
    }
    
    @Test
    public void test1()
    {
        Integer [] arr = new Integer [] {2,3,1,5,6,9,6,3};
        Integer [] expected = new Integer [] {1,2,3,3,5,6,6,9};
        Sorting s = new Bubble();
        s.sort(arr);
        assertTrue(Arrays.equals(arr, expected));
        assertTrue(s.isSorted(arr));
    }

}
