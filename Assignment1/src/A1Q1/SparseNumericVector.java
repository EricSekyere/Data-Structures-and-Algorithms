package A1Q1;
import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value.  The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like
 * inner products (projections).  Note that location indices can be any integer
 * from 1 to Long.MAX_VALUE.  The representation is based upon a
 * singly-linked list, with header and trailer nodes to facilitate operations.
 * The following methods are supported:  iterator, getSize, getFirst,
 * add, remove, and project, which projects the vector onto a second vector
 * passed as a parameter.
 * @author jameselder
 */
public class SparseNumericVector implements Iterable {

    protected SparseNumericNode header;
    protected SparseNumericNode trailer;
    protected long size;

    /**
     * Constructor
     */
    public SparseNumericVector() {
        trailer = new SparseNumericNode(new SparseNumericElement(Long.MAX_VALUE, 0), null);
        header = new SparseNumericNode(new SparseNumericElement(1, 0), trailer);
        size = 0;
    }

   /**
     * Iterator
     */
    public Iterator<SparseNumericElement> iterator() { //iterator
        return new SparseNumericIterator(this);
    }

    /**
     * @return number of non-zero elements in vector
     */
   public long getSize() {
        return size;
    }

     /**
     * @return the first node in the list.
     */
     public SparseNumericNode getFirst() {
        if (size == 0)
                return null;
        else
            return header.getNext();
    }
    
    /**
     * Add the element to the vector.  It is inserted to maintain the
     * vector in increasing order of index.  If an element with the same
     * index already exists, its value is updated.
     * @param e element to add
     */
   public void add(SparseNumericElement e) {
	// we iterate based on the header
	   SparseNumericNode current = header;
	   // put the element e in a node
	   SparseNumericNode temp = new SparseNumericNode(e, null);
	   if(e.getValue()!=0){
	      

	   if(this.getSize() == 0 && current.getNext() == trailer){
	       temp.setNext(trailer);
	       current.setNext(temp) ;
	       
	   }
	   else if(this.getSize() == 0 && e.getIndex() == 1){
	      current = temp;
	   }

	   while(current.getNext()!= trailer  &&  (e.getIndex() > current.getNext().getElement().getIndex())){
	      current = current.getNext();
	   }

	   if(e.getIndex() == current.getNext().getElement().getIndex()){
	      current.getNext().getElement().setValue( e.getValue());
	   } else {
	      temp.setNext(current.getNext());
	      current.setNext(temp);                
	   }
	   size++;

	   } else if (e.getValue() == 0) {
	      boolean k = remove(e.getIndex());
	      if(k){
	   	   size--;
	      }
	   }
    }

    /**
     * If an element with the specified index exists, it is removed and the
     * method returns true.  If not, it returns false.
     *
     * @param index of element to remove
     * @return true if removed, false if does not exist
     */
   public boolean remove(Long index) {
	   // store  the header in a node
   	SparseNumericNode current = header;
   	// if the next element is the trailer then just return false
       if (current.getNext() == trailer){
           return false;
       }
       else{
         while(current.getNext()!=trailer){
       	  current = current.getNext();
       	  if (index == current.getNext().getElement().getIndex()){
       		  current.setNext((current.getNext()).getNext());
       		  size--;
       		  return true;
       		  
       	  }
         }
       }
		return false;
       
   }

    /**
     * Returns the inner product of the vector with a second vector passed as a
     * parameter.  The vectors are assumed to reside in the same space.
     * Runs in O(m+n) time, where m and n are the number of non-zero elements in
     * each vector.
     * @param Y Second vector with which to take inner product
     * @return result of inner product
     */

    public double project(SparseNumericVector Y) {
        double result = 0;
        SparseNumericNode temp = this.header;
        SparseNumericNode current = temp;
        SparseNumericNode input = Y.header;
        
        while(input !=  Y.trailer ){
        	while(current != this.trailer){
        		if (current.getElement().getIndex() == input.getElement().getIndex()){
        			double value = current.getElement().getValue()*input.getElement().getValue();
        			result += value;
        		}
        		// move to the next node
        		current  = current.getNext();
        	}
        	current = temp;
        	input = input.getNext();
        }
		return result;     
    }

   

       /**
     * returns string representation of sparse vector
     */

    public String toString() {
        String sparseVectorString = "";
        Iterator<SparseNumericElement> it = iterator();
        SparseNumericElement x;
        while (it.hasNext()) {
            x = it.next();
            sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
            //System.out.println("(index " + x.getIndex() + ", value " + x.getValue() + ")");
        }
        return sparseVectorString;
    }
}
