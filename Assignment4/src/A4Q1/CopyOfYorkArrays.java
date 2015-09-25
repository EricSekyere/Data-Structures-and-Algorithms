package A4Q1;
import java.util.*;

/**
 *
 * Provides two static methods for sorting Integer arrays (heapSort and mergeSort)
 * @author jameselder
 */
public class CopyOfYorkArrays {

    /* Sorts the input array of Integers a using HeapSort.  Result is returned in a.
     * Makes use of java.util.PriorityQueue.  
       Sorting is NOT in place - PriorityQueue allocates a separate heap-based priority queue. 
       Not a stable sort, in general.  
       Throws a null pointer exception if the input array is null.  */
    public static void heapSort(Integer[] a) throws NullPointerException {
       //implement this method.
    	if (a == null){
    		throw new NullPointerException();
    	}
    	int condition = a.length;
    	int cHS = 0;
    	int temp;
    	while (condition > 0){
    		if ( (2 * cHS) + 2 <= a.length){
    			if (a[cHS] < a[(2 * cHS) + 2] || a[cHS] < a[(2 * cHS) + 1]){
    				if (a[(2 * cHS) + 1] <= a[(2 * cHS) + 2]){
    				temp = a[cHS];
    				a[cHS] = a[(2 * cHS) + 2];
    				a[(2 * cHS) + 2] = temp;
    				}
    				else{
    					temp = a[cHS];
        				a[cHS] = a[(2 * cHS) + 1];
        				a[(2 * cHS) + 1] = temp;
    				}
    				cHS++;
    			}
    		}
    		temp = a[0];
    		a[0] = a[condition - 1];
    		a[condition - 1] = temp;
    		condition--;
    	}
    	
    }
    /* Sorts the input array of Integers a using mergeSort and returns result.
     * Sorting is stable.
       Throws a null pointer exception if the input array is null. */
    public static Integer[] mergeSort(Integer[] a)  throws NullPointerException {
    	if(a == null){
    		throw new NullPointerException();
    	}
        return(mergeSort(a, 0, a.length-1));
    }
    
    /* Sorts the input subarray of Integers a[p...q] using MergeSort and returns result.
     * Sorting is stable.
     */
    private static Integer[] mergeSort(Integer[] a, int p, int q) {
        //implement this method.
    	if(a.length > 1||q>p){
    		int midpoint = (p + q) / 2;
	    	int mergeArraysize = q - p;
	    	Integer[] left = new Integer[midpoint]; 
	    	Integer[] right = new Integer[q - midpoint];
	    	Integer[] sortedArray = new Integer[mergeArraysize + 1];
		    left = mergeSort(a, p, midpoint);
			right = mergeSort(a, (midpoint + 1), q);
		    sortedArray = merge(left, right);
	    	return sortedArray;
    	}
    	else{
    		return a;
    	}
    }
    
    /* Merges two sorted arrays of Integers into a single sorted array.  Given two
     * equal elements, one in a and one in b, the element in a precedes the element in b.
     */
    private static Integer[] merge(Integer[] a, Integer[] b) {
        //implement this method.
        int mergeListSize = a.length + b.length;
    	Integer[] mergeList = new Integer[mergeListSize];
        int CmList = 0; // counter for merged Array
        int counterA = 0; // counter for array a
        int counterB = 0; // counter for array b
        while(CmList < mergeListSize){
            if((counterA < a.length) && (counterB < b.length)){
                if(a[counterA] <= b[counterB]){
                    CmList++;
                    counterA++;
                    mergeList[CmList] = a[counterA];

                }
                else{
                	CmList++;
                    counterB++;
                	mergeList[CmList] = b[counterB];
                }
            }
            else{
                    while (counterB < b.length){
                    	CmList++;
                        counterB++;
                    	mergeList[CmList] = b[counterB];

                    }
                    while(counterA < a.length){
                    	counterA++;
                    	CmList++;
                    	mergeList[CmList] = a[counterA];

                    }
            }
        }
        return mergeList;
    }
}