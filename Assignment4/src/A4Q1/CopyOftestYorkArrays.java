package A4Q1;
import java.util.*;
import java.lang.Math.*;

/**
 *
 * @author jameselder
 */
public class CopyOftestYorkArrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random();
        int n = 20;
        Integer[] a = new Integer[n];
        Integer[] b = null;
        long startTime, endTime;
        
        try {
            CopyOfYorkArrays.mergeSort(b);
        } catch (NullPointerException x) {
            System.out.println("mergeSort correctly detects null pointer");
        }
        try {
            CopyOfYorkArrays.heapSort(b);
        } catch (NullPointerException x) {
            System.out.println("heapSort correctly detects null pointer");
        }
        
        for (int i=0; i < n; i++) {
            a[i] = r.nextInt(n);
        }
        System.out.println("Input Array: " + Arrays.toString(a));
        
        startTime = System.nanoTime();
        b = CopyOfYorkArrays.mergeSort(a);
        endTime = System.nanoTime();
        System.out.println("Output Array (MergeSort)," + (endTime - startTime) / 1000 + " microseconds: " + Arrays.toString(b));

        b = a.clone();
        startTime = System.nanoTime();
        CopyOfYorkArrays.heapSort(a);
        endTime = System.nanoTime();
        System.out.println("Output Array (HeapSort):" + (endTime - startTime) / 1000 + " microseconds: " + Arrays.toString(a));

        startTime = System.nanoTime();
        Arrays.sort(b);
        endTime = System.nanoTime();
        System.out.println("Output Array (QuickSort):" + (endTime - startTime) / 1000 + " microseconds: " + Arrays.toString(b));
    }
}
