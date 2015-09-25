package A4Q1;
import java.util.*;

/**
 *
 * Provides two static methods for sorting Integer arrays (heapSort and mergeSort)
 * @author jameselder
 */
public class YorkArrays {
    /* Sorts the input array of Integers a using HeapSort.  Result is returned in a.
     * Makes use of java.util.PriorityQueue.  
       Sorting is NOT in place - PriorityQueue allocates a separate heap-based priority queue. 
       Not a stable sort, in general.  
       Throws a null pointer exception if the input array is null.  */
	
	private static PriorityQueue<Integer> temp;
    public static void heapSort(Integer[] a) throws NullPointerException {
       //implement this method.
    	if( a == null){
    		throw new NullPointerException();
    	}
    	temp = new PriorityQueue<Integer>();
    	for(Integer s : a){
    		temp.add(s);
    	}
    	for(int j = 0; j < a.length; j++){
    		a[j] = temp.remove();
    	}
    }
    
    /* Sorts the input array of Integers a using mergeSort and returns result.
     * Sorting is stable.
       Throws a null pointer exception if the input array is null. */
    public static Integer[] mergeSort(Integer[] a)  throws NullPointerException {
        return(mergeSort(a, 0, a.length-1));
    }
    
    /* Sorts the input subarray of Integers a[p...q] using MergeSort and returns result.
     * Sorting is stable.
     */
    private static Integer[] mergeSort(Integer[] a, int p, int q) {
    	int mid = (q+p)/2, highlength = q - mid, high = q, low = p;
    	Integer[] firsthalf = new Integer[a.length/2];
    	
        if (a.length == 0 || low > high) { 
            return null;
        }
        else if (a.length == 1 ||low == high) {
        	highlength = mid = 1;
            firsthalf = new Integer[mid];
            low = 0;
            firsthalf[low] = a[p];
            return firsthalf;
        } 
        else{
            return merge(mergeSort(a, low, mid),mergeSort(a, mid+1,high));
        }
        
      	/*if(p > 1){
		System.arraycopy(a, p, firsthalf, 0, mid);
		mergeSort(firsthalf, p, firsthalf.length);
		
		System.arraycopy(a, mid+1, secondhalf, 0,highlength);
		mergeSort(secondhalf, p, secondhalf.length);
		
	}*/
        
    }
    
    /* Merges two sorted arrays of Integers into a single sorted array.  Given two
     * equal elements, one in a and one in b, the element in a precedes the element in b.
     */
    private static Integer[] merge(Integer[] a, Integer[] b) {
    	//final list to merge the two halves into
    	Integer[] finalList = new Integer[a.length+b.length];
    	// Indices of  list a, b and finalList respectively
    	int index1 = 0, index2 = 0, index3 = 0;
    	
    	for(;index1<a.length && index2<b.length;){
    		if(a[index1]<b[index2]){
    			finalList[index3++] = a[index1++];
    		}
    		else{
    			finalList[index3++] = b[index2++]; 
    		}
    	}
    	for(;index1 < a.length;){
    		finalList[index3++] = a[index1++];
    	}
    	for(;index2 < b.length;){
    		finalList[index3++]= b[index2++];
    	}
    	return finalList;
    }
}
