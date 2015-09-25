package A3Q2;

/**
 * Stores a pair of sorted arrays of equal length.  The arrays are assumed to
 * be disjoint, i.e., none of the elements are repeated, either within or between
 * the arrays.  Elements within each array are stricly increasing in value.
 * A method is provided to find the kth smallest value in the union of the two
 * arrays, in O(log k) time.
 * @author jameselder
 */
public class SortedArrayPair<E extends Comparable<E>> {
    E[] A1, A2;
    /* Note that the constructor assumes the arrays to be pre-sorted.*/
    public SortedArrayPair(E[] array1, E[] array2) throws NullPointerException,UnmatchedArrayPairException {
        if (array1 == null | array2 == null) {
            throw new NullPointerException();
        }
        if (array1.length != array2.length) {
            throw new UnmatchedArrayPairException("Unmatched array lengths");
        }
        A1 = array1;
        A2 = array2;
    }

    /**
     * Returns the kth smallest value in the union  of the two arrays,
     * in O(log k) time.  An exception is thrown if k is not positive,
     * or if it is more than double the length of the arrays.
     * @param k
     * @return
     * @throws RankOutOfRangeException
     */
    public E kthSmallestOfUnion(int k) throws RankOutOfRangeException {
    	int arrayLength = A1.length;
    	if (k <= 0 || k >(2*arrayLength)){
    		throw new RankOutOfRangeException();
    	}
    	// if value of k is first index 
    	if(k < 2){
            if (A2[0].compareTo(A1[0])> 0) {
                return A1[0];
            } else {
                return A2[0];
            }
    	}
    	//lower bound
        int lowA1 = Math.max(0, k - arrayLength - 1);
        //higher bound
        int highA1 = Math.min(k, arrayLength) - 1;
        
        int lowA2 = lowA1;
        int highA2 = highA1;

    	//binary search algorithm
        for(;lowA1 < highA1 || lowA2 < highA2;){
        	
        	int midA1 = (lowA1+highA1+1)/2;//(int)Math.ceil((lowA1+highA1)/2);
        	int midA2 = k-1-midA1;//(int)Math.floor(k-1-midA1);
        	
        	if(A2[midA2].compareTo(A1[midA1])> 0){
        		highA2 = midA2-1;
        		lowA1 = midA1;
        	}
        	else{
        		highA1 = midA1-1;
        		lowA2 = midA2;
        	}
        }
        if (highA1 < lowA1) {
            return A1[highA2];
        } 
        else if (highA2 < lowA2) {
            return A2[highA1];
        } 
        else{ 
        	if (A1[highA1].compareTo(A2[highA2]) > 0) {
        		return A1[highA1];
        	} 
        	else {
        		return A2[highA2];
        	} 
        }   
	}
}

