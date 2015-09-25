package A2Q1;
import java.util.*;
/**
 * Represents a sorted integer array.  Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k.  Runs in O(n) time, where n is the length of the array.
 * @author jameselder
 */
public class SortedIntegerArray {

    protected int[] sortedIntegerArray;

    public SortedIntegerArray(int[] integerArray) {
        sortedIntegerArray = integerArray.clone();
        Arrays.sort(sortedIntegerArray);
    }

/**
 * Determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * @author jameselder
 */
    public boolean kPairSum(Integer k) {
    	//call the helper method with the 
    	return kPairSum(k, 0, sortedIntegerArray.length - 1);
    }
    
    // a recursive helper method
    /**
     * 
     * @param k the integer sum to check
     * @param i	the start point of the array i.e. 0
     * @param j the end point of the array i.e. length -1
     * @return true if the sum exists and false if the sum does not exist
     */
    private boolean kPairSum(Integer k, int i, int j) {
    	// base case if index i become greater than or 
    	//equal to j the return false and halt
    	if (i >= j) {
    		return false;
    		}
    	
    	// keep adding i and j on each index
    	int sum = sortedIntegerArray[i] + sortedIntegerArray[j]; 
    	
    	//if the sum achieved is equal to k then return true
    	if (sum == k.intValue()) {
    		return true;
    		}
    	//else sum is > than  value of k then move j index down  and try adding
    	if (sum > k.intValue()) {
    		return kPairSum(k, i, j - 1);
    		
    		}
    	//else sum is < than  value of k then move i index up  and try adding
    	return kPairSum(k, i + 1, j);
    }
}
